package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;

public class CommandFactory {
    private ProgramBlock concernedBlock;
    private boolean draggingStartedFromPalette;

    public boolean isDragging() {
        return concernedBlock != null;
    }

    public void startFromPalette(ProgramBlock blockToAdd) {
        this.concernedBlock = blockToAdd;
        this.draggingStartedFromPalette = true;
    }

    public void startFromProgramArea(ProgramBlock blockToMove) {
        this.concernedBlock = blockToMove;
        this.draggingStartedFromPalette = false;
    }

    public ProgramModificationCommand createAddOrMoveCommand(ProgramBlock destinationBlock, AttachLocation attachLocation) {
        ProgramModificationCommand command = new EmptyCommand();

        if(isDraggingFromPalette()){
            command = new AddFromPalette(concernedBlock, destinationBlock, attachLocation);
        }
        if(isDraggingWithinProgramArea()){
            command = new MoveFromProgramArea(concernedBlock, destinationBlock, attachLocation);
        }

        reset();
        return command;
    }

    public ProgramModificationCommand createRemovalCommand() {
        ProgramModificationCommand command = new EmptyCommand();
        if(isDragging()){
            command = new RemoveFromProgramArea(concernedBlock);
        }
        reset();
        return command;
    }

    private boolean isDraggingWithinProgramArea() {
        return !draggingStartedFromPalette && isDragging();
    }

    private boolean isDraggingFromPalette() {
        return draggingStartedFromPalette && isDragging();
    }

    private void reset() {
        this.draggingStartedFromPalette = false;
        this.concernedBlock = null;
    }
}
