package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;

public class WallInFrontBlock implements PredicateBlock {

    @Override
    public boolean satisfies(GameWorldApi gameWorld) {
        return gameWorld.isFacingAWall();
    }

    @Override
    public String toString() {
        return WallInFrontBlock.class.getSimpleName();
    }

    @Override
    public String getName() {
        return "Wall In F.";
    }
}
