package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.*;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BlockExecutionTest {

    @Test
    public void testStepStatementList(){
        var gameWorld = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new MoveForwardBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorld);
        blockExecution.step();
        verify(gameWorld, times(1)).moveForward();

        blockExecution.step();
        verify(gameWorld, times(1)).turnLeft();

        blockExecution.step();
        verify(gameWorld, times(2)).moveForward();
    }

    @Test
    public void testStepStatementListContainingIfBlockReturningTrue(){
        var gameWorld = mock(GameWorldApi.class);
        when(gameWorld.isWallInFront()).thenReturn(true);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        IfBlock ifBlock = new IfBlock();
        ifBlock.setPredicateBlock(new WallInFrontBlock());
        ifBlock.addStatementBlock(new TurnLeftBlock());
        ifBlock.addStatementBlock(new TurnRightBlock());
        statementListBlock.add(ifBlock);
        statementListBlock.add(new MoveForwardBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorld);
        blockExecution.step();
        verify(gameWorld, times(1)).moveForward();

        blockExecution.step();
        verify(gameWorld, times(1)).turnLeft();
        blockExecution.step();
        verify(gameWorld, times(1)).turnRight();

        blockExecution.step();
        verify(gameWorld, times(2)).moveForward();
    }
}