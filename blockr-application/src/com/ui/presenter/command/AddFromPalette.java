package com.ui.presenter.command;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;

/**
 * The Add from Palette Command.
 * This command can be executed and also undone.
 */
public class AddFromPalette implements ProgramModificationCommand {
    private final ProgramBlock destinationBlock;
    private AttachLocation attachLocation;
    private final ProgramBlock blockToAdd;

    /**
     * Instantiates a new Add from palette command
     *
     * @param blockToAdd       the block to add
     * @param destinationBlock the destination block to which the blockToAdd will connect to
     * @param attachLocation   the attach location to which the blockToAdd will connect at
     */
    public AddFromPalette(ProgramBlock blockToAdd, ProgramBlock destinationBlock, AttachLocation attachLocation) {
        this.blockToAdd = blockToAdd;
        this.destinationBlock = destinationBlock;
        this.attachLocation = attachLocation;
    }

    @Override
    public boolean execute() {
        return destinationBlock.add(blockToAdd, attachLocation);
    }

    @Override
    public void undoExecution() {
        blockToAdd.removeYourself();
    }
}
