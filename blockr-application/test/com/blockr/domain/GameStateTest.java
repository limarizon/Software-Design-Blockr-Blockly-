package com.blockr.domain;

import com.blocker.gameworldType.api.GameWorldTypeApi;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameStateTest {

    @Test
    public void getGameWorldType() {
        GameWorldTypeApi gameWorldType = Mockito.mock(GameWorldTypeApi.class);
        GameState gameState = new GameState(gameWorldType);
        assertEquals(gameWorldType.getClass(), gameState.getGameWorldType().getClass());
    }

    @Test
    public void testMaxBlocks() {
        GameWorldTypeApi gameWorldType = Mockito.mock(GameWorldTypeApi.class);
        GameState gameState = new GameState(gameWorldType);
        assertEquals(false, gameState.isMaxBlocksReached());
    }

    @Test
    public void isRunning() {
        GameWorldTypeApi gameWorldType = Mockito.mock(GameWorldTypeApi.class);
        GameState gameState = new GameState(gameWorldType);
        assertEquals(false, gameState.isProgramRunning());
    }

    @Test
    public void getFuncDef() {
        GameWorldTypeApi gameWorldType = Mockito.mock(GameWorldTypeApi.class);
        GameState gameState = new GameState(gameWorldType);
        assertTrue(gameState.getFunctionDefinition()!=null);
    }

    @Test
    public void getProgramDef() {
        GameWorldTypeApi gameWorldType = Mockito.mock(GameWorldTypeApi.class);
        GameState gameState = new GameState(gameWorldType);
        assertFalse(gameState.getProgramDefinition()==null);
    }

    @Test
    public void step() {
        GameWorldTypeApi gameWorldType = Mockito.mock(GameWorldTypeApi.class);
        GameState gameState = new GameState(gameWorldType);
        ProgramBlock programBlock = Mockito.mock(ProgramBlock.class);
        Boolean bool = false;
        try{
            gameState.step();
            gameState.isCurrentStep(programBlock);
        }catch (Exception e){
            bool = true;
        }
        assertTrue(bool);
    }
}
