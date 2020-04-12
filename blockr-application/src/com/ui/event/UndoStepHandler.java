package com.ui.event;

import com.blockr.domain.GameState;

public class UndoStepHandler implements UiEventHandler<UndoStepHandler.UndoStep, Void> {

    private final GameState gameState;

    public UndoStepHandler(GameState gameState) {
        this.gameState = gameState;
    }

    @Override
    public Void handle(UndoStep undoStep) {
        gameState.undoStepBlockProgram();
        return null;
    }

    public static class UndoStep implements UiEvent<Void> {
        public UndoStep() {

        }
    }
}