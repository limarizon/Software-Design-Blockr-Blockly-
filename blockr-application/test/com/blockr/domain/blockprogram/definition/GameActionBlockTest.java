package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Action;
import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class GameActionBlockTest {

    @Test
    public void stepSuccessfulAction() {
        Action action = Mockito.mock(Action.class);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        GameActionBlock gameActionBlock = new GameActionBlock(action);
        ExecutionCallStack executionCallStack = new ExecutionCallStack(gameWorld);
        when(gameWorld.perform(action)).thenReturn(true);

        gameActionBlock.step(executionCallStack);
    }

    @Test
    public void stepFailureAction() {
        Action action = Mockito.mock(Action.class);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        GameActionBlock gameActionBlock = new GameActionBlock(action);
        ExecutionCallStack executionCallStack = new ExecutionCallStack(gameWorld);
        when(gameWorld.perform(action)).thenReturn(false);

        gameActionBlock.step(executionCallStack);
    }

    @Test
    public void copy() {
        assertEquals(GameActionBlock.class, new GameActionBlock(null).copy().getClass());
    }
}