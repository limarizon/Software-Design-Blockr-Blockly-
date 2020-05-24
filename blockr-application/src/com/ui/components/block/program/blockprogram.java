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
public class blockprogram {
    /**
     * The components that will be present in the UI
     */
    private final List<ProgramBlockComponent> components;
    /**
     * The corresponding window regions of the components
     */
    private final List<WindowRegion> regionPositions;

    /**
     * constructor which initiates the components and window regions
     * @param components The components that will be present in the UI
     * @param regionPositions The corresponding window regions of the components
     */
    public blockprogram(List<ProgramBlockComponent> components, List<WindowRegion> regionPositions) {
      this.components=components;
      this.regionPositions= regionPositions;
    }

    /**
     * provides all the components that are built by this object
     * @return a list of components that will be present in the UI
     */
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

    /**
     * This method creates a builder object which will build all the components and window regions of this object
     * @param state
     * @param mediator
     * @return
     */
    public static Builder builder(GameState state,UiMediator mediator){
        return new Builder(state,mediator);
    }

    /**
     * This class is a builder responsible of the creation of the components that will be drawn in the UI
     */
    public static class Builder {

        public static final int START_POSITION = 30;
        public static final int SPACE_BETWEEN = 0;
        private static final int SPACE_BETWEEN_MAIN_AND_FUNCTION = 50;

        private WindowPosition currentPos;

        private List<ProgramBlockComponent> components = new ArrayList<>();
        private List<WindowRegion> regionPositions = new ArrayList<>();
        private final GameState state;
        private final UiMediator mediator;

        /**
         * constructor which initialises the builder and all its properties
         * @param state the state of the game
         * @param mediator the mediator object
         */
        public Builder(GameState state,UiMediator mediator){
            this.currentPos = new WindowPosition(START_POSITION,START_POSITION);
            this.state = state;
            this.mediator = mediator;
        }

        /**
         * creates and adds a statement block
         * @param statementBlock the statement block
         * @param rootPos the begin position from where the creation begins
         * @return the resulting position of the last block that was built
         */
        private WindowPosition addStatementBlock(StatementBlock statementBlock,WindowPosition rootPos){
            ProgramStatementBlockComponent blockComponent = new ProgramStatementBlockComponent(state, statementBlock, mediator, rootPos);
            components.add(blockComponent);
            regionPositions.add(new WindowRegion(rootPos.getX(), rootPos.getY(), rootPos.getX() + blockComponent.getWidth(), rootPos.getY() + blockComponent.getHeight()));
            return rootPos.plus(new WindowPosition(0,SPACE_BETWEEN + blockComponent.getHeight()));
        }

        /**
         * creates and adds a predicate block
         * @param pred the predicateblock
         * @param pos the begin position from where the creation begins
         * @return the resulting position of the last block that was built
         */
        private WindowPosition addPredicateBlock(PredicateBlock pred,WindowPosition pos){
            ProgramPredicateBlockComponent predicate = new ProgramPredicateBlockComponent(state, pred, mediator, pos);
            components.add(predicate);
            regionPositions.add(new WindowRegion(pos.getX(), pos.getY(), pos.getX() + predicate.getWidth(), pos.getY() + predicate.getHeight()));
            return pos.plus(new WindowPosition(BlockSizes.CONDITION_BLOCK_WIDTH,0));
        }

        /**
         * creates and adds a controlflow block and all its components
         * @param statementBlock the controlflowblock
         * @param rootPos the begin position from where the creation begins
         * @return the resulting position of the last block that was built
         */
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

        /**
         * creates and add all the components a list of statement blocks
         * @param rootPos the begin position from where the creation begins
         * @param statementBlocks the block program from which al the components are built
         * @return the resulting position of the last block that was built
         */
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

        /**
         * creates and adds all the components to the UI corresponding to a block program
         * @param statementBlocks the block program from which al the components are built
         * @return a builder which can build al the components of a block program
         */
        public Builder addBlockProgram(List<StatementBlock> statementBlocks){
            this.currentPos = addStatementList(currentPos, statementBlocks).plus(new WindowPosition(0, SPACE_BETWEEN_MAIN_AND_FUNCTION));
            return this;
        }

        /**
         * Builds the programBlocComponentBuilder
         * @return a programBlocComponentBuilder
         */
        public blockprogram build(){
            return new blockprogram(components,regionPositions);
        }
    }

}
