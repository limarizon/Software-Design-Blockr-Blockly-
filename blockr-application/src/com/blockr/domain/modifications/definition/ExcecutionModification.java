package com.blockr.domain.modifications.definition;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

public class ExcecutionModification implements Modification {

    public ExcecutionModification() {

    }

    // snapshot of world

    @Override
    public String toString(){
        return null;
    }

    @Override
    public void undo(GameWorldApi gameWorldApi, ExecutionCallStack stack) {
        //first save the snapshot of the current world : get it from gameWorldApi
        //restore the current gameworld with the snapshot in this modification :restore function of gameworldApi
        //set the saved previous snapshot in the snapshot of this modification : get it from local variable
    }

    @Override
    public void redo(GameWorldApi gameWorldApi, ExecutionCallStack stack) {
        //first save the snapshot of the current world : get it from gameWorldApi
        //restore the current gameworld with the snapshot in this modification :restore function of gameworldApi
        //set the saved previous snapshot in the snapshot of this modification : get it from local variable
    }
}
