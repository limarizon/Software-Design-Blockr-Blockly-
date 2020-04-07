package com.ui.event;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.presenter.ProgramCreator;

public class DraggingStartedHandler implements UiEventHandler<DraggingStartedHandler.DraggingStarted, Void> {

    private final ProgramCreator programCreator;

    public DraggingStartedHandler(ProgramCreator programCreator) {
        this.programCreator = programCreator;
    }

    @Override
    public Void handle(DraggingStarted draggingStarted) {
        programCreator.startDragging(draggingStarted.blockToAdd);
        return null;
    }

    public static class DraggingStarted implements UiEvent<Void> {
        private final ProgramBlock blockToAdd;

        public DraggingStarted(ProgramBlock blockToAdd) {
            this.blockToAdd = blockToAdd;
        }
    }
}
