package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public class NothingBlock implements StatementBlock {

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushOnUndoStack(this);
    }

    @Override
    public void nonStatementListBlockStep(ExecutionCallStack executionCallStack) {
        executionCallStack.pushOnUndoStack(this);
    }

    @Override
    public String getName() {
        return "Nothing Block";
    }

    @Override
    public String toString() {
        return NothingBlock.class.getSimpleName();
    }

    @Override
    public StatementBlock invert(){
        return new NothingBlock();
    }
}

