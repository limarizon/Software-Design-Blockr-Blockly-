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
        this.level = new Level(gameWorld, 5);
        this.programDefinition = new StatementListBlock();
        //createDefinitionForTesting();

        this.blockExecution = new BlockExecution(programDefinition, gameWorld);
    }

    //TODO : clean up
    private void createDefinitionForTesting() {
        List<Predicate> predicates = gameWorldType.getPredicates();
        List<Action> actions = gameWorldType.getActions();

        this.programDefinition.add(new GameActionBlock(actions.get(0)));
        this.programDefinition.add(new GameActionBlock((actions.get(1))));
        this.programDefinition.add(new GameActionBlock((actions.get(2))));
        WhileBlock whileBlock = new WhileBlock();
        whileBlock.setPredicate(new GamePredicateBlock(null));
        whileBlock.addStatementBlock(new GameActionBlock(actions.get(0)));
        whileBlock.addStatementBlock(new GameActionBlock(actions.get(0)));
        IfBlock ifBlock = new IfBlock();
        NotBlock notBlock = new NotBlock();
        notBlock.setPredicateToNegate(new GamePredicateBlock(null));
        ifBlock.setPredicate(notBlock);
        ifBlock.addStatementBlock(new GameActionBlock(actions.get(1)));
        whileBlock.addStatementBlock(ifBlock);
        this.programDefinition.add(whileBlock);
        this.programDefinition.add(new GameActionBlock(actions.get(0)));
        IfBlock otherIfBlock = new IfBlock();
        otherIfBlock.setPredicate(new GamePredicateBlock(null));
        otherIfBlock.addStatementBlock(new GameActionBlock(actions.get(2)));
        this.programDefinition.add(otherIfBlock);
        this.programDefinition.add(new GameActionBlock(actions.get(1)));
        this.programDefinition.add(new GameActionBlock(actions.get(2)));
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

    public <B extends ProgramBlock> boolean isCurrentStep(B source) {
        return blockExecution.isCurrentStep(source);
    }
}