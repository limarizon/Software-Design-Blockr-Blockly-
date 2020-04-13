package com.blockr.domain.blockprogram.definition;

import com.ui.components.block.program.AttachLocation;

public interface ProgramBlock {

    String getName();

    ProgramBlock copy();

    void add(ProgramBlock blockToAdd, AttachLocation attachLocation);

    boolean isStatementBlock();


}
