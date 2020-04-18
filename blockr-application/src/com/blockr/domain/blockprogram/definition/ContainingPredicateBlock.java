package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.definition.location.ProgramLocation;

public interface ContainingPredicateBlock {
    PredicateBlock getPredicate();
    void setPredicate(PredicateBlock predicate);
    void removePredicate(PredicateBlock predicate);

    ProgramLocation getLocation(PredicateBlock predicateBlock);
}
