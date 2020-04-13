package com.ui.event;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;
import com.ui.presenter.ProgramCreator;

public class DraggingStoppedHandler implements UiEventHandler<DraggingStoppedHandler.Command, Void> {

    private final ProgramCreator programCreator;

    public DraggingStoppedHandler(ProgramCreator programCreator) {
        this.programCreator = programCreator;
    }

    @Override
    public Void handle(Command command) {
        programCreator.addToBlock(command.destinationBlock, command.attachLocation);
        return null;
    }

    public static class Command implements UiEvent<Void> {
        private final ProgramBlock destinationBlock;
        public AttachLocation attachLocation;

        public Command(ProgramBlock destinationBlock, AttachLocation attachLocation) {
            this.destinationBlock = destinationBlock;
            this.attachLocation = attachLocation;
        }
    }
}
