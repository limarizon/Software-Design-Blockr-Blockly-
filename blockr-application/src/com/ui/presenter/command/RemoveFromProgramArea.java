package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;

public class RemoveFromProgramArea implements ProgramModificationCommand {
    private final ProgramBlock blockToRemove;
    private final ProgramLocation previousLocation;

    public RemoveFromProgramArea(ProgramBlock blockToRemove) {
        this.previousLocation = blockToRemove.getLocation();
        this.blockToRemove = blockToRemove;
    }

    @Override
    public boolean execute() {
        blockToRemove.removeYourself();
        return true;
    }

    @Override
    public void undoExecution() {
        previousLocation.restore(blockToRemove);
    }
}
