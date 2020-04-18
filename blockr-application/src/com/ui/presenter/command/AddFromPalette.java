package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;

public class AddFromPalette implements ProgramModificationCommand {
    private final ProgramBlock destinationBlock;
    private AttachLocation attachLocation;
    private final ProgramBlock blockToAdd;

    public AddFromPalette(ProgramBlock blockToAdd, ProgramBlock destinationBlock, AttachLocation attachLocation) {
        this.blockToAdd = blockToAdd;
        this.destinationBlock = destinationBlock;
        this.attachLocation = attachLocation;
    }

    @Override
    public boolean execute() {
        return destinationBlock.add(blockToAdd, attachLocation);
    }

    @Override
    public void undoExecution() {
        blockToAdd.removeYourself();
    }
}
