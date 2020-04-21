package com.ui.event;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.presenter.ProgramCreationController;

/**
 * The Dragging started from palette handler.
 */
public class DraggingStartedFromPaletteHandler implements UiEventHandler<DraggingStartedFromPaletteHandler.Command, Void> {

    private final ProgramCreationController programCreator;
    private final GameState gameState;

    /**
     * Instantiates a new Dragging started from palette handler.
     *
     * @param gameState      the game state
     * @param programCreator the program creation controller needed in the UI for correct UI behaviour
     */
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

    /**
     * The command that will be used by the {@link #DraggingStartedFromPaletteHandler(GameState, ProgramCreationController)}
     */
    public static class Command implements UiEvent<Void> {
        private final ProgramBlock blockToAdd;

        /**
         * Instantiates a new Command.
         *
         * @param blockToAdd the block to add
         */
        public Command(ProgramBlock blockToAdd) {
            this.blockToAdd = blockToAdd;
        }
    }
}
