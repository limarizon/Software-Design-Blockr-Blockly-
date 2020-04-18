package com.blockr.domain.blockprogram.definition.location;

import com.blockr.domain.blockprogram.definition.ProgramBlock;

public interface ProgramLocation {
    void undo(ProgramBlock programBlock);
}
