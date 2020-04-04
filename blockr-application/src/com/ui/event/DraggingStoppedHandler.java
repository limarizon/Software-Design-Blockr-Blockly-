package com.ui.event;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.AttachLocation;
import com.ui.presenter.ProgramCreator;

public class DraggingStoppedHandler implements UiEventHandler<DraggingStoppedHandler.DraggingStopped, Void> {

    private final ProgramCreator programCreator;

    public DraggingStoppedHandler(ProgramCreator programCreator) {
        this.programCreator = programCreator;
    }

    @Override
    public Void handle(DraggingStopped draggingStopped) {
        programCreator.addToBlock(draggingStopped.destinationBlock, draggingStopped.attachLocation);
        return null;
    }

    public static class DraggingStopped implements UiEvent<Void> {
        private final ProgramBlock destinationBlock;
        public AttachLocation attachLocation;

        public DraggingStopped(ProgramBlock destinationBlock, AttachLocation attachLocation) {
            this.destinationBlock = destinationBlock;
            this.attachLocation = attachLocation;
        }
    }
}
