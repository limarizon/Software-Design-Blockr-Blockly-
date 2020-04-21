package com.ui.event;

import com.blockr.domain.GameState;
import com.ui.presenter.ProgramCreationController;

/**
 * The Ctrl z handler.
 */
public class CtrlZHandler implements UiEventHandler<CtrlZHandler.Command, Void> {

    private final GameState gameState;
    private final ProgramCreationController programCreationController;

    /**
     * Instantiates a new Ctrl z handler.
     *
     * @param gameState                 the game state
     * @param programCreationController the program creation controller needed in the UI for correct UI behaviour
     */
    public CtrlZHandler(GameState gameState, ProgramCreationController programCreationController) {
        this.gameState = gameState;
        this.programCreationController = programCreationController;
    }

    @Override
    public Void handle(Command command) {
        if (gameState.isProgramRunning()) {
            gameState.undoStepBlockProgram();
        } else {
            programCreationController.undo();
        }
        return null;
    }

    /**
     * The command that will be used by the {@link #CtrlZHandler(GameState, ProgramCreationController)}
     */
    public static class Command implements UiEvent<Void> {
        /**
         * Instantiates a new Command.
         */
        public Command() {

        }
    }
}