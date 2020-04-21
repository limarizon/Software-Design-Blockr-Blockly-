package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.ui.components.block.program.AttachLocation;

/**
 * MoveFromProgramArea command which is used for moving a block in the Program Area blockProgram.
 * This command will move the {@link #blockToMove} to a new postion as well as store its prior {@link #previousLocation},
 * which is needed for {@link #undoExecution()}.
 */
public class MoveFromProgramArea implements ProgramModificationCommand {
    private final ProgramLocation previousLocation;
    private ProgramBlock blockToMove;
    private final ProgramBlock destinationBlock;
    private final AttachLocation attachLocation;

    /**
     * Instantiates a new Move in the program area command.
     * The constructor will set its {@link #previousLocation} to {@link #blockToMove} its previous location, needed for {@link #undoExecution()}
     *
     * @param blockToMove      the block to move
     * @param destinationBlock the block to which {@link #blockToMove} will be linked to
     * @param attachLocation   the location at which the {@link #blockToMove} will be linked to the destinationBlock
     */
    public MoveFromProgramArea(ProgramBlock blockToMove, ProgramBlock destinationBlock, AttachLocation attachLocation) {
        previousLocation = blockToMove.getLocation();
        this.blockToMove = blockToMove;
        this.destinationBlock = destinationBlock;
        this.attachLocation = attachLocation;
    }

    @Override
    public boolean execute() {
        blockToMove.removeYourself();
        return destinationBlock.add(blockToMove, attachLocation);
    }

    @Override
    public void undoExecution() {
        blockToMove.removeYourself();
        previousLocation.undo(blockToMove);
    }
}
