package com.ui.event;

import com.blockr.domain.GameState;

public class ExecuteStepHandler implements UiEventHandler<ExecuteStepHandler.Command, Void> {

    private final GameState gameState;

    public ExecuteStepHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public Void handle(Command command) {
        gameState.step();
        return null;
    }

    public static class Command implements UiEvent<Void> {
        public Command() {

        }
    }
}
