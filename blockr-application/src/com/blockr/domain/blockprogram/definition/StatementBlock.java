package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public interface StatementBlock extends ProgramBlock {

    void step(ExecutionCallStack executionCallStack);

    default boolean isControlFlow(){return false;}
}
