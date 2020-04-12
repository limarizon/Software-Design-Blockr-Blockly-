package com.ui.event;

import com.blockr.domain.GameState;

public class RedoStepHandler implements UiEventHandler<RedoStepHandler.RedoStep, Void> {

    private final GameState gameState;

    public RedoStepHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public Void handle(RedoStep redoStep) {
        gameState.redoStepBlockProgram();
        return null;
    }

    public static class RedoStep implements UiEvent<Void> {
        public RedoStep() {

        }
    }
}