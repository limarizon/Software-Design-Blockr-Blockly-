package com.ui.event;

import com.blockr.domain.GameState;
import com.ui.presenter.ProgramCreationController;

public class CtrlZHandler implements UiEventHandler<CtrlZHandler.Command, Void> {

    private final GameState gameState;
    private final ProgramCreationController programCreationController;

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

    public static class Command implements UiEvent<Void> {
        public Command() {

        }
    }
}