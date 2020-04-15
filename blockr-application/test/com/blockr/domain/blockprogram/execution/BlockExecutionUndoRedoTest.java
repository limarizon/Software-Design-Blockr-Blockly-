package com.blockr.domain.blockprogram.execution;

import com.blocker.apiUtilities.Action;
import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.*;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

public class BlockExecutionUndoRedoTest extends BlockTest {

    @Test
    public void testUndoOnce(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));
        statementListBlock.add(gameActionBlock(2));
        statementListBlock.add(gameActionBlock(3));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0, 1);
        blockExecution.undoStep();
        var snap = gameWorldApi.createSnapshot();
        verifyActionTriggered(0, 1);
    }

    @Test
    public void testUndoTwice(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));
        statementListBlock.add(gameActionBlock(2));
        statementListBlock.add(gameActionBlock(3));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0, 1);
        blockExecution.step();
        verifyActionTriggered(1, 1);
        var snap = gameWorldApi.createSnapshot();
        verifyActionTriggered(2, 0);
        blockExecution.undoStep();
        verify(gameWorldApi, times(1)).restore(snap);
        blockExecution.undoStep();
        verify(gameWorldApi, times(2)).restore(snap);
    }
    @Test
    public void testDoIllegalUndo(){
        var statementListBlock = new StatementListBlock();
        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);

        blockExecution.undoStep();
        var snap = gameWorldApi.createSnapshot();
        verify(gameWorldApi, times(0)).restore(snap);
    }
    @Test
    public void testRedoOnce(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));
        statementListBlock.add(gameActionBlock(2));
        statementListBlock.add(gameActionBlock(3));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).createSnapshot();
        var snap = gameWorldApi.createSnapshot();
        blockExecution.undoStep();
        verify(gameWorldApi, times(1)).restore(snap);
        blockExecution.redoStep();
        verify(gameWorldApi, times(2)).perform(any(Action.class));
    }
    @Test
    public void testRedoTwice(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));
        statementListBlock.add(gameActionBlock(2));
        statementListBlock.add(gameActionBlock(3));

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
        verifyActionTriggered(0,2);
        blockExecution.redoStep();
        verifyActionTriggered(1,2);
    }

    @Test
    public void testUndoRedo(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));
        statementListBlock.add(gameActionBlock(2));
        statementListBlock.add(gameActionBlock(3));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).createSnapshot();
        var snap = gameWorldApi.createSnapshot();
        blockExecution.undoStep();
        verify(gameWorldApi, times(1)).restore(snap);
        blockExecution.redoStep();
        verify(gameWorldApi, times(2)).perform(any(Action.class));
        blockExecution.undoStep();
        verify(gameWorldApi, times(2)).restore(snap);
        blockExecution.redoStep();
        verify(gameWorldApi, times(3)).perform(any(Action.class));
    }

    @Test
    public void testForDiagramUndoRedo(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).createSnapshot();
        //var snap = gameWorldApi.createSnapshot();
        blockExecution.undoStep();
        //verify(gameWorldApi, times(1)).restore(snap);


    }

    @Test
    public void testIllegalRedo1(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));
        statementListBlock.add(gameActionBlock(2));
        statementListBlock.add(gameActionBlock(3));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);

        blockExecution.redoStep();
        verify(gameWorldApi, times(0)).perform(any(Action.class));
    }

    @Test
    public void testIllegalRedo2(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));
        statementListBlock.add(gameActionBlock(2));
        statementListBlock.add(gameActionBlock(3));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0,1);
        blockExecution.step();
        verifyActionTriggered(1,1);
        blockExecution.step();
        verifyActionTriggered(2,1);
        var snap = gameWorldApi.createSnapshot();
        blockExecution.undoStep();
        verify(gameWorldApi,times(1)).restore(snap);
        blockExecution.undoStep();
        verify(gameWorldApi,times(2)).restore(snap);
        blockExecution.step();
        verifyActionTriggered(1,2);
        blockExecution.redoStep();
        verifyActionTriggered(2,1);
    }



}