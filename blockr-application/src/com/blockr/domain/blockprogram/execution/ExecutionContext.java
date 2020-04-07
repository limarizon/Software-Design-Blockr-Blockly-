package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;

public class ExecutionContext {
    private ControlFlowBlock controlFlowBlock;
    private int lineNumber;
    private GameWorldApi gameWorld;

    public ExecutionContext(ControlFlowBlock statementListBlock, int lineNumber, GameWorldApi gameWorld) {
        this.gameWorld = gameWorld;
        this.controlFlowBlock = statementListBlock;
        this.lineNumber = lineNumber;
    }

    public GameWorldApi getGameWorld() {
        return gameWorld;
    }

    public ControlFlowBlock getControlFlow() {
        return controlFlowBlock;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return lineNumber + " - " + controlFlowBlock.toString();
    }

}
