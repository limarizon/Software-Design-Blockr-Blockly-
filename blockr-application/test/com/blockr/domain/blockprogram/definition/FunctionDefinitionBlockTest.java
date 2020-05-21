package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Action;
import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionDefinitionBlockTest {


    @Test
    public void executeAdditionRemovalOfBlocksInFunction() {
        FunctionDefinitionBlock funcDef = new FunctionDefinitionBlock();
        FunctionCallBlock functionCall = new FunctionCallBlock(funcDef);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        StatementListBlock statements =  new StatementListBlock();
        statements.add(functionCall);

        Action action1 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock = new GameActionBlock(action1);
        funcDef.addStatementBlock(gameActionBlock);

        Action action2 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock2 = new GameActionBlock(action2);
        AttachLocation location1 = AttachLocation.NEXT;
        funcDef.add(gameActionBlock2, location1);

        Action action3 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock3 = new GameActionBlock(action3);
        funcDef.addToStatementList(gameActionBlock3, gameActionBlock2, AttachLocation.NEXT);

        Action action4 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock4 = new GameActionBlock(action4);
        funcDef.addToStatementList(gameActionBlock4, 2);
        statements.add(funcDef);

        ExecutionCallStack executionCallStack = new ExecutionCallStack(gameWorld);

        functionCall.step(executionCallStack);
        funcDef.removeFromStatementList(gameActionBlock4);
        assertEquals(2, funcDef.getStatementListBlock().getStatements().size());
        funcDef.add(gameActionBlock4, AttachLocation.NEXT);
        assertEquals(2, funcDef.getStatementListBlock().getStatements().size());
        funcDef.removeYourself();
        assertEquals(2, statements.getStatements().size());
    }

    @Test
    public void checkAdditionToFunction() {
        FunctionDefinitionBlock funcDef = new FunctionDefinitionBlock();
        FunctionCallBlock functionCall = new FunctionCallBlock(funcDef);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        StatementListBlock statements =  new StatementListBlock();
        statements.add(functionCall);

        assertFalse(statements.isEmpty());
        assertEquals("List", statements.getName());
        statements.setParent(null);

        Action action1 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock = new GameActionBlock(action1);
        funcDef.addStatementBlock(gameActionBlock);

        Action action2 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock2 = new GameActionBlock(action2);
        AttachLocation location1 = AttachLocation.BODY;
        funcDef.add(gameActionBlock2, location1);

        Action action3 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock3 = new GameActionBlock(action3);
        funcDef.addToStatementList(gameActionBlock3, gameActionBlock2, AttachLocation.BODY);

        Action action4 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock4 = new GameActionBlock(action4);
        funcDef.addToStatementList(gameActionBlock4, 2);
        statements.add(funcDef);

        ExecutionCallStack executionCallStack = new ExecutionCallStack(gameWorld);

        functionCall.step(executionCallStack);
        funcDef.removeFromStatementList(gameActionBlock4);
        assertEquals(2, funcDef.getStatementListBlock().getStatements().size());
        funcDef.add(gameActionBlock4, AttachLocation.NEXT);
        assertEquals(2, funcDef.getStatementListBlock().getStatements().size());
        funcDef.removeYourself();
        assertEquals(2, statements.getStatements().size());
        StatementBlock stat2 = statements.copy();
        statements.removeYourself();
        stat2.add(gameActionBlock2, AttachLocation.BODY);
        assertEquals(false, stat2.canContainStatements());
        assertEquals(false, stat2.canContainPredicate());
    }

    @Test
    public void checkLastStatement() {
        FunctionDefinitionBlock funcDef = new FunctionDefinitionBlock();
        FunctionCallBlock functionCall = new FunctionCallBlock(funcDef);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        StatementListBlock statements =  new StatementListBlock();
        statements.add(functionCall);

        Action action1 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock = new GameActionBlock(action1);
        funcDef.addStatementBlock(gameActionBlock);

        Action action2 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock2 = new GameActionBlock(action2);
        AttachLocation location1 = AttachLocation.NEXT;
        funcDef.add(gameActionBlock2, location1);

        ExecutionCallStack executionCallStack = new ExecutionCallStack(gameWorld);

        functionCall.step(executionCallStack);
        assertTrue(funcDef.wasLastStatement(0, gameWorld));
        funcDef.removeYourself();
        assertEquals(1, statements.getStatements().size());
    }

    @Test
    public void countBlocks() {
        FunctionDefinitionBlock funcDef = new FunctionDefinitionBlock();
        FunctionCallBlock functionCall = new FunctionCallBlock(funcDef);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        StatementListBlock statements =  new StatementListBlock();
        statements.add(functionCall);
        assertEquals(true, funcDef.canContainStatements());
        Action action1 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock = new GameActionBlock(action1);
        funcDef.addStatementBlock(gameActionBlock);

        Action action2 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock2 = new GameActionBlock(action2);
        funcDef.addStatementBlock(gameActionBlock2);

        ExecutionCallStack executionCallStack = new ExecutionCallStack(gameWorld);

        functionCall.step(executionCallStack);
        assertEquals(2, funcDef.countBlocks());
        funcDef.step(executionCallStack);
    }

    @Test
    public void checkID() {
        FunctionDefinitionBlock funcDef = new FunctionDefinitionBlock();
        FunctionCallBlock functionCall = new FunctionCallBlock(funcDef);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        StatementListBlock statements =  new StatementListBlock();
        statements.add(functionCall);

        ExecutionCallStack executionCallStack = new ExecutionCallStack(gameWorld);

        assertEquals(true, funcDef.isFunctionDefinition());
        assertEquals("Function", funcDef.getName());

    }

    @Test
    public void checkSingleFunction() {
        FunctionDefinitionBlock funcDef = new FunctionDefinitionBlock();
        FunctionCallBlock functionCall = new FunctionCallBlock(funcDef);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        StatementListBlock statements =  new StatementListBlock();
        statements.add(functionCall);

        assertEquals(null, funcDef.copy());
    }

    @Test
    public void checkLocation() {
        FunctionDefinitionBlock funcDef = new FunctionDefinitionBlock();
        FunctionCallBlock functionCall = new FunctionCallBlock(funcDef);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        StatementListBlock statements =  new StatementListBlock();
        statements.add(functionCall);

        Action action1 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock = new GameActionBlock(action1);
        funcDef.addStatementBlock(gameActionBlock);

        ExecutionCallStack executionCallStack = new ExecutionCallStack(gameWorld);

        assertEquals(null, funcDef.getLocation());
        funcDef.getLocation(gameActionBlock);
    }

    @Test
    public void checkToExecuteLineNumber() {
        FunctionDefinitionBlock funcDef = new FunctionDefinitionBlock();
        FunctionCallBlock functionCall = new FunctionCallBlock(funcDef);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        StatementListBlock statements =  new StatementListBlock();
        statements.add(functionCall);

        Action action1 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock = new GameActionBlock(action1);
        funcDef.addStatementBlock(gameActionBlock);

        ExecutionCallStack executionCallStack = new ExecutionCallStack(gameWorld);
        funcDef.step(executionCallStack);
        assertEquals(false, funcDef.isNextStepToExecute(1, gameActionBlock));
    }
}
