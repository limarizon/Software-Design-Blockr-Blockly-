package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.*;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.*;

public class BlockExecutionUndoTest {

    @Test
    public void testUndoOnce(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new TurnRightBlock());
        statementListBlock.add(new MoveForwardBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).createSnapshot();
        blockExecution.undoStep();
        var snap = gameWorldApi.createSnapshot();
        verify(gameWorldApi, times(1)).restore(snap);
    }
    @Test
    public void testUndoTwice(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new TurnRightBlock());
        statementListBlock.add(new MoveForwardBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).createSnapshot();
        blockExecution.step();
        verify(gameWorldApi, times(2)).createSnapshot();
        var snap = gameWorldApi.createSnapshot();
        verify(gameWorldApi, times(0)).restore(snap);
        blockExecution.undoStep();
        verify(gameWorldApi, times(1)).restore(snap);
        blockExecution.undoStep();
        verify(gameWorldApi, times(2)).restore(snap);
    }

}