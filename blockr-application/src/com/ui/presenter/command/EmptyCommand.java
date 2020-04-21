package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;

/**
 * The EmptyCommand used for initiating any type of command in the UI. If no valid command can be assigned
 * in the CommandFactory, we will return an EmptyCommand resulting in no changes in the UI.
 * @see CommandFactory#createAddOrMoveCommand(ProgramBlock, AttachLocation)
 * @see CommandFactory#createRemovalCommand()
 */
public class EmptyCommand implements ProgramModificationCommand {
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public void undoExecution() {

    }
}
