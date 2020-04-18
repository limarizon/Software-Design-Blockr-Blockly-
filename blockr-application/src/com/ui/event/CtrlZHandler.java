package com.ui.event;

import com.blockr.domain.GameState;
import com.ui.presenter.ProgramCreator;

public class CtrlZHandler implements UiEventHandler<CtrlZHandler.Command, Void> {

    private final GameState gameState;
    private final ProgramCreator programCreator;

    public CtrlZHandler(GameState gameState, ProgramCreator programCreator) {
        this.gameState = gameState;
        this.programCreator = programCreator;
    }

    @Override
    public Void handle(Command command) {
        if(!gameState.isProgramRunning())
        {//call function here
            programCreator.undo();
        }
        else{
            gameState.undoStepBlockProgram();
        }
        return null;
    }

    public static class Command implements UiEvent<Void> {
        public Command() {

        }
    }
}