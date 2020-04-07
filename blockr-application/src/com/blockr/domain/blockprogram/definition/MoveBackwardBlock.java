package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public class MoveBackwardBlock implements StatementBlock {

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        if(!executionCallStack.getGameWorld().isFacingAWall()) {
            executionCallStack.getGameWorld().moveBackward();
            executionCallStack.pushOnUndoStack(this);
        }
        else {
            executionCallStack.pushOnUndoStack(new NothingBlock());
        }
    }

    @Override
    public void nonStatementListBlockStep(ExecutionCallStack executionCallStack) {
        if(!executionCallStack.getGameWorld().isFacingAWall()) {
            executionCallStack.getGameWorld().moveBackward();
            executionCallStack.pushOnUndoStack(this);
        }
        else {
            executionCallStack.pushOnUndoStack(new NothingBlock());
        }
    }


    @Override
    public String getName() {
        return "Move Backward";
    }

    @Override
    public String toString() {
        return MoveBackwardBlock.class.getSimpleName();
    }

    @Override
    public StatementBlock invert(){
        return new MoveForwardBlock();
    }
}

