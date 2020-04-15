package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.ContainingStatementBlock;
import com.blockr.domain.blockprogram.definition.ProgramBlock;

public class ExecutionContext {
    private ContainingStatementBlock statementContainer;
    private int lineNumber;
    private GameWorldApi gameWorld;

    public ExecutionContext(ContainingStatementBlock statementListBlock, int lineNumber, GameWorldApi gameWorld) {
        this.gameWorld = gameWorld;
        this.statementContainer = statementListBlock;
        this.lineNumber = lineNumber;
    }

    public GameWorldApi getGameWorld() {
        return gameWorld;
    }

    public ContainingStatementBlock getStatementContainer() {
        return statementContainer;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return lineNumber + " - " + statementContainer.toString();
    }

    public <B extends ProgramBlock> boolean isCurrentStep(B source) {
        return getStatementContainer().getStatementListBlock().getStatements().get(lineNumber) == source;
    }
}
