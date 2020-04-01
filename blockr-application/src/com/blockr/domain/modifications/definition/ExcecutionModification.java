package com.blockr.domain.modifications.definition;

import com.blocker.gameworld.api.GameWorldApi;

public class ExcecutionModification implements Modification {
    // snapshot of world
    @Override
    public void undo(GameWorldApi gameWorldApi) {
        //first save the snapshot of the current world : get it from gameWorldApi
        //restore the current gameworld with the snapshot in this modification :restore function of gameworldApi
        //set the saved previous snapshot in the snapshot of this modification : get it from local variable
    }

    @Override
    public void redo(GameWorldApi gameWorldApi) {
        //first save the snapshot of the current world : get it from gameWorldApi
        //restore the current gameworld with the snapshot in this modification :restore function of gameworldApi
        //set the saved previous snapshot in the snapshot of this modification : get it from local variable
    }

    @Override
    public String toString(){
        return null;
    }
}
