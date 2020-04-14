package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;

public class MoveFromProgramArea implements ProgramCreationCommand {
    private ProgramBlock blockToAdd;
    private final ProgramBlock destinationBlock;
    private final AttachLocation attachLocation;

    public MoveFromProgramArea(ProgramBlock blockToAdd, ProgramBlock destinationBlock, AttachLocation attachLocation) {
        this.blockToAdd = blockToAdd;
        this.destinationBlock = destinationBlock;
        this.attachLocation = attachLocation;
    }



    @Override
    public void execute() {
        blockToAdd.removeYourself();
        destinationBlock.add(blockToAdd, attachLocation);
    }

    @Override
    public void undoExecution() {

    }
}
