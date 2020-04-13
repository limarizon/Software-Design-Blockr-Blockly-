package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.ui.components.block.program.AttachLocation;

public class NotBlock implements PredicateBlock {

    private PredicateBlock predicateToNegate;

    public void setPredicateToNegate(PredicateBlock predicateToNegate) {
        this.predicateToNegate = predicateToNegate;
    }

    public PredicateBlock getPredicateToNegate(){return this.predicateToNegate;}

    @Override
    public boolean satisfies(GameWorldApi gameWorld) {
        return ! predicateToNegate.satisfies(gameWorld);
    }

    @Override
    public boolean hasPredicate() {
        return true;
    }

    @Override
    public String toString() {
        return NotBlock.class.getSimpleName();
    }

    @Override
    public String getName() {
        return "Not";
    }

    @Override
    public ProgramBlock copy() {
        return new NotBlock();
    }

    @Override
    public void add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        //TODO : to implement
    }
}
