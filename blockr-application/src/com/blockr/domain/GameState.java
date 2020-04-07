package com.blockr.domain;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.StatementListBlock;
import com.blockr.domain.blockprogram.execution.BlockExecution;
import com.blockr.domain.game.Level;

public class GameState {
    private Level level;
    private BlockExecution blockExecution;
    private StatementListBlock programDefinition;

    public GameState(GameWorldApi gameWorld){
        this.level = new Level(gameWorld, 5);
        this.programDefinition = new StatementListBlock();
        this.blockExecution = new BlockExecution(programDefinition, gameWorld);
    }

    public void resetBlockProgram(){
        level.getGameWorld().reset();
        blockExecution.reset();
    }

    public GameWorldApi getGameWorld() {
        return level.getGameWorld();
    }

    public StatementListBlock getProgramDefinition() {
        return programDefinition;
    }

    public void step() {
        blockExecution.step();
    }
}