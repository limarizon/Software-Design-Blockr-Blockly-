package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public class MoveForwardBlock implements StatementBlock {

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        if(!executionCallStack.getGameWorld().isFacingAWall()) {
            executionCallStack.getGameWorld().moveForward();
            executionCallStack.pushOnUndoStack(this);
        }
    }

    @Override
    public String getName() {
        return "Move Forward";
    }

    @Override
    public String toString() {
        return MoveForwardBlock.class.getSimpleName();
    }

    public StatementBlock invert(){
        return new MoveBackwardBlock();
    }
}
