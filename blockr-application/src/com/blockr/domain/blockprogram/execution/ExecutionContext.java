package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.ContainingStatementBlock;
import com.blockr.domain.blockprogram.definition.ProgramBlock;

/**
 * @author
 * @version
 */
public class ExecutionContext {
    /**
     * A ContainingStatementBlock in this object
     */
    private ContainingStatementBlock statementContainer;
    /**
     * The index in the StatemenListblock contained in the ContainingStatementBlock of the actionblock to be
     * executed next
     */
    private int lineNumber;
    /**
     * The game world API on which the ActionBlocks are performed
     */
    private GameWorldApi gameWorld;

    /**
     * constructor initialising the attributes of this class
     * @param statementListBlock StatemenListblock contained in the ContainingStatementBlock
     * @param lineNumber The index in the StatemenListblock contained in the ContainingStatementBlock of the actionblock to be executed next
     * @param gameWorld The gameworld API on which the ActionBlocks are performed
     */
    public ExecutionContext(ContainingStatementBlock statementListBlock, int lineNumber, GameWorldApi gameWorld) {
        this.gameWorld = gameWorld;
        this.statementContainer = statementListBlock;
        this.lineNumber = lineNumber;
    }

    /**
     * Gets the gameworld API on which the ActionBlocks are performed
     * @return The gameworld API on which the ActionBlocks are performed
     */
    public GameWorldApi getGameWorld() {
        return gameWorld;
    }

    /**
     * Get the ContainingStatementBlock in this object
     * @return the ContainingStatementBlock in this object
     */
    public ContainingStatementBlock getStatementContainer() {
        return statementContainer;
    }

    /**
     * Get the index in the StatemenListblock contained in the ContainingStatementBlock of the actionblock to be
     * executed next
     * @return the index in the StatemenListblock contained in the ContainingStatementBlock of the actionblock to be
     * executed next
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Converts the object to String format
     * @return the converted object representation
     */
    @Override
    public String toString() {
        return lineNumber + " - " + statementContainer.toString();
    }

    /**
     *  Get the current ProgramBlock which is the sourceBlock or contains the sourceBlock on the current
     *  lineNumber
     * @param source a program block
     * @param <B> ProgramBlock subtype
     * @return the current ProgramBlock which is the sourceBlock or contains the sourceBlock on the current
     */
    public <B extends ProgramBlock> boolean isCurrentStep(B source) {
        return getStatementContainer().getStatementListBlock().getStatements().get(lineNumber) == source;
    }
}
