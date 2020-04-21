package com.ui.event;

import com.blockr.domain.GameState;

/**
 * The Execute step handler.
 */
public class ExecuteStepHandler implements UiEventHandler<ExecuteStepHandler.Command, Void> {

    private final GameState gameState;

    /**
     * Instantiates a new Execute step handler.
     *
     * @param gameState the game state
     */
    public ExecuteStepHandler(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Will call the correct domain parts to handle given command.
     * @param command UiEvent
     * @return null;
     */
    @Override
    public Void handle(Command command) {
        gameState.step();
        return null;
    }

    /**
     * The command that will be used by the {@link #ExecuteStepHandler(GameState)} (GameState)}.
     */
    public static class Command implements UiEvent<Void> {
        /**
         * Instantiates a new Command.
         */
        public Command() {

        }
    }
}
