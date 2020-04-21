package com.blockr.domain.game;

import com.blocker.gameworld.api.GameWorldApi;

/**
 * @author
 * @version
 */
public class Level {
    /**
     * Gets the gameworld API in this level
     * @return
     */
    public GameWorldApi getGameWorld(){
        return gameWorld;
    }

    /**
     * The gameworld API in this level
     */
    private final GameWorldApi gameWorld;

    /**
     * Gets the maximum number of blocks that can be used in this level
     * @return the maximum number of blocks that can be used in this level
     */
    public int getMaxBlocks(){
        return maxBlocks;
    }

    /**
     * The maximum number of blocks that can be used in this level
     */
    private final int maxBlocks;

    /**
     * Constructor for Level
     * @param gameWorld: object GameWorld instantiated with its grid, startingposition, startorientation, goalposition
     * @param maxBlocks: Maximum amount of blocks allowed to use
     */
    public Level(GameWorldApi gameWorld, int maxBlocks){
        this.gameWorld = gameWorld;
        this.maxBlocks = maxBlocks;
    }


}
