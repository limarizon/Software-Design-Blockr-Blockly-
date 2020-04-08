package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.*;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class BlockExecutionUndoTest {

    @Test
    public void testRunStatementList(){
        var gameWorldApi = mock(GameWorldApi.class);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(new MoveForwardBlock());
        statementListBlock.add(new TurnLeftBlock());
        statementListBlock.add(new TurnRightBlock());
        statementListBlock.add(new MoveForwardBlock());


    }

}