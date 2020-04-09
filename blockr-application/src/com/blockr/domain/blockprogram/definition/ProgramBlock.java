package com.blockr.domain.blockprogram.definition;

import com.ui.components.block.program.AttachLocation;

public interface ProgramBlock {

    String getName();

    void add(StatementBlock blockToAdd, AttachLocation attachLocation);

    ProgramBlock copy();
}
