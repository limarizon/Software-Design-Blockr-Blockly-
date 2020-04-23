package com.ui.event;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.presenter.ProgramCreationController;

/**
 * The Dragging started from program area handler.
 */
public class DraggingStartedFromProgramAreaHandler implements UiEventHandler<DraggingStartedFromProgramAreaHandler.Command, Void> {

    private final ProgramCreationController programCreationController;

    /**
     * Instantiates a new Dragging started from program area handler.
     *
     * @param programCreationController the program creation controller needed in the UI for correct UI behaviour
     */
    public DraggingStartedFromProgramAreaHandler(ProgramCreationController programCreationController) {
        this.programCreationController = programCreationController;
    }

    @Override
    public Void handle(Command draggingStarted) {
        programCreationController.startDraggingFromProgramArea(draggingStarted.blockToAdd);
        return null;
    }

    /**
     * The command that will be used by the DraggingStartedFromProgramAreaHandler(ProgramCreationController)
     */
    public static class Command implements UiEvent<Void> {
        private final ProgramBlock blockToAdd;

        /**
         * Instantiates a new Command.
         *
         * @param blockToAdd the block to add
         */
        public Command(ProgramBlock blockToAdd) {
            this.blockToAdd = blockToAdd;
        }
    }
}
