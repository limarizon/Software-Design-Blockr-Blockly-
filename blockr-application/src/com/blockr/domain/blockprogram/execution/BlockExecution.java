package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.StatementListBlock;

public class BlockExecution {
    private final ExecutionCallStack executionCallStack;
    private final StatementListBlock statementListBlock;

    public BlockExecution(StatementListBlock statementListBlock, GameWorldApi gameWorld) {
        this.executionCallStack = new ExecutionCallStack(gameWorld);
        this.statementListBlock = statementListBlock;
        executionCallStack.pushFrame(statementListBlock);
    }

    public void step(){
        this.executionCallStack.step();
    }

    public void reset(){
        this.executionCallStack.reset();
        executionCallStack.pushFrame(statementListBlock);
    }

    public void undoStep() {

    }
}
