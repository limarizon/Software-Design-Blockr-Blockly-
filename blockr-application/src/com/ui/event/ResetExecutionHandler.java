package com.ui.event;

import com.blockr.domain.GameState;

/**
 * The Reset execution handler.
 */
public class ResetExecutionHandler implements UiEventHandler<ResetExecutionHandler.Command, Void> {

    private final GameState gameState;

    /**
     * Instantiates a new Reset execution handler.
     *
     * @param gameState the game state
     */
    public ResetExecutionHandler(GameState gameState) {
        this.gameState = gameState;
    }

    /**
     * Will call the correct domain parts to handle given command.
     * @param resetExecution uiEvent
     * @return null;
     */
    @Override
    public Void handle(Command resetExecution) {
        gameState.resetBlockProgram();
        return null;
    }

    /**
     * The command that will be used by the ResetExecutionHandler(GameState)
     */
    public static class Command implements UiEvent<Void> {
        /**
         * Instantiates a new Command.
         */
        public Command() {

        }
    }
}
