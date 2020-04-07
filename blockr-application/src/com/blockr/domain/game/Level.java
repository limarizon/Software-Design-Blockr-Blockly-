package com.blockr.domain.game;

import com.blocker.gameworld.api.GameWorldApi;

public class Level {

    public GameWorldApi getGameWorld(){
        return gameWorld;
    }

    private final GameWorldApi gameWorld;

    public int getMaxBlocks(){
        return maxBlocks;
    }

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
