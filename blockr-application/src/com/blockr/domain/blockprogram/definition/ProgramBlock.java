package com.blockr.domain.blockprogram.definition;

public interface ProgramBlock {

    String getName();

    default void add(ProgramBlock blockToAdd){}
}
