package com.ui.presenter;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.StatementListBlock;

public class ProgramCreator {
    private ProgramBlock blockToAdd;
    private StatementListBlock mainList = new StatementListBlock();

    public void startDragging(ProgramBlock blockToAdd) {
        this.blockToAdd = blockToAdd;
    }

    public void addToBlock(ProgramBlock destinationBlock) {
        if(blockToAdd != null){
            // can Add
            // add to Parent => via polymorphisme doen, maar dan moet block ook wel link naar parent hebben (zou misschien ook wel handig zijn voor iets anders)
            // of eerder canAdd doen
            destinationBlock.add(blockToAdd);
        }
    }
}
