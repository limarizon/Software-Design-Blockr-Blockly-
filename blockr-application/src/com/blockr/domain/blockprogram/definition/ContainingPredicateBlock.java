package com.blockr.domain.blockprogram.definition;

public interface ContainingPredicateBlock {
    PredicateBlock getPredicate();
    void setPredicate(PredicateBlock predicate);
    void removePredicate(PredicateBlock predicate);
}
