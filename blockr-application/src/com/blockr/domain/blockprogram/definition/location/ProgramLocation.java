package com.blockr.domain.blockprogram.definition.location;

import com.blockr.domain.blockprogram.definition.ProgramBlock;

/**
 * @author
 * @version
 */
public interface ProgramLocation {
    /**
     * a program block will be added to this location
     * @param programBlock the program block to be added
     */
    void undo(ProgramBlock programBlock);
}
