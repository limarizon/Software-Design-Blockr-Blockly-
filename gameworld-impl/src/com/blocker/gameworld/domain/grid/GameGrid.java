package com.blocker.gameworld.domain.grid;

import com.blocker.gameworld.domain.GridPosition;

import static com.blocker.gameworld.domain.grid.TileType.BLOCKED;
import static com.blocker.gameworld.domain.grid.TileType.FREE;

public class GameGrid {
    private static final TileType[][] GRID = {
            {BLOCKED, BLOCKED, BLOCKED, BLOCKED, BLOCKED},
            {BLOCKED, FREE, FREE, FREE, BLOCKED},
            {BLOCKED, FREE, FREE, FREE, BLOCKED},
            {BLOCKED, FREE, FREE, FREE, BLOCKED},
            {BLOCKED, BLOCKED, BLOCKED, BLOCKED, BLOCKED},
    };

    public int getWidth(){
        return GRID[0].length;
    }

    public int getHeight(){
        return GRID.length;
    }

    public TileType getTileType(int x, int y){

        if(x < 0 || x >= getWidth()){
            throw new IllegalArgumentException(String.format("x must lie between 0 and %s", getWidth()));
        }

        if(y < 0 || y >= getHeight()){
            throw new IllegalArgumentException(String.format("y must lie between 0 and %s", getHeight()));
        }
        //y is the row
        return GRID[y][x];
    }

    public TileType getTileType(GridPosition gridPosition){
        return getTileType(gridPosition.getX(), gridPosition.getY());
    }

}
