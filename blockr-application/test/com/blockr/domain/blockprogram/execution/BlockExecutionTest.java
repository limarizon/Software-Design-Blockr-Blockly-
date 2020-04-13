package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.*;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BlockExecutionTest {

    @Test
    public void testRunStatementList(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).moveForward();

        blockExecution.step();
        verify(gameWorldApi, times(1)).turnLeft();
    }

    @Test
    public void testRunStatementWithIfList(){
        var gameWorldApi = mock(GameWorldApi.class);

        when(gameWorldApi.isFacingAWall()).thenReturn(true);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
            IfBlock ifBlock = new IfBlock();
            ifBlock.setPredicate(new WallInFrontBlock());
            ifBlock.addStatementBlock(new TurnRightBlock());
            ifBlock.addStatementBlock(new MoveForwardBlock());
        statementListBlock.add(ifBlock);
        statementListBlock.add(new TurnLeftBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).moveForward();

        blockExecution.step();
        verify(gameWorldApi, times(1)).turnRight();

        blockExecution.step();
        verify(gameWorldApi, times(2)).moveForward();

        blockExecution.step();
        verify(gameWorldApi, times(1)).turnLeft();
    }

    @Test
    public void testRunStatementWithIfListNotSatisfied(){
        var gameWorldApi = mock(GameWorldApi.class);

        when(gameWorldApi.isFacingAWall()).thenReturn(false);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
                IfBlock ifBlock = new IfBlock();
                ifBlock.setPredicate(new WallInFrontBlock());
                ifBlock.addStatementBlock(new TurnRightBlock());
                ifBlock.addStatementBlock(new MoveForwardBlock());
        statementListBlock.add(ifBlock);
        statementListBlock.add(new TurnLeftBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).moveForward();

        blockExecution.step();

        blockExecution.step();
        verify(gameWorldApi, times(1)).turnLeft();
    }

    @Test
    public void testRunStatementWithWhileCondition(){
        var gameWorldApi = mock(GameWorldApi.class);

        when(gameWorldApi.isFacingAWall()).thenReturn(true, true, true, false);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
            WhileBlock whileBlock = new WhileBlock();
            whileBlock.setPredicate(new WallInFrontBlock());
            whileBlock.addStatementBlock(new TurnRightBlock());
            whileBlock.addStatementBlock(new MoveForwardBlock());
        statementListBlock.add(whileBlock);
        statementListBlock.add(new TurnLeftBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).moveForward();

        blockExecution.step();
        verify(gameWorldApi, times(1)).turnRight();
        blockExecution.step();
        verify(gameWorldApi, times(2)).moveForward();

        blockExecution.step();
        verify(gameWorldApi, times(2)).turnRight();
        blockExecution.step();
        verify(gameWorldApi, times(3)).moveForward();

        blockExecution.step();
        verify(gameWorldApi, times(3)).turnRight();
        blockExecution.step();
        verify(gameWorldApi, times(4)).moveForward();

        blockExecution.step();
        blockExecution.step();
        verify(gameWorldApi, times(1)).turnLeft();
    }

    @Test
    public void testRunStatementWithWhileAndInsideIfCondition(){
        var gameWorldApi = mock(GameWorldApi.class);

        when(gameWorldApi.isFacingAWall()).thenReturn(true, false, true, false, false);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        WhileBlock whileBlock = new WhileBlock();
        whileBlock.setPredicate(new WallInFrontBlock());
        whileBlock.addStatementBlock(new TurnRightBlock());
            IfBlock ifBlock = new IfBlock();
            ifBlock.setPredicate(new WallInFrontBlock());
            ifBlock.addStatementBlock(new TurnLeftBlock());
            whileBlock.addStatementBlock(ifBlock);
        whileBlock.addStatementBlock(new MoveForwardBlock());
        statementListBlock.add(whileBlock);

        statementListBlock.add(new TurnLeftBlock());

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verify(gameWorldApi, times(1)).moveForward();

        blockExecution.step();
        verify(gameWorldApi, times(1)).turnRight();
        blockExecution.step();
        blockExecution.step();
        verify(gameWorldApi, times(2)).moveForward();

        blockExecution.step();
        verify(gameWorldApi, times(2)).turnRight();
        blockExecution.step();
        blockExecution.step();
        verify(gameWorldApi, times(3)).moveForward();

        blockExecution.step();
        blockExecution.step();
        verify(gameWorldApi, times(1)).turnLeft();
    }

}