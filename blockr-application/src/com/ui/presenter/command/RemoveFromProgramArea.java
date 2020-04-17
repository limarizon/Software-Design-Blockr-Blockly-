package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;

public class RemoveFromProgramArea implements ProgramCreationCommand {
    private final ProgramBlock blockToRemove;
    private final ProgramBlock destinationBlock;
    private boolean hasDestinationBlock;
    private AttachLocation attachLocation;

    public RemoveFromProgramArea(ProgramBlock blockToRemove, ProgramBlock destinationBlock, AttachLocation attachLocation) {
        this.blockToRemove = blockToRemove;
        this.destinationBlock = destinationBlock;
        this.hasDestinationBlock = (destinationBlock==null);
        this.attachLocation = attachLocation;
    }

    @Override
    public boolean isOriginalModification(){
        return false;
    }


    @Override
    public void execute() {
        if(!hasDestinationBlock)
            blockToRemove.removeYourself();
    }

    @Override
    public void undoExecution() {
        destinationBlock.add(blockToRemove, attachLocation);
    }
}
