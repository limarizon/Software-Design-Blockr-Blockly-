package com.ui.event;

import com.blockr.domain.GameState;

public class ctrlShiftZHandler implements UiEventHandler<ctrlShiftZHandler.Command, Void> {

    private final GameState gameState;

    public ctrlShiftZHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public Void handle(Command command) {
        if(!gameState.programIsRunning) {//call function here
            gameState.redoBlockAddition();
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