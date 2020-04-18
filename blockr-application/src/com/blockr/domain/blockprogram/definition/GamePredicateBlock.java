package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.ui.components.block.program.AttachLocation;

public class GamePredicateBlock implements PredicateBlock {

    private ContainingPredicateBlock parent;
    private Predicate gamePredicate;

    public GamePredicateBlock(Predicate gamePredicate) {
        this.gamePredicate = gamePredicate;
    }

    @Override
    public boolean satisfies(GameWorldApi gameWorld) {
        return gameWorld.evaluate(gamePredicate);
    }

    @Override
    public boolean hasSubPredicate() {
        return false;
    }

    @Override
    public String toString() {
        return GamePredicateBlock.class.getSimpleName();
    }

    @Override
    public String getName() {
        return gamePredicate.getName();
    }

    @Override
    public ProgramBlock copy() {
        return new GamePredicateBlock(gamePredicate);
    }

    @Override
    public void add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        // no action
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
    public boolean isPredicateBlock() {
        return true;
    }

    @Override
    public ProgramLocation getLocation() {
        return this.parent.getLocation(this);
    }
}
