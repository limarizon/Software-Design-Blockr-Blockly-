package com.blockr.domain;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.blocker.gameworldType.api.GameWorldTypeApi;
import com.blockr.domain.blockprogram.definition.*;
import com.blockr.domain.blockprogram.execution.BlockExecution;
import com.blockr.domain.game.Level;

import java.util.List;

/**
 * This Class describes the state in which the block program and game world is at certain point in time
 * @author Simon Van Campenhout and Liam Volckerick
 * @version 2.0
 */
public class GameState {
    /**
     * The type of the gameworld
     */
    private final GameWorldTypeApi gameWorldType;
    /**
     * The level of the game which indicates the difficulty of this game
     */
    private Level level;
    /**
     * The current state of execution represented by the Blockexecution
     */
    private BlockExecution blockExecution;
    /**
     * The program that is created by the user and is to be executed
     */
    private StatementListBlock programDefinition;

    /**
     * The constructor of GameState which initialises the attributes
     * @param gameWorldType a GameWorldTypeApi instance
     */
    public GameState(GameWorldTypeApi gameWorldType){
        this.gameWorldType = gameWorldType;
        GameWorldApi gameWorld = gameWorldType.createGameWorldInstance();
        this.level = new Level(gameWorld, 6);
        this.programDefinition = new StatementListBlock();
        this.blockExecution = new BlockExecution(programDefinition, gameWorld);
    }

    /**
     * Resets the blockExecution
     */
    public void resetBlockProgram(){
        blockExecution.reset();
    }

    /**
     * Peforms an undo action on the blockExecution
     */
    public void undoStepBlockProgram(){
        blockExecution.undoStep();
    }

    /**
     * Peforms a redo action on the blockExecution
     */
    public void redoStepBlockProgram(){
        blockExecution.redoStep();
    }

    /**
     * Gets the gameworld in certain state
     * @return The gameworld in certain state
     */
    public GameWorldApi getGameWorld() {
        return level.getGameWorld();
    }

    /**
     * Gets a gameworldType
     * @return a gameworldType
     */
    public GameWorldTypeApi getGameWorldType() {
        return gameWorldType;
    }

    /**
     * Gets the block program that will be executed
     * @return the programDefinition of the gamestate
     */
    public StatementListBlock getProgramDefinition() {
        return programDefinition;
    }

    /**
     *  Peforms a step in the blockExecution
     */
    public void step() {
        blockExecution.step();
    }

    /**
     * Checks whether a block program is being executed
     * @return a boolean indicating the execution of the block program
     */
    public boolean isProgramRunning(){
        return blockExecution.isStepping();
    }

    /**
     * Checks whether the maximum number of used blocks allowed is exceeded
     * @return a boolean indicating whether the maximum number of used blocks allowed is exceeded
     */
    public boolean isMaxBlocksReached() {
        return this.level.getMaxBlocks() <= programDefinition.countBlocks() ;
    }

    /**
     * Get the current ProgramBlock which is the sourceBlock or contains the sourceBlock
     * @param source a program block
     * @param <B> ProgramBlock subtype
     * @return the current ProgramBlock which is the sourceBlock or contains the sourceBlock on the current
     */
    public <B extends ProgramBlock> boolean isCurrentStep(B source) {
        return blockExecution.isCurrentStep(source);
    }

}