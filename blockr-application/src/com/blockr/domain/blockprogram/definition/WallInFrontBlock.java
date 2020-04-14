package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.ui.components.block.program.AttachLocation;

public class WallInFrontBlock implements PredicateBlock {

    private ContainingPredicateBlock parent;

    @Override
    public boolean satisfies(GameWorldApi gameWorld) {
        return gameWorld.isFacingAWall();
    }

    @Override
    public boolean hasSubPredicate() {
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
            this.parent.removePredicate(this);
            this.parent.setPredicate(notBlockToAdd);
            notBlockToAdd.setPredicateToNegate(this);
        }
    }

    @Override
    public void setParent(ContainingPredicateBlock parent) {
        this.parent = parent;
    }

    @Override
    public void removeYourself() {
        parent.removePredicate(this);
    }

    @Override
    public boolean isGamePredicateBlock() {
        return true;
    }
}
