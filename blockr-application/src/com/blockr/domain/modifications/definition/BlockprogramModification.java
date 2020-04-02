package com.blockr.domain.modifications.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.definition.StatementListBlock;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public class BlockprogramModification implements Modification {
    private StatementListBlock snapshot; //with to many snapshot saved, the memory gets full, so we limit it

    public BlockprogramModification(StatementListBlock snapshot) {
        this.snapshot = snapshot;
    }


    @Override
    public void undo(GameWorldApi gameWorldApi, ExecutionCallStack stack) {
        //first save the snapshot of the current (statementListblock) blockprogram : get it from blockprogram
        //replace the current blockprogram with the snapshot in this modification
        //set the saved previous blockprogram in the snapshot of this modification : get it from local variable
    }

    @Override
    public void redo(GameWorldApi gameWorldApi, ExecutionCallStack stack) {
        //first save the snapshot of the current (statementListblock) blockprogram : get it from blockprogram
        //replace the current blockprogram with the snapshot in this modification
        //set the saved previous blockprogram in the snapshot of this modification : get it from local variable
    }
}
