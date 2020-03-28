package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public interface StatementBlock {

    void step(ExecutionCallStack executionCallStack);

}
