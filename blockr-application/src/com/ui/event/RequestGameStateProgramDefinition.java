package com.ui.event;

import com.blockr.domain.GameState;
import com.ui.presenter.ProgramCreationController;
import com.ui.presenter.ProgramDefinitionHolder;

/**
 * The RequestGameStateProgramDefinition handler.
 */
public class RequestGameStateProgramDefinition implements UiEventHandler<RequestGameStateProgramDefinition.Command, Void> {

    private final GameState gameState;
    private final ProgramDefinitionHolder programDefinitionHolder;

    /**
     * Instantiates a new Request Game State Program Def. handler.
     *
     * @param gameState                 the game state
     * @param programDefinitionHolder the program creation controller needed in the UI for correct UI behaviour
     */
    public RequestGameStateProgramDefinition(GameState gameState, ProgramDefinitionHolder programDefinitionHolder) {
        this.gameState = gameState;
        this.programDefinitionHolder = programDefinitionHolder;
    }

    @Override
    public Void handle(Command command) {
        programDefinitionHolder.setProgramDefinition(gameState.getProgramDefinition());
        programDefinitionHolder.setFunctionDefinitionBlock(gameState.getFunctionDefinition());
        return null;
    }

    /**
     * The command that will be used by the RequestGameStateProgramDefinition handler(GameState, ProgramCreationController)
     */
    public static class Command implements UiEvent<Void> {
        /**
         * Instantiates a new Command.
         */
        public Command() {

        }
    }
}