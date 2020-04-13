package com.ui.event;

import com.blockr.domain.GameState;

public class RedoStepHandler implements UiEventHandler<RedoStepHandler.Command, Void> {

    private final GameState gameState;

    public RedoStepHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public Void handle(Command redoStep) {
        gameState.redoStepBlockProgram();
        return null;
    }

    public static class Command implements UiEvent<Void> {
        public Command() {

        }
    }
}