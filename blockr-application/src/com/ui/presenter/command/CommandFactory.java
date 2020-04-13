package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;

public class CommandFactory {
    private ProgramBlock blockToAdd;
    private boolean draggingStartedFromPalette;

    public boolean isDragging() {
        return blockToAdd != null;
    }

    public void startFromPalette(ProgramBlock blockToAdd) {
        this.blockToAdd = blockToAdd;
        this.draggingStartedFromPalette = true;
    }


    public void startFromProgramArea(ProgramBlock blockToAdd) {
        this.blockToAdd = blockToAdd;
        this.draggingStartedFromPalette = false;
    }

    public ProgramCreationCommand createCommand(ProgramBlock destinationBlock, AttachLocation attachLocation) {
        if(isDraggingFromPalette()){
            //TODO : eventueel toevoegen van
            ProgramCreationCommand command = new AddFromPalette(blockToAdd, destinationBlock, attachLocation);
            reset();
            return command;
        }

        if(isDraggingWithinProgramArea()){
            ProgramCreationCommand command = new MoveFromProgramArea(blockToAdd, destinationBlock, attachLocation);
            reset();
            return command;
        }

        //TODO: draggen van ProgramArea naar Palette : remove
        return new EmptyCommand();
    }

    private boolean isDraggingWithinProgramArea() {
        return !draggingStartedFromPalette && blockToAdd !=null;
    }

    private boolean isDraggingFromPalette() {
        return draggingStartedFromPalette && blockToAdd !=null;
    }

    private void reset() {
        this.draggingStartedFromPalette = false;
        this.blockToAdd = null;
    }

}
