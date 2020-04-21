package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public class WhileBlock extends ControlFlowBlock {
    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushFrame(this);
        if(isPredicateSatisfied(executionCallStack.getGameWorld())){
            statementListBlock.step(executionCallStack);
        }
        if(! executionCallStack.isCurrentFrame(statementListBlock)){
            if(isPredicateSatisfied(executionCallStack.getGameWorld())){
                executionCallStack.pushFrame(statementListBlock);
            }else{
                executionCallStack.dropFrame();
            }
        }
    }

    @Override
    public StatementBlock copy() {
        return new WhileBlock();
    }

    @Override
    public String getName() {
        return "While";
    }

    @Override
    public boolean wasLastStatement(int lineNumber, GameWorldApi gameWorld) {
        if(statementListBlock.wasLastStatement(lineNumber, gameWorld)){
            return !isPredicateSatisfied(gameWorld);
        }
        return false;
    }

    @Override
    public String toString() {
        return WhileBlock.class.getSimpleName() + " " +  statementListBlock.toString();
    }
}
