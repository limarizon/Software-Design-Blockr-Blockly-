package com.ui.event;

import com.blockr.domain.GameState;
import com.ui.presenter.ProgramCreationController;

/**
 * The Dragging stopped in palette handler.
 */
public class DraggingStoppedInPaletteHandler implements UiEventHandler<DraggingStoppedInPaletteHandler.Command, Void> {

    private final ProgramCreationController programCreationController;

    /**
     * Instantiates a new Dragging stopped in palette handler.
     *
     * @param programCreationController the program creation controller needed in the UI for correct UI behaviour
     */
    public DraggingStoppedInPaletteHandler(ProgramCreationController programCreationController) {
        this.programCreationController = programCreationController;
    }

    @Override
    public Void handle(Command command) {
        programCreationController.handleDraggingStoppedForRemoval();
        return null;
    }

    /**
     * The command that will be used by {@link #DraggingStoppedInPaletteHandler(ProgramCreationController)} (GameState)}.
     */
    public static class Command implements UiEvent<Void> {

        /**
         * Instantiates a new Command.
         */
        public Command() {
        }
    }
}
