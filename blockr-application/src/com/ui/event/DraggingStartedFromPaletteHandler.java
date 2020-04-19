package com.ui.event;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.presenter.ProgramCreationController;

public class DraggingStartedFromPaletteHandler implements UiEventHandler<DraggingStartedFromPaletteHandler.Command, Void> {

    private final ProgramCreationController programCreationController;

    public DraggingStartedFromPaletteHandler(ProgramCreationController programCreationController) {
        this.programCreationController = programCreationController;
    }

    @Override
    public Void handle(Command draggingStarted) {
        programCreationController.startDraggingFromPalette(draggingStarted.blockToAdd);
        return null;
    }

    public static class Command implements UiEvent<Void> {
        private final ProgramBlock blockToAdd;

        public Command(ProgramBlock blockToAdd) {
            this.blockToAdd = blockToAdd;
        }
    }
}
