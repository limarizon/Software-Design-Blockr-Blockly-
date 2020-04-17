package com.ui.event;

import com.blockr.domain.GameState;
import com.ui.presenter.ProgramCreator;

public class CtrlShiftZHandler implements UiEventHandler<CtrlShiftZHandler.Command, Void> {

    private final GameState gameState;
    private final ProgramCreator programCreator;

    public CtrlShiftZHandler(GameState gameState, ProgramCreator programCreator) {
        this.gameState = gameState;
        this.programCreator = programCreator;
    }

    @Override
    public Void handle(Command command) {
        if(!gameState.programIsRunning) {//call function here
            programCreator.redo();
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