package com.ui.event;

import com.ui.presenter.ProgramCreationController;

public class DraggingStoppedInPaletteHandler implements UiEventHandler<DraggingStoppedInPaletteHandler.Command, Void> {

    private final ProgramCreationController programCreationController;

    public DraggingStoppedInPaletteHandler(ProgramCreationController programCreationController) {
        this.programCreationController = programCreationController;
    }

    @Override
    public Void handle(Command command) {
        programCreationController.handleDraggingStoppedForRemoval();
        return null;
    }

    public static class Command implements UiEvent<Void> {

        public Command() {
        }
    }
}
