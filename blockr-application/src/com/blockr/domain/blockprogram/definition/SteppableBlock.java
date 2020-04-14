package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public interface SteppableBlock {
    void step(ExecutionCallStack executionCallStack);
}
