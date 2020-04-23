package com.blockr.domain.blockprogram.definition.location;

import com.blockr.domain.blockprogram.definition.ProgramBlock;

/**
 * This class contains the description of the location of a program block inside the block program
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public interface ProgramLocation {
    /**
     * a program block will be added to this location
     * @param programBlock the program block to be added
     */
    void undo(ProgramBlock programBlock);
}
