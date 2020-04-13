package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.ui.components.block.program.AttachLocation;

public class WallInFrontBlock implements PredicateBlock {

    private ProgramBlock parent;

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
        if(blockToAdd.isNot()){
            NotBlock notBlockToAdd = (NotBlock) blockToAdd;
            blockToAdd.setParent(this.parent);
            setParent(notBlockToAdd);
        }
    }

    @Override
    public void setParent(ProgramBlock parent) {
        this.parent = parent;
    }

    @Override
    public void removeStatement() {
        parent.removePredicate(this);
    }

    @Override
    public void removePredicate(PredicateBlock predicate) {
        //do nothing
    }
}
