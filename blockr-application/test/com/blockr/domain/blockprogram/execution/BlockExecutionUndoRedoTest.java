package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.*;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.*;

public class BlockExecutionUndoRedoTest {

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
    @Test
    public void testDoIllegalUndo(){
        var gameWorldApi = mock(GameWorldApi.class);
        var statementListBlock = new StatementListBlock();
        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);

        blockExecution.undoStep();
        var snap = gameWorldApi.createSnapshot();
        verify(gameWorldApi, times(0)).restore(snap);
    }
    @Test
    public void testRedoOnce(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new TurnRightBlock());
        statementListBlock.add(new MoveForwardBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).createSnapshot();
        var snap = gameWorldApi.createSnapshot();
        blockExecution.undoStep();
        verify(gameWorldApi, times(1)).restore(snap);
        blockExecution.redoStep();
        verify(gameWorldApi, times(2)).moveForward();
    }
    @Test
    public void testRedoTwice(){
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
        blockExecution.undoStep();
        verify(gameWorldApi, times(1)).restore(snap);
        blockExecution.undoStep();
        verify(gameWorldApi, times(2)).restore(snap);
        blockExecution.redoStep();
        verify(gameWorldApi, times(2)).moveForward();
        blockExecution.redoStep();
        verify(gameWorldApi, times(2)).turnLeft();
    }
    @Test
    public void testUndoRedo(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new TurnRightBlock());
        statementListBlock.add(new MoveForwardBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).createSnapshot();
        var snap = gameWorldApi.createSnapshot();
        blockExecution.undoStep();
        verify(gameWorldApi, times(1)).restore(snap);
        blockExecution.redoStep();
        verify(gameWorldApi, times(2)).moveForward();
        blockExecution.undoStep();
        verify(gameWorldApi, times(2)).restore(snap);
        blockExecution.redoStep();
        verify(gameWorldApi, times(3)).moveForward();
    }

    @Test
    public void testForDiagramUndoRedo(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).createSnapshot();
        //var snap = gameWorldApi.createSnapshot();
        blockExecution.undoStep();
        //verify(gameWorldApi, times(1)).restore(snap);


    }

    @Test
    public void testIllegalRedo1(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new TurnRightBlock());
        statementListBlock.add(new MoveForwardBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);

        blockExecution.redoStep();
        verify(gameWorldApi, times(0)).moveForward();
    }
    @Test
    public void testIllegalRedo2(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new TurnRightBlock());
        statementListBlock.add(new MoveForwardBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi,times(1)).moveForward();
        blockExecution.step();
        verify(gameWorldApi,times(1)).turnLeft();
        blockExecution.step();
        verify(gameWorldApi,times(1)).turnRight();
        var snap = gameWorldApi.createSnapshot();
        blockExecution.undoStep();
        verify(gameWorldApi,times(1)).restore(snap);
        blockExecution.undoStep();
        verify(gameWorldApi,times(2)).restore(snap);
        blockExecution.step();
        verify(gameWorldApi,times(2)).turnLeft();
        blockExecution.redoStep();
        verify(gameWorldApi,times(1)).turnRight();
    }



}