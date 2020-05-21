package com.blocker.gameworld.domain;
import com.blocker.gameworld.domain.grid.GameGrid;
import org.junit.Before;
import org.junit.Test;

import static com.blocker.gameworld.domain.grid.TileType.BLOCKED;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class GameGridTest {
    GameGrid grid;
    @Before
    public void intialize (){
        grid = new GameGrid();
    }
    @Test
    public void getWidthtest(){
        assertEquals(grid.getWidth(),5);
    }


    @Test
    public void getHeighttest(){
        assertEquals(grid.getHeight(),5);

    }


    @Test
    public void getTileTypetest(){
                assertThrows(IllegalArgumentException.class,() -> {grid.getTileType(-1,0);});
        assertThrows(IllegalArgumentException.class,() -> {grid.getTileType(0,1000);});
                assertEquals(grid.getTileType(0,0), BLOCKED);
                assertEquals(grid.getTileType(new GridPosition(0,0)), BLOCKED);
                assertEquals(grid.getTileType(0,3),grid.getTileType(new GridPosition(0,3)));
    }


}
