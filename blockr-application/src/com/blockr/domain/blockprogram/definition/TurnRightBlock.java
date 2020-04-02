package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public class TurnRightBlock implements StatementBlock {

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.getGameWorld().turnRight();
    }

    @Override
    public String getName() {
        return "Turn Right";
    }

    @Override
    public String toString() {
        return TurnRightBlock.class.getSimpleName();
    }
}
