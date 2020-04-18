package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.ui.components.block.program.AttachLocation;

public interface ProgramBlock {
    String getName();
    ProgramBlock copy();

    void add(ProgramBlock blockToAdd, AttachLocation attachLocation);
    void removeYourself();

    boolean isStatementBlock();
    default boolean isPredicateBlock() { return false;}

    ProgramLocation getLocation();


}
