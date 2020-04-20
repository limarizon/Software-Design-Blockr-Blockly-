package com.ui.event;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.presenter.ProgramCreationController;

public class DraggingStartedFromPaletteHandler implements UiEventHandler<DraggingStartedFromPaletteHandler.Command, Void> {

    private final ProgramCreationController programCreator;
    private final GameState gameState;

    public DraggingStartedFromPaletteHandler( GameState gameState, ProgramCreationController programCreator) {
        this.programCreator = programCreator;
        this.gameState = gameState;
    }

    @Override
    public Void handle(Command draggingStarted) {
        if(this.gameState.isMaxBlocksReached()){
            return null;
        }else {
            programCreator.startDraggingFromPalette(draggingStarted.blockToAdd);
            return null;
        }
    }

    public static class Command implements UiEvent<Void> {
        private final ProgramBlock blockToAdd;

        public Command(ProgramBlock blockToAdd) {
            this.blockToAdd = blockToAdd;
        }
    }
}
