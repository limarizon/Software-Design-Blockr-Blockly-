package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;

public class NotBlock implements PredicateBlock {

    private PredicateBlock predicateToNegate;

    public void setPredicateToNegate(PredicateBlock predicateToNegate) {
        this.predicateToNegate = predicateToNegate;
    }

    @Override
    public boolean satisfies(GameWorldApi gameWorld) {
        return ! predicateToNegate.satisfies(gameWorld);
    }

    @Override
    public String toString() {
        return NotBlock.class.getSimpleName();
    }

    @Override
    public String getName() {
        return "Not";
    }
}
