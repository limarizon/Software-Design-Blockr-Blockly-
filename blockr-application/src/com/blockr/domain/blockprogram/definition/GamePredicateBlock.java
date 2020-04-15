package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;
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
        if(blockToAdd.isNot()){
            NotBlock notBlockToAdd = (NotBlock) blockToAdd;
            var saveParent = parent;
            this.parent.removePredicate(this);
            saveParent.setPredicate(notBlockToAdd);
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
