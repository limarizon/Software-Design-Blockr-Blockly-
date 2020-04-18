package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;

public interface PredicateBlock extends ProgramBlock {
    void setParent(ContainingPredicateBlock parent);

    boolean satisfies(GameWorldApi gameWorldApi);
    boolean hasSubPredicate();

    default boolean isStatementBlock(){return false;}
}
