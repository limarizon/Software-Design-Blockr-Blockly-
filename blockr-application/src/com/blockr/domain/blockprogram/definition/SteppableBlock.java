package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public interface SteppableBlock {
    /**
     * steps into a certain block object which implements this interface
     * @param executionCallStack the execution stack on which the block is pushed when to be executed and popped after execution of the block
     */
    void step(ExecutionCallStack executionCallStack);
}
