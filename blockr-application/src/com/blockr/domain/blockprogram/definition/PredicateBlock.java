package com.blockr.domain.blockprogram.definition;

import com.blocker.gameworld.api.GameWorldApi;

public interface PredicateBlock {
    boolean satisfies(GameWorldApi gameWorldApi);
}
