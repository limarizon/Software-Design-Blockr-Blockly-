package com.ui.event;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.presenter.ProgramCreator;

public class DraggingStartedFromPaletteHandler implements UiEventHandler<DraggingStartedFromPaletteHandler.Command, Void> {

    private final ProgramCreator programCreator;

    public DraggingStartedFromPaletteHandler(ProgramCreator programCreator) {
        this.programCreator = programCreator;
    }

    @Override
    public Void handle(Command draggingStarted) {
        programCreator.startDraggingFromPalette(draggingStarted.blockToAdd);
        return null;
    }

    public static class Command implements UiEvent<Void> {
        private final ProgramBlock blockToAdd;

        public Command(ProgramBlock blockToAdd) {
            this.blockToAdd = blockToAdd;
        }
    }
}
