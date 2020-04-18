package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;

public class RemoveFromProgramArea implements ProgramModificationCommand {
    private final ProgramBlock blockToRemove;

    public RemoveFromProgramArea(ProgramBlock blockToRemove) {
        this.blockToRemove = blockToRemove;
    }

    @Override
    public boolean isOriginalModification(){
        return false;
    }


    @Override
    public void execute() {
        blockToRemove.removeYourself();
    }

    @Override
    public void undoExecution() {
    }
}
