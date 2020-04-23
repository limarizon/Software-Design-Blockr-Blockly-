package com.blocker.gameworld.domain.grid;

import com.blocker.gameworld.domain.GridPosition;

import static com.blocker.gameworld.domain.grid.TileType.BLOCKED;
import static com.blocker.gameworld.domain.grid.TileType.FREE;

/**
 * The class defining the gameworld grid
 */
public class GameGrid {
    private static final TileType[][] GRID = {
            {BLOCKED, BLOCKED, BLOCKED, BLOCKED, BLOCKED},
            {BLOCKED, FREE, FREE, FREE, BLOCKED},
            {BLOCKED, FREE, FREE, FREE, BLOCKED},
            {BLOCKED, FREE, FREE, FREE, BLOCKED},
            {BLOCKED, BLOCKED, BLOCKED, BLOCKED, BLOCKED},
    };

    /**
     * Get width int.
     *
     * @return the width
     */
    public int getWidth(){
        return GRID[0].length;
    }

    /**
     * Get height int.
     *
     * @return the height
     */
    public int getHeight(){
        return GRID.length;
    }

    /**
     * Get tile type.
     *
     * @param x the x-value of the tile
     * @param y the y-value of the tile
     * @return the tile type at the given (x,y) coordinate
     */
    public TileType getTileType(int x, int y){

        if(x < 0 || x >= getWidth()){
            throw new IllegalArgumentException(String.format("x must lie between 0 and %s", getWidth()));
        }

        if(y < 0 || y >= getHeight()){
            throw new IllegalArgumentException(String.format("y must lie between 0 and %s", getHeight()));
        }
        return GRID[x][y];
    }

    /**
     * Get tile type of a GridPosition.
     *
     * @param gridPosition the grid position
     * @return the tile type
     */
    public TileType getTileType(GridPosition gridPosition){
        return getTileType(gridPosition.getX(), gridPosition.getY());
    }

}
