package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.ui.components.block.program.AttachLocation;

public class MoveFromProgramArea implements ProgramModificationCommand {
    private final ProgramLocation previousLocation;
    private ProgramBlock blockToMove;
    private final ProgramBlock destinationBlock;
    private final AttachLocation attachLocation;

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
