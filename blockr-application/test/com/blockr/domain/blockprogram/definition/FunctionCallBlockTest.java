package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Action;
import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.execution.AbstractBlockTest;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;
import org.junit.Test;
import org.mockito.Mockito;
import org.mockito.cglib.core.Local;

import javax.swing.text.StyledEditorKit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

public class FunctionCallBlockTest extends AbstractBlockTest {

    @Test
    public void stepSuccessfulAction() {
        FunctionDefinitionBlock funcDef = Mockito.mock(FunctionDefinitionBlock.class);
        FunctionCallBlock functionCall = new FunctionCallBlock(funcDef);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        //FunctionCallBlock functionActionBlock = new FunctionCallBlock(action);
        ExecutionCallStack executionCallStack = new ExecutionCallStack(gameWorld);
        Boolean bool = false;
        try{
            functionCall.step(executionCallStack);
            bool = true;
        }catch (Exception e){
            bool = false;
        }
        assertEquals(true, bool);
    }

    @Test
    public void checkCopyMethod() {
        FunctionDefinitionBlock funcDef = Mockito.mock(FunctionDefinitionBlock.class);
        FunctionCallBlock functionCall = new FunctionCallBlock(funcDef);
        GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        StatementListBlock statements =  new StatementListBlock();
        statements.add(functionCall);

        Action action1 = Mockito.mock(Action.class);
        GameActionBlock gameActionBlock = new GameActionBlock(action1);
        funcDef.addStatementBlock(gameActionBlock);

        assertEquals(1, functionCall.countBlocks());
        StatementBlock happy = functionCall.copy();
        assertEquals(happy.getClass(), functionCall.getClass());
        assertEquals(happy.getName(), "Function Call");
        assertEquals("FunctionCallBlock", happy.toString());
    }
}