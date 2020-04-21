package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public class IfBlock extends ControlFlowBlock{

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        if(isPredicateSatisfied(executionCallStack.getGameWorld())){
            statementListBlock.step(executionCallStack);
        }
    }

    @Override
    public StatementBlock copy() {
        return new IfBlock();
    }

    @Override
    public String getName() {
        return "If";
    }

    @Override
    public String toString() {
        return IfBlock.class.getSimpleName() + statementListBlock.toString();
    }

    @Override
    public boolean wasLastStatement(int lineNumber, GameWorldApi gameWorld) {
        return statementListBlock.wasLastStatement(lineNumber, gameWorld);
    }

}
