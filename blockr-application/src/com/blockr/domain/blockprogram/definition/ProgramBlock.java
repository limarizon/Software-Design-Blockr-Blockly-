package com.blockr.domain.blockprogram.definition;

import com.ui.components.block.program.AttachLocation;

public interface ProgramBlock<PARENT extends ProgramBlock> {

    String getName();

    ProgramBlock copy();

    void add(ProgramBlock blockToAdd, AttachLocation attachLocation);

    boolean isStatementBlock();

    void setParent(PARENT parent);

    void removeStatement();
    default void removePredicate(PredicateBlock predicate){};

    default boolean isNot() {return false;};

}
