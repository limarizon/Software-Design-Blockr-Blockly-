package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.ui.components.block.program.AttachLocation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class GamePredicateBlockTest {

    @Test
    public void Name() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.CONDITION;
        Predicate predicate = Mockito.mock(Predicate.class);
        GamePredicateBlock predicateBlock = new GamePredicateBlock(predicate);

        assertEquals(null, predicateBlock.getName());
        assertEquals("GamePredicateBlock", predicateBlock.toString());
    }

    @Test
    public void copy() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.CONDITION;
        Predicate predicate = Mockito.mock(Predicate.class);
        GamePredicateBlock predicateBlock = new GamePredicateBlock(predicate);

        assertEquals(predicateBlock.getClass(), predicateBlock.copy().getClass());
    }

    @Test
    public void removeFromCfb() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.CONDITION;
        Predicate predicate = Mockito.mock(Predicate.class);
        GamePredicateBlock predicateBlock = new GamePredicateBlock(predicate);
        cfb.add(predicateBlock, location);
        predicateBlock.removeYourself();

        Action action1 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock = new GameActionBlock(action1);

        assertEquals(false,  predicateBlock.add(gameActionBlock, location));
    }

    @Test
    public void addStatementToCondition() {
        WhileBlock cfb = new WhileBlock();
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.CONDITION;
        Predicate predicate = Mockito.mock(Predicate.class);
        GamePredicateBlock predicateBlock = new GamePredicateBlock(predicate);

        assertEquals(false, predicateBlock.add(predicateBlock, location));
    }

    @Test
    public void isPredicate() {
        assertEquals(true, new GamePredicateBlock(null).isPredicateBlock());
    }
}