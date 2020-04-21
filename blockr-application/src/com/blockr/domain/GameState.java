package com.blockr.domain;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.blocker.gameworldType.api.GameWorldTypeApi;
import com.blockr.domain.blockprogram.definition.*;
import com.blockr.domain.blockprogram.execution.BlockExecution;
import com.blockr.domain.game.Level;

import java.util.List;

public class GameState {
    private final GameWorldTypeApi gameWorldType;
    private Level level;
    private BlockExecution blockExecution;
    private StatementListBlock programDefinition;

    public GameState(GameWorldTypeApi gameWorldType){
        this.gameWorldType = gameWorldType;
        GameWorldApi gameWorld = gameWorldType.createGameWorldInstance();
        this.level = new Level(gameWorld, 6);
        this.programDefinition = new StatementListBlock();
        this.blockExecution = new BlockExecution(programDefinition, gameWorld);
    }

    public void resetBlockProgram(){
        blockExecution.reset();
    }

    public void undoStepBlockProgram(){
        blockExecution.undoStep();
    }

    public void redoStepBlockProgram(){
        blockExecution.redoStep();
    }

    public GameWorldApi getGameWorld() {
        return level.getGameWorld();
    }

    public GameWorldTypeApi getGameWorldType() {
        return gameWorldType;
    }

    public StatementListBlock getProgramDefinition() {
        return programDefinition;
    }

    public void step() {
        blockExecution.step();
    }

    public boolean isProgramRunning(){
        return blockExecution.isStepping();
    }

    public boolean isMaxBlocksReached() {
        return this.level.getMaxBlocks() <= programDefinition.countBlocks() ;
    }

    public <B extends ProgramBlock> boolean isCurrentStep(B source) {
        return blockExecution.isCurrentStep(source);
    }

}