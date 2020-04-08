package com.ui.event;

import com.blockr.domain.GameState;

public class ResetExecutionHandler implements UiEventHandler<ResetExecutionHandler.ResetExecution, Void> {

    private final GameState gameState;

    public ResetExecutionHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public Void handle(ResetExecution resetExecution) {
        gameState.resetBlockProgram();
        return null;
    }

    public static class ResetExecution implements UiEvent<Void> {
        public ResetExecution() {

        }
    }
}
