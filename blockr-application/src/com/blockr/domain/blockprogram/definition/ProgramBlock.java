package com.blockr.domain.blockprogram.definition;

import com.ui.components.block.AttachLocation;

public interface ProgramBlock {

    String getName();

    void add(StatementBlock blockToAdd, AttachLocation attachLocation);
}
