package com.blockr.domain.block;

import com.blocker.gameworld.api.GameWorldApi;

public class WallInFrontBlock extends ConditionBlock {

    @Override
    public boolean evaluate(GameWorldApi gameWorld) {
        //return gameWorld.isRobotInFrontOfWall();
        //return gameWorld.getTileType(gameWorld.getRobotPosition().translate(gameWorld.getRobotOrientation().getOffset())) == TileType.Blocked;
        return true;
    }
}
