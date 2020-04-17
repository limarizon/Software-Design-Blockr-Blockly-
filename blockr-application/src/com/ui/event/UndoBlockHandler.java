package com.ui.event;

import com.blockr.domain.GameState;

public class UndoBlockHandler implements UiEventHandler<UndoBlockHandler.Command, Void> {

    private final GameState gameState;

    public UndoBlockHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public Void handle(Command command) {
        if(!gameState.programIsRunning)
        {//call function here
            return null;
        }
        return null;
    }

    public static class Command implements UiEvent<Void> {
        public Command() {

        }
    }
}
