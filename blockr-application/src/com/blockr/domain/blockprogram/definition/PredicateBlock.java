package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;

public interface PredicateBlock extends ProgramBlock {
    default void setParent(ContainingPredicateBlock parent) {parent.setPredicate(this);}

    boolean satisfies(GameWorldApi gameWorldApi);
    boolean hasSubPredicate();

    default boolean isStatementBlock(){return false;}
}
