package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;

public class MoveFromProgramArea implements ProgramModificationCommand {
    private ProgramBlock blockToAdd;
    private final ProgramBlock destinationBlock;
    private final AttachLocation attachLocation;

    public MoveFromProgramArea(ProgramBlock blockToAdd, ProgramBlock destinationBlock, AttachLocation attachLocation) {
        this.blockToAdd = blockToAdd;
        this.destinationBlock = destinationBlock;
        this.attachLocation = attachLocation;
    }

    @Override
    public boolean isOriginalModification(){
        return false;
    }

    @Override
    public void execute() {
        blockToAdd.removeYourself();
        destinationBlock.add(blockToAdd, attachLocation);
    }

    @Override
    //TODO: needs more information regarding original connection
    public void undoExecution() {

    }
}
