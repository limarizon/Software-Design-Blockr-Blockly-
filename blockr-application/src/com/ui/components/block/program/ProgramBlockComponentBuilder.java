package com.ui.components.block.program;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.*;
import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.WindowRegion;
import com.ui.components.block.graphics.BlockSizes;

import java.util.ArrayList;
import java.util.List;

/**
 * The class that draws the entire blockProgram in the Program Area based on the calculated AttachLocations and
 * programDefinition given in the.
 */
public class ProgramBlockComponentBuilder {

    private final List<ProgramBlockComponent> components;

    private final List<WindowRegion> regionPositions;

    public ProgramBlockComponentBuilder(List<ProgramBlockComponent> components,List<WindowRegion> regionPositions) {
      this.components=components;
      this.regionPositions= regionPositions;
    }

    public List<ProgramBlockComponent> getComponents() {
        return components;
    }

    /**
     * Gets child region.
     *
     * @param child the child
     * @return the child region
     */
    public WindowRegion getChildRegion(Component child) {
        int index = components.indexOf(child);
        if(index == -1) return null;
        return regionPositions.get(index);
    }

    public static Builder builder(GameState state,UiMediator mediator){
        return new Builder(state,mediator);
    }

    public static class Builder {

        public static final int START_POSITION = 30;
        public static final int SPACE_BETWEEN = 0;
        private static final int SPACE_BETWEEN_MAIN_AND_FUNCTION = 50;

        private WindowPosition currentPos;

        private List<ProgramBlockComponent> components = new ArrayList<>();
        private List<WindowRegion> regionPositions = new ArrayList<>();
        private final GameState state;
        private final UiMediator mediator;


        public Builder(GameState state,UiMediator mediator){
            this.currentPos = new WindowPosition(START_POSITION,START_POSITION);
            this.state = state;
            this.mediator = mediator;
        }

        private WindowPosition addStatementBlock(StatementBlock statementBlock,WindowPosition rootPos){
            ProgramStatementBlockComponent blockComponent = new ProgramStatementBlockComponent(state, statementBlock, mediator, rootPos);
            components.add(blockComponent);
            regionPositions.add(new WindowRegion(rootPos.getX(), rootPos.getY(), rootPos.getX() + blockComponent.getWidth(), rootPos.getY() + blockComponent.getHeight()));
            return rootPos.plus(new WindowPosition(0,SPACE_BETWEEN + blockComponent.getHeight()));
        }

        private WindowPosition addPredicateBlock(PredicateBlock pred,WindowPosition pos){
            ProgramPredicateBlockComponent predicate = new ProgramPredicateBlockComponent(state, pred, mediator, pos);
            components.add(predicate);
            regionPositions.add(new WindowRegion(pos.getX(), pos.getY(), pos.getX() + predicate.getWidth(), pos.getY() + predicate.getHeight()));
            return pos.plus(new WindowPosition(BlockSizes.CONDITION_BLOCK_WIDTH,0));
        }

        private WindowPosition addControlflowBlock(StatementBlock statementBlock,WindowPosition rootPos){
            var blockComponent = new ProgramControlFlowBlockComponent(state, (ContainingStatementsBlock) statementBlock, mediator, rootPos);
            components.add(blockComponent);
            regionPositions.add(new WindowRegion(rootPos.getX(), rootPos.getY(), rootPos.getX() + blockComponent.getWidth(), rootPos.getY() + blockComponent.getHeight()));
            addStatementList(rootPos.plus(new WindowPosition(BlockSizes.CONTROL_FLOW_INNER_START,SPACE_BETWEEN + BlockSizes.CONDITION_BLOCK_HEIGHT)), ((ContainingStatementsBlock) statementBlock).getStatementListBlock().getStatements());
            if(statementBlock.canContainPredicate()){
                var pred = ((ContainingPredicateBlock) statementBlock).getPredicate();
                if(pred!=null){
                    var pos = addPredicateBlock(pred,rootPos.plus(new WindowPosition(BlockSizes.BLOCK_WIDTH,0)));
                    if(pred.hasSubPredicate()) {
                        addPredicateBlock(((NotBlock) pred).getPredicateToNegate(), pos);
                    }
                }
            }
            return rootPos.plus(new WindowPosition(0,SPACE_BETWEEN + blockComponent.getHeight()));
        }

        private WindowPosition addStatementList( WindowPosition rootPos, List<StatementBlock> statementBlocks){
            for(StatementBlock statementBlock : statementBlocks ){
                if(statementBlock.canContainStatements()){
                    rootPos = addControlflowBlock(statementBlock,rootPos);
                }else{
                    rootPos = addStatementBlock(statementBlock,rootPos);
                }
            }
            return rootPos;
        }

        public Builder addBlockProgram(List<StatementBlock> statementBlocks){
            this.currentPos = addStatementList(currentPos, statementBlocks).plus(new WindowPosition(0, SPACE_BETWEEN_MAIN_AND_FUNCTION));
            return this;
        }

        public ProgramBlockComponentBuilder build(){
            return new ProgramBlockComponentBuilder(components,regionPositions);
        }
    }

}
