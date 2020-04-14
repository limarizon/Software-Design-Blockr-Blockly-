package com.blockr.domain.blockprogram.definition;

import com.ui.components.block.program.AttachLocation;

public interface ProgramBlock {
    String getName();
    ProgramBlock copy();

    void add(ProgramBlock blockToAdd, AttachLocation attachLocation);
    void removeYourself();

    boolean isStatementBlock();
    default boolean isNot() {return false;};
    default boolean isGamePredicateBlock() { return false;};


}
