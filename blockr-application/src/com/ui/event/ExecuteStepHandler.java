package com.ui.event;

import com.blockr.domain.GameState;

public class ExecuteStepHandler implements UiEventHandler<ExecuteStepHandler.ExecuteStep, Void> {

    private final GameState gameState;

    public ExecuteStepHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public Void handle(ExecuteStep executeStep) {
        gameState.resetBlockProgram();
        return null;
    }

    public static class ExecuteStep implements UiEvent<Void> {
        public ExecuteStep() {

        }
    }
}
