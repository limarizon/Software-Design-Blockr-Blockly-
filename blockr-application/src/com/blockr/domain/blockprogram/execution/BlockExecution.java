package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.StatementListBlock;

public class BlockExecution {
    private final ExecutionCallStack executionCallStack;

    public BlockExecution(StatementListBlock statementListBlock, GameWorldApi gameWorld) {
        this.executionCallStack = new ExecutionCallStack(gameWorld);
        executionCallStack.pushFrame(statementListBlock);
    }

    public void step(){
        this.executionCallStack.step();
    }

    public void reset(){
        this.executionCallStack.reset();
    }

    public void undoStep() {

    }
}
