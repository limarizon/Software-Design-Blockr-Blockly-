package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ControlFlowBlock;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;

public class AddFromPalette implements ProgramCreationCommand {
    private final ProgramBlock destinationBlock;
    private AttachLocation attachLocation;
    private final ProgramBlock blockToAdd;

    public AddFromPalette(ProgramBlock blockToAdd, ProgramBlock destinationBlock, AttachLocation attachLocation) {
        this.blockToAdd = blockToAdd;
        this.destinationBlock = destinationBlock;
        this.attachLocation = attachLocation;
    }

    @Override
    public void execute() {
        destinationBlock.add(blockToAdd, attachLocation);
    }

    @Override
    public void undoExecution() {
        //TODO: implement
    }
}
