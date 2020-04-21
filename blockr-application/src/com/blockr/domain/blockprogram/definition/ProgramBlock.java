package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.ui.components.block.program.AttachLocation;

/**
 * This interface contains the description of a program block used in the block program
 * @author Simon Van Campenhout & Liam Volckerick
 * @version 2.0
 */
public interface ProgramBlock {
    /**
     * gives the name of the program block
     * @return the name of the program block as String
     */
    String getName();

    /**
     * makes a copy of this programBlock object
     * @return a ProgramBlock which is the copy of ProgramBlock to which this method is called
     */
    ProgramBlock copy();

    /**
     * attaches a program block to the program block to which this method is called on a location specified in the arguments
     * and indicates whether this attachment has succeeded
     * @param blockToAdd the program block to which this program block is added
     * @param attachLocation the location on the blockToAdd program block on which the block is added
     * @return  a boolean indication whether the attachment has succeeded
     */
    boolean add(ProgramBlock blockToAdd, AttachLocation attachLocation);

    /**
     * this program block will remove himself by calling this function from all the parent blocks and other connections
     */
    void removeYourself();

    /**
     * gives more specific information about the possible other interfaces the block implements
     * @return  a boolean indication whether it's a statementBlock interface implementation or not
     */
    boolean isStatementBlock();

    /**
     * gives more specific information about the possible other interfaces the block implements
     * @return a boolean indication whether it's a predicate interface implementation or not
     */
    default boolean isPredicateBlock() { return false;}

    /**
     * gives information of the of the location of this program block in a block program
     * @return an interface which provides methods to retrieve programblock location information
     */
    ProgramLocation getLocation();

    /**
     * gives information of the amount of blocks that are used in the block program
     * @return the number of blocks used int the block program where this program block is residing
     */
    int countBlocks();
}
