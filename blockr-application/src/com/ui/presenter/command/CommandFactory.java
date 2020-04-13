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


    //Mogelijke acties :
    // Vanuit palette :
        // singular statement (=action)
        // ---
        // toevoegen aan program area = in top control block op bepaalde positie (laatste)
        // toevoegen onder een blok = in parent control block op bepaalde positie (laatste of op bepaalde locatie)
        //
        // --
        // predicate
        // ---
        // toevoegen aan controlflowblock met predicate
        // --
        // controlflowblock
        // --
        // toevoegen aan ander controlflow block (op bepaalde positie)
    public ProgramCreationCommand createCommand(ProgramBlock destinationBlock, AttachLocation attachLocation) {
        if(isDraggingFromPalette()){
            ProgramCreationCommand command = new AddFromPalette(blockToAdd, destinationBlock, attachLocation);
            reset();
            return command;
        }

        if(isDraggingWithinProgramArea()){
            ProgramCreationCommand command = new MoveFromProgramArea(blockToAdd, destinationBlock, attachLocation);
            reset();
            return command;
        }
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
