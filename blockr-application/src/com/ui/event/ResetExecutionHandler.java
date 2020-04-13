package com.ui.event;

import com.blockr.domain.GameState;

public class ResetExecutionHandler implements UiEventHandler<ResetExecutionHandler.Command, Void> {

    private final GameState gameState;

    public ResetExecutionHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public Void handle(Command resetExecution) {
        gameState.resetBlockProgram();
        return null;
    }

    public static class Command implements UiEvent<Void> {
        public Command() {

        }
    }
}
