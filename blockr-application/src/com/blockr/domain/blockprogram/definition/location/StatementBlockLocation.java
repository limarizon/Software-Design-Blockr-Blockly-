package com.blockr.domain.blockprogram.definition.location;

import com.blockr.domain.blockprogram.definition.ContainingStatementsBlock;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;

/**
 * This class contains the description of the location of a statement block inside the block program
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public class StatementBlockLocation implements ProgramLocation {
    /**
     * The parent which contains a statement block from this location
     */
    private final ContainingStatementsBlock parent;
    /**
     * The linenumber of a statementListBlock where a statement block resides
     */
    private final int lineNumber;

    /**
     * Contructor that sets the attribute of this class
     * @param parent The parent which contains a statement block from this location
     * @param lineNumber The linenumber of a statementListBlock where a statement block resides
     */
    public StatementBlockLocation(ContainingStatementsBlock parent, int lineNumber) {
        this.parent = parent;
        this.lineNumber = lineNumber;
    }

    /**
     * A statement block will be attached to the parent of this location
     * @param block a block that will be attached as a statementblock to a parent
     */
    @Override
    public void undo(ProgramBlock block) {
        if(block.isStatementBlock()){
            parent.addToStatementList((StatementBlock) block, lineNumber);
        }
    }
}
