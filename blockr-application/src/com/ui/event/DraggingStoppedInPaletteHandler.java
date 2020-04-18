package com.ui.event;

import com.ui.presenter.ProgramCreator;

public class DraggingStoppedInPaletteHandler implements UiEventHandler<DraggingStoppedInPaletteHandler.Command, Void> {

    private final ProgramCreator programCreator;

    public DraggingStoppedInPaletteHandler(ProgramCreator programCreator) {
        this.programCreator = programCreator;
    }

    @Override
    public Void handle(Command command) {
        programCreator.handleDraggingStoppedForRemoval();
        return null;
    }

    public static class Command implements UiEvent<Void> {

        public Command() {
        }
    }
}
