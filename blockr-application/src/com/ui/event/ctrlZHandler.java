package com.ui.event;

import com.blockr.domain.GameState;

public class ctrlZHandler implements UiEventHandler<ctrlZHandler.Command, Void> {

    private final GameState gameState;

    public ctrlZHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public Void handle(Command command) {
        if(!gameState.programIsRunning)
        {//call function here
            gameState.undoBlockAddition();
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