package com.ui.event;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.presenter.ProgramCreator;

public class DraggingStartedHandler implements UiEventHandler<DraggingStartedHandler.DraggingStartedFromPalette, Void> {

    private final ProgramCreator programCreator;

    public DraggingStartedHandler(ProgramCreator programCreator) {
        this.programCreator = programCreator;
    }

    @Override
    public Void handle(DraggingStartedFromPalette draggingStarted) {
        programCreator.startDragging(draggingStarted.blockToAdd);
        return null;
    }

    public static class DraggingStartedFromPalette implements UiEvent<Void> {
        private final ProgramBlock blockToAdd;

        public DraggingStartedFromPalette(ProgramBlock blockToAdd) {
            this.blockToAdd = blockToAdd;
        }
    }
}
