package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;

public interface PredicateBlock extends ProgramBlock {
    boolean satisfies(GameWorldApi gameWorldApi);
    boolean hasPredicate();
    default boolean isStatementBlock(){return false;};
}
