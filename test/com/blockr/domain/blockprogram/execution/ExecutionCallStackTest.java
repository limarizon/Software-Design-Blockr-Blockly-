package com.blockr.domain.blockprogram.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.MoveForwardBlock;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ExecutionCallStackTest {

    @Test
    public void testStepMoveForward(){
        var gameWorld = mock(GameWorldApi.class);
        var executionContext = new ExecutionCallStack(gameWorld);

        new MoveForwardBlock().step(executionContext);

        verify(gameWorld, times(1)).moveForward();
    }


}
