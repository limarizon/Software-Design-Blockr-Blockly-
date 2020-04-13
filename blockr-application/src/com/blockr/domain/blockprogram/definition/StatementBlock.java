package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public interface StatementBlock extends ProgramBlock<ControlFlowBlock> {

    void step(ExecutionCallStack executionCallStack);

    default boolean isControlFlow(){return false;}

    StatementBlock copy();

    void setParent(ControlFlowBlock parent);

    @Override
    default boolean isStatementBlock(){return true;};
}
