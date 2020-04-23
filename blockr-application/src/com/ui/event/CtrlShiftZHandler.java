package com.ui.event;

import com.blockr.domain.GameState;
import com.ui.presenter.ProgramCreationController;

/**
 * The Ctrl shift z handler.
 */
public class CtrlShiftZHandler implements UiEventHandler<CtrlShiftZHandler.Command, Void> {

    private final GameState gameState;
    private final ProgramCreationController programCreationController;

    /**
     * Instantiates a new Ctrl shift z handler.
     *
     * @param gameState                 the game state
     * @param programCreationController the program creation controller needed in the UI for correct UI behaviour
     */
    public CtrlShiftZHandler(GameState gameState, ProgramCreationController programCreationController) {
        this.gameState = gameState;
        this.programCreationController = programCreationController;
    }

    @Override
    public Void handle(Command command) {
        if(!gameState.isProgramRunning()) {//call function here
            programCreationController.redo();
        }
        else{
            gameState.redoStepBlockProgram();
        }
        return null;
    }

    /**
     * The command that will be used by the CtrlShiftZHandler(GameState, ProgramCreationController)
     */
    public static class Command implements UiEvent<Void> {
        /**
         * Instantiates a new Command.
         */
        public Command() {

        }
    }
}