package com.ui.components.block.program;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.blockr.domain.blockprogram.definition.NotBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;
import com.blockr.domain.blockprogram.definition.StatementListBlock;
import com.ui.Component;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.WindowRegion;
import com.ui.components.block.graphics.BlockSizes;

import java.util.ArrayList;
import java.util.List;

public class ProgramBlockComponentBuilder {

    public static final int START_POSITION = 30;
    public static final int SPACE_BETWEEN = 0;

    private final java.util.List<ProgramBlockComponent> components = new ArrayList<>();
    private final List<WindowRegion> regionPositions = new ArrayList<>();

    public ProgramBlockComponentBuilder(StatementListBlock programDefinition, GameState state, UiMediator mediator) {
        var rootPos = new WindowPosition(START_POSITION,START_POSITION);

        //TODO: meer gecompliceerde programma's uittekenen hier
        BuildBlockComponent(rootPos,programDefinition,state,mediator);
    }

    private void BuildBlockComponent(WindowPosition newRoot,StatementListBlock programDefinition, GameState state, UiMediator mediator){
        var rootPos = newRoot;
        for(StatementBlock statementBlock : programDefinition.getStatements() ){
            if(! statementBlock.isControlFlow()){
                ProgramStatementBlockComponent blockComponent = new ProgramStatementBlockComponent(state, statementBlock, mediator, rootPos);
                components.add(blockComponent);
                regionPositions.add(new WindowRegion(rootPos.getX(), rootPos.getY(), rootPos.getX() + blockComponent.getWidth(), rootPos.getY() + blockComponent.getHeight()));
                rootPos = rootPos.plus(new WindowPosition(0,SPACE_BETWEEN + blockComponent.getHeight()));
            }
            else{
                var controlFlowBlock = (ControlFlowBlock) statementBlock;
                ProgramControlFlowBlockComponent blockComponent = new ProgramControlFlowBlockComponent(state, controlFlowBlock, mediator, rootPos);
                components.add(blockComponent);
                regionPositions.add(new WindowRegion(rootPos.getX(), rootPos.getY(), rootPos.getX() + blockComponent.getWidth(), rootPos.getY() + blockComponent.getHeight()));
                var newPos = rootPos.plus(new WindowPosition(BlockSizes.CONTROL_FLOW_INNER_START,SPACE_BETWEEN + BlockSizes.CONDITION_BLOCK_HEIGHT));
                //recursief de bodycomponenten maken met de juist beginpositie
                BuildBlockComponent(newPos,controlFlowBlock.getStatementListBlock(),state,mediator);
                var pred = controlFlowBlock.getPredicate();
                if(pred!=null){
                    //het predicaat maken en positie bepalen
                    //TODO een cfb kan meerdere predicaten hebben, ook nog in domain veranderen
                    var pos = rootPos.plus(new WindowPosition(BlockSizes.BLOCK_WIDTH,0));
                    ProgramPredicateBlockComponent predicate = new ProgramPredicateBlockComponent(state, pred, mediator, pos);
                    components.add(predicate);
                    regionPositions.add(new WindowRegion(pos.getX(), pos.getY(), pos.getX() + predicate.getWidth(), pos.getY() + predicate.getHeight()));
                    if(pred.hasPredicate()){
                        var posPred = pos.plus(new WindowPosition(BlockSizes.CONDITION_BLOCK_WIDTH,0));
                        ProgramPredicateBlockComponent predicate1 = new ProgramPredicateBlockComponent(state, ((NotBlock)pred).getPredicateToNegate(), mediator, posPred);
                        components.add(predicate1);
                        regionPositions.add(new WindowRegion(posPred.getX(), posPred.getY(), posPred.getX() + predicate1.getWidth(), posPred.getY() + predicate1.getHeight()));
                    }
                }
                rootPos = rootPos.plus(new WindowPosition(0,SPACE_BETWEEN + blockComponent.getHeight()));
            }
        }

    }

    public List<ProgramBlockComponent> getComponents() {
        return components;
    }

    //TODO : binnen gecompliceerde programma's ook nog het juiste component kunnen selecteren (onMouseUp)
    public WindowRegion getChildRegion(Component child) {
        int index = components.indexOf(child);
        if(index == -1) return null;
        return regionPositions.get(index);
    }
}
