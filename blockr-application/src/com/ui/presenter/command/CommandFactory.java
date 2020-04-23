package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;

/**
 * The CommandFactory is the class in which each type of command from the UI can be produced.
 * Depending on the I/O in the PaletteArea and ProgramArea, we will call its respectable command.
 * This class is used in the ProgramCreationController to create commands for the blockModifications.
 */
public class CommandFactory {
    private ProgramBlock concernedBlock;
    private boolean draggingStartedFromPalette;

    /**
     * Is a boolean which states if the I/O is in a dragging state.
     *
     * @return true if {@link #concernedBlock} is not null. false if there is no {@link #concernedBlock}.
     */
    public boolean isDragging() {
        return concernedBlock != null;
    }

    /**
     * Method which will assign the blockToAdd to {@link #concernedBlock} AND set draggingStartedFromPalette = true.
     *
     * @param blockToAdd the block to add
     */
    public void startFromPalette(ProgramBlock blockToAdd) {
        this.concernedBlock = blockToAdd;
        this.draggingStartedFromPalette = true;
    }

    /**
     * Method which will assign the blockToMove to {@link #concernedBlock} AND set {@link #draggingStartedFromPalette} = true.
     *
     * @param blockToMove the block to move
     */
    public void startFromProgramArea(ProgramBlock blockToMove) {
        this.concernedBlock = blockToMove;
        this.draggingStartedFromPalette = false;
    }

    /**
     * Create addition or reordering of the Program Area block command.
     *
     * @param destinationBlock the block to which {@link #concernedBlock} will be linked to
     * @param attachLocation   the location at which the {@link #concernedBlock} will be linked to the destinationBlock
     * @return the program modification command
     */
    public ProgramModificationCommand createAddOrMoveCommand(ProgramBlock destinationBlock, AttachLocation attachLocation) {
        ProgramModificationCommand command = new EmptyCommand();

        if(isDraggingFromPalette()){
            command = new AddFromPalette(concernedBlock, destinationBlock, attachLocation);
        }
        if(isDraggingWithinProgramArea() && (concernedBlock != destinationBlock)){
            command = new MoveFromProgramArea(concernedBlock, destinationBlock, attachLocation);
        }

        reset();
        return command;
    }

    /**
     * Create removal from the Program Area command.
     *
     * @return the program modification command
     */
    public ProgramModificationCommand createRemovalCommand() {
        ProgramModificationCommand command = new EmptyCommand();
        if(isDragging() && !isDraggingFromPalette()){
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

    /**
     * Method which resets {@link #isDraggingFromPalette()} to false.
     * {@link #concernedBlock} will be reset to null value.
     */
    private void reset() {
        this.draggingStartedFromPalette = false;
        this.concernedBlock = null;
    }
}
