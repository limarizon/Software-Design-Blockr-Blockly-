package com.blockr.domain;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.*;
import com.blockr.domain.blockprogram.execution.BlockExecution;
import com.blockr.domain.game.Level;

public class GameState {
    private Level level;
    private BlockExecution blockExecution;
    private StatementListBlock programDefinition;



    public GameState(GameWorldApi gameWorld){
        this.level = new Level(gameWorld, 5);
        this.programDefinition = new StatementListBlock();
        //createDefinitionForTesting();

        this.blockExecution = new BlockExecution(programDefinition, gameWorld);
    }

    //TODO : clean up
    private void createDefinitionForTesting() {
        this.programDefinition.add(new MoveForwardBlock());
        this.programDefinition.add(new TurnLeftBlock());
        this.programDefinition.add(new TurnRightBlock());
        WhileBlock whileBlock = new WhileBlock();
        whileBlock.setPredicate(new WallInFrontBlock());
        whileBlock.addStatementBlock(new MoveForwardBlock());
        whileBlock.addStatementBlock(new TurnRightBlock());
        IfBlock ifBlock = new IfBlock();
        NotBlock notBlock = new NotBlock();
        notBlock.setPredicateToNegate(new WallInFrontBlock());
        ifBlock.setPredicate(notBlock);
        ifBlock.addStatementBlock(new TurnLeftBlock());
        whileBlock.addStatementBlock(ifBlock);
        this.programDefinition.add(whileBlock);
        this.programDefinition.add(new MoveForwardBlock());
        IfBlock otherIfBlock = new IfBlock();
        otherIfBlock.setPredicate(new WallInFrontBlock());
        otherIfBlock.addStatementBlock(new MoveForwardBlock());
        this.programDefinition.add(otherIfBlock);
        this.programDefinition.add(new TurnRightBlock());
        this.programDefinition.add(new TurnLeftBlock());
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

    public StatementListBlock getProgramDefinition() {
        return programDefinition;
    }

    public void step() {
        blockExecution.step();
    }

    public <B extends ProgramBlock> boolean isCurrentStep(B source) {
        return blockExecution.isCurrentStep(source);
    }
}