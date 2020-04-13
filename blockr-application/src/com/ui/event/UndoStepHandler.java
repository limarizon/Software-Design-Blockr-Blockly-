package com.ui.event;

import com.blockr.domain.GameState;

public class UndoStepHandler implements UiEventHandler<UndoStepHandler.Command, Void> {

    private final GameState gameState;

    public UndoStepHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public Void handle(Command undoStep) {
        gameState.undoStepBlockProgram();
        return null;
    }

    public static class Command implements UiEvent<Void> {
        public Command() {

        }
    }
}