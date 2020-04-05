package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.*;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BlockExecutionRevertTest {

    @Test
    public void testRunRevertStatementListSingleRedo(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new TurnLeftBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        blockExecution.step();
        blockExecution.undoStep();
        blockExecution.redoStep();
        blockExecution.step();
        verify(gameWorldApi,times(2)).turnLeft();
    }

    @Test
    public void testRunRevertStatementListUndoRedoUndo(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new TurnLeftBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        blockExecution.step();
        blockExecution.undoStep();
        blockExecution.redoStep();
        blockExecution.undoStep();
        //blockExecution.redoStep();
        verify(gameWorldApi,times(1)).turnRight();
    }

    @Test
    public void testRunRevertStatementListUndoRedoUndoRedo(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new TurnLeftBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        blockExecution.step();
        blockExecution.undoStep();
        blockExecution.redoStep();
        blockExecution.undoStep();
        blockExecution.redoStep();
        verify(gameWorldApi,times(1)).turnLeft();
    }

    @Test
    public void testRunRevertStatementListUndoUndoRedoRedo(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new TurnLeftBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        blockExecution.step();
        blockExecution.undoStep();
        blockExecution.undoStep();
        blockExecution.redoStep();
        blockExecution.redoStep();//de stacks kloppen tot de call nextLineNumberPreviousFrame(); in REDO
        verify(gameWorldApi,times(1)).turnLeft();
    }

    @Test
    public void testRunRevertStatementListInCondition(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        NotBlock Not = new NotBlock();
        Not.setPredicateToNegate(new WallInFrontBlock());
        IfBlock If = new IfBlock();
        If.setPredicateBlock(Not);
        If.addStatementBlock(new MoveBackwardBlock());
        If.addStatementBlock(new MoveBackwardBlock());
        If.addStatementBlock(new TurnLeftBlock());
        If.addStatementBlock(new TurnLeftBlock());
        If.addStatementBlock(new MoveForwardBlock());

        statementListBlock.add(If);

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        blockExecution.step();
        blockExecution.undoStep();
        blockExecution.redoStep();
        verify(gameWorldApi,times(2)).moveBackward();
        blockExecution.step();
        verify(gameWorldApi,times(1)).turnLeft();
        blockExecution.step();
        blockExecution.step();
        verify(gameWorldApi,times(1)).moveForward();
    }
}
