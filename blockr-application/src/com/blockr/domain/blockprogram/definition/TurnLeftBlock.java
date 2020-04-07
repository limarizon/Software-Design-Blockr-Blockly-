package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public class TurnLeftBlock implements StatementBlock {

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.getGameWorld().turnLeft();
        executionCallStack.pushOnUndoStack(this);
    }

    @Override
    public String getName() {
        return "Turn Left";
    }

    @Override
    public String toString() {
        return TurnLeftBlock.class.getSimpleName();
    }

    public StatementBlock invert(){
        return new TurnRightBlock();
    }
}
