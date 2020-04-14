package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.ContainingStatementBlock;
import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.blockr.domain.blockprogram.definition.ProgramBlock;

public class ExecutionContext {
    private ContainingStatementBlock controlFlowBlock;
    private int lineNumber;
    private GameWorldApi gameWorld;

    public ExecutionContext(ContainingStatementBlock statementListBlock, int lineNumber, GameWorldApi gameWorld) {
        this.gameWorld = gameWorld;
        this.controlFlowBlock = statementListBlock;
        this.lineNumber = lineNumber;
    }

    public GameWorldApi getGameWorld() {
        return gameWorld;
    }

    public ContainingStatementBlock getControlFlow() {
        return controlFlowBlock;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return lineNumber + " - " + controlFlowBlock.toString();
    }

    public <B extends ProgramBlock> boolean isCurrentStep(B source) {
        return getControlFlow().getStatementListBlock().getStatements().get(lineNumber) == source;
    }
}
