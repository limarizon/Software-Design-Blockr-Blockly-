package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.ui.components.block.program.AttachLocation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class ControlFlowBlockTest {

    @Test
    public void addBodyAction1() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.BODY;
        Action action = Mockito.mock(Action.class);
        StatementBlock statement = new GameActionBlock(action);
        assertEquals(true, cfb.canContainStatements());
        cfb.add(statement, location);
        assertEquals(1, cfb.getStatementListBlock().getStatements().size());

    }

    @Test
    public void addBodyAction2() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation locationB = AttachLocation.BODY;
        AttachLocation location = AttachLocation.NEXT;
        Action action = Mockito.mock(Action.class);
        StatementBlock statement1 = new GameActionBlock(action);
        Action action2 = Mockito.mock(Action.class);
        StatementBlock statement2 = new GameActionBlock(action2);
        cfb.add(statement1, locationB);
        cfb.addToStatementList(statement2, statement1 , location);
        assertEquals(2, cfb.getStatementListBlock().getStatements().size());
    }

    @Test
    public void addBodyAction3() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation locationB = AttachLocation.BODY;
        AttachLocation location = AttachLocation.NEXT;
        Action action = Mockito.mock(Action.class);
        StatementBlock statement1 = new GameActionBlock(action);
        Action action2 = Mockito.mock(Action.class);
        StatementBlock statement2 = new GameActionBlock(action2);
        cfb.add(statement1, locationB);
        cfb.addToStatementList(statement2, 1);
        cfb.getLocation(statement1);
        assertEquals(2, cfb.getStatementListBlock().getStatements().size());
    }

    @Test
    public void addBodyAction4() {
        WhileBlock cfb = new WhileBlock();
        StatementListBlock statement = new StatementListBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation locationB = AttachLocation.BODY;
        AttachLocation location = AttachLocation.NEXT;
        Action action = Mockito.mock(Action.class);
        StatementBlock statement1 = new GameActionBlock(action);
        Action action2 = Mockito.mock(Action.class);
        StatementBlock statement2 = new GameActionBlock(action2);
        cfb.add(statement1, locationB);
        cfb.addToStatementList(statement2, 1);
        statement.add(cfb);
        cfb.getLocation();

        assertEquals(2, cfb.getStatementListBlock().getStatements().size());
    }

    @Test
    public void removeBodyStatement() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation locationB = AttachLocation.BODY;
        AttachLocation location = AttachLocation.NEXT;
        Action action = Mockito.mock(Action.class);
        StatementBlock statement1 = new GameActionBlock(action);
        Action action2 = Mockito.mock(Action.class);
        StatementBlock statement2 = new GameActionBlock(action2);
        cfb.add(statement1, locationB);
        cfb.addToStatementList(statement2,1);
        cfb.removeFromStatementList(statement2);
        assertEquals(1, cfb.getStatementListBlock().getStatements().size());
    }

    @Test
    public void addConditionStatement() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.CONDITION;
        Predicate predicateBlock = Mockito.mock(Predicate.class);
        ProgramBlock blockToAdd = new GamePredicateBlock(predicateBlock);
        assertEquals(true, cfb.canContainPredicate());
        cfb.add(blockToAdd, location);
        assertEquals(false, cfb.getPredicate().hasSubPredicate());

    }

    @Test
    public void testInvalidCondition() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        assertEquals(false, cfb.isPredicateSatisfied(gameWorld));
    }

    @Test
    public void removeCondition() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        Predicate predicateBlock = Mockito.mock(Predicate.class);
        ProgramBlock blockToAdd = new GamePredicateBlock(predicateBlock);
        AttachLocation location = AttachLocation.CONDITION;
        PredicateBlock blockToRemove = (PredicateBlock) blockToAdd;
        cfb.add(blockToAdd, location);
        cfb.removePredicate(blockToRemove);
        assertEquals(null, cfb.getPredicate());
    }

    @Test
    public void checkCorrectLocationCondition() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.CONDITION;
        Predicate predicateBlock = Mockito.mock(Predicate.class);
        ProgramBlock blockToAdd = new GamePredicateBlock(predicateBlock);
        assertEquals(true, cfb.canContainPredicate());
        cfb.add(blockToAdd, location);
        cfb.getPredicate().getLocation();

    }

    @Test
    public void addPreviousStatement() {
        StatementListBlock statements = new StatementListBlock();
        WhileBlock cfb = new WhileBlock();
        Action action1 = Mockito.mock(Action.class);
        StatementBlock statement1 = new GameActionBlock(action1);
        statements.add(statement1);

        Action action2 = Mockito.mock(Action.class);
        StatementBlock statement2 = new GameActionBlock(action2);

        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.PREVIOUS;
        statements.add(cfb);
        cfb.add(statement2, location);
        assertEquals(3, statements.getStatements().size());
        cfb.removeYourself();
        assertEquals(2, statements.getStatements().size());
    }

    @Test
    public void addNextStatement() {
        StatementListBlock statements = new StatementListBlock();
        WhileBlock cfb = new WhileBlock();
        Action action1 = Mockito.mock(Action.class);
        StatementBlock statement1 = new GameActionBlock(action1);
        statements.add(statement1);

        Action action2 = Mockito.mock(Action.class);
        StatementBlock statement2 = new GameActionBlock(action2);

        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.NONE;
        statements.add(cfb);
        cfb.add(statement2, location);
        assertEquals(2, statements.getStatements().size());

    }
}