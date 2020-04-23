package com.ui.event;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;
import com.ui.presenter.ProgramCreationController;

/**
 * The Dragging stopped handler.
 */
public class DraggingStoppedHandler implements UiEventHandler<DraggingStoppedHandler.Command, Void> {

    private final ProgramCreationController programCreationController;

    /**
     * Instantiates a new Dragging stopped handler.
     *
     * @param programCreationController the program creation controller needed in the UI for correct UI behaviour
     */
    public DraggingStoppedHandler(ProgramCreationController programCreationController) {
        this.programCreationController = programCreationController;
    }

    @Override
    public Void handle(Command command) {
        programCreationController.handleDraggingStoppedForAddingOrMoving(command.destinationBlock, command.attachLocation);
        return null;
    }

    /**
     * The command that will be used by the DraggingStoppedHandler(ProgramCreationController)
     */
    public static class Command implements UiEvent<Void> {
        private final ProgramBlock destinationBlock;
        /**
         * The Attach location.
         */
        public AttachLocation attachLocation;

        /**
         * Instantiates a new Command.
         *
         * @param destinationBlock the destination block
         * @param attachLocation   the attach location
         */
        public Command(ProgramBlock destinationBlock, AttachLocation attachLocation) {
            this.destinationBlock = destinationBlock;
            this.attachLocation = attachLocation;
        }
    }
}
