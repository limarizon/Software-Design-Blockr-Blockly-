package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.ui.components.block.program.AttachLocation;
import org.junit.Test;
import org.mockito.Mockito;

import java.nio.channels.AcceptPendingException;

import static org.junit.jupiter.api.Assertions.*;

public class NotBlockTest {
    @Test
    public void Name() {
        NotBlock predicate = new NotBlock();
        ProgramBlock programBlock = Mockito.mock(ProgramBlock.class);

        assertEquals("Not", predicate.getName());
        assertEquals("NotBlock", predicate.toString());
        assertEquals("Not", predicate.copy().getName());
        assertEquals(false, programBlock.isFunctionDefinition());
    }

    @Test
    public void addPredicateTo() {
        Predicate predicate = Mockito.mock(Predicate.class);
        GamePredicateBlock predicateBlock = new GamePredicateBlock(predicate);
        NotBlock notBlock = new NotBlock();

        notBlock.setPredicateToNegate(predicateBlock);
        assertEquals(true, notBlock.hasSubPredicate());
        assertEquals(2, notBlock.countBlocks());
        assertFalse(notBlock.getPredicate() == null);
    }

    @Test
    public void addBlockTo() {
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.CONDITION;
        Predicate predicate = Mockito.mock(Predicate.class);
        GamePredicateBlock predicateBlock = new GamePredicateBlock(predicate);
        NotBlock notBlock = new NotBlock();

        assertEquals(true, notBlock.add(predicateBlock, AttachLocation.BODY));
        assertEquals(2, notBlock.countBlocks());
    }

    @Test
    public void satisfies() {
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.CONDITION;
        Predicate predicate = Mockito.mock(Predicate.class);
        GamePredicateBlock predicateBlock = new GamePredicateBlock(predicate);
        NotBlock notBlock = new NotBlock();
        notBlock.setPredicateToNegate(predicateBlock);

        assertEquals(true, notBlock.satisfies(gameWorld));
    }

    @Test
    public void satisfiesNull() {
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.CONDITION;
        Predicate predicate = Mockito.mock(Predicate.class);
        GamePredicateBlock predicateBlock = new GamePredicateBlock(predicate);
        NotBlock notBlock = new NotBlock();

        assertEquals(false, notBlock.satisfies(gameWorld));
    }

    @Test
    public void addFalseBlockTo() {
        Action action = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock = new GameActionBlock(action);
        NotBlock notBlock = new NotBlock();

        assertEquals(false, notBlock.add(gameActionBlock, AttachLocation.BODY));
        assertEquals(1, notBlock.countBlocks());
    }

    @Test
    public void removeBlock() {
        WhileBlock cfb = new WhileBlock();
        NotBlock notBlock = new NotBlock();
        StatementListBlock statementListBlock = new StatementListBlock();
        cfb.setPredicate(notBlock);
        statementListBlock.add(cfb);
        assertEquals(1, statementListBlock.getStatements().size());
        notBlock.removeYourself();
        assertTrue(cfb.getPredicate() == null);
    }

    @Test
    public void removeBlock2() {
        WhileBlock cfb = new WhileBlock();
        NotBlock notBlock = new NotBlock();
        StatementListBlock statementListBlock = new StatementListBlock();
        cfb.setPredicate(notBlock);
        Predicate predicate = Mockito.mock(Predicate.class);
        GamePredicateBlock predicateBlock = new GamePredicateBlock(predicate);
        notBlock.setPredicate(predicateBlock);
        statementListBlock.add(cfb);
        assertEquals(1, statementListBlock.getStatements().size());
        notBlock.removePredicate(predicateBlock);
        assertEquals(false,notBlock.getPredicateToNegate() == null);
    }

    @Test
    public void location() {
        WhileBlock cfb = new WhileBlock();
        NotBlock notBlock = new NotBlock();
        StatementListBlock statementListBlock = new StatementListBlock();
        cfb.setPredicate(notBlock);
        statementListBlock.add(cfb);
        assertEquals(null, notBlock.getLocation());
        GamePredicateBlock pred = new GamePredicateBlock(null);
        notBlock.setPredicate(pred);
        notBlock.getLocation(pred);
    }
}