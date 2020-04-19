package com.ui.event;

import com.blockr.domain.GameState;
import com.ui.presenter.ProgramCreationController;

public class CtrlShiftZHandler implements UiEventHandler<CtrlShiftZHandler.Command, Void> {

    private final GameState gameState;
    private final ProgramCreationController programCreationController;

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

    public static class Command implements UiEvent<Void> {
        public Command() {

        }
    }
}