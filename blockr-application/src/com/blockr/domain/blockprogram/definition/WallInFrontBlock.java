package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.ui.components.block.program.AttachLocation;

public class WallInFrontBlock implements PredicateBlock {

    @Override
    public boolean satisfies(GameWorldApi gameWorld) {
        return gameWorld.isFacingAWall();
    }

    @Override
    public boolean hasPredicate() {
        return false;
    }

    @Override
    public String toString() {
        return WallInFrontBlock.class.getSimpleName();
    }

    @Override
    public String getName() {
        return "Wall In F.";
    }

    @Override
    public ProgramBlock copy() {
        return new WallInFrontBlock();
    }

    @Override
    public void add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        //TODO, to implement
    }

    @Override
    public void removeFromProgram() {
        //TODO, to implement
    }
}
