package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.ui.components.block.program.AttachLocation;

/**
 * This interface contains the description of a block which contains a StatementBlock
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public interface ContainingStatementBlock extends SteppableBlock{
    /**
     * adds a block to (another) location in the statementList block  in the block program
     * @param blockToAdd the block which is added from the the referencedBlock
     * @param referencedBlock the block where the blockToAdd was added previously
     * @param attachLocation the location on this block where the blockToAdd will be added
     */
    void addToStatementList(StatementBlock blockToAdd, StatementBlock referencedBlock, AttachLocation attachLocation);

    /**
     * adds a block to the empty body of this containingStatement block in the block program
     * @param block the block that is added to the statementList
     * @param lineNumber the index in the statementlist where it will be added
     */
    void addToStatementList(StatementBlock block, int lineNumber);

    /**
     * gives information of the of the location of this program block in a block program
     * @param statementBlock a StatementBlock instance
     * @return an interface which provides methods to retrieve programblock location information
     */
    ProgramLocation getLocation(StatementBlock statementBlock);

    /**
     * gives a statementlist block object which is contained in this block
     * @return the body of this block
     */
    StatementListBlock getStatementListBlock();

    /**
     * removes a block from the statementList block of this block
     * @param blockToRemove the block that will be removed
     */
    void removeFromStatementList(StatementBlock blockToRemove);

    /**
     * gives information of whether a linenumber is the last statement in the statementList of this block
     * @param lineNumber used for checking with the length of the statementList block this block contains
     * @param gameWorld used for checking the amount of block inside the statementList of this block
     * @return a boolean indicating whether this was the last statement in the statementblock
     */
    boolean wasLastStatement(int lineNumber, GameWorldApi gameWorld);


}
