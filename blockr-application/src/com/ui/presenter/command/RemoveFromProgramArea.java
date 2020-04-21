package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;

/**
 * The type Remove from program area.
 */
public class RemoveFromProgramArea implements ProgramModificationCommand {
    private final ProgramBlock blockToRemove;
    private final ProgramLocation previousLocation;

    /**
     * Instantiates a new Remove from program area.
     *
     * @param blockToRemove the block to remove
     */
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
        previousLocation.undo(blockToRemove);
    }
}
