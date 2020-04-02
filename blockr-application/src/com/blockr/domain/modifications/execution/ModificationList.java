package com.blockr.domain.modifications.execution;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.common.SafeProgrammingHelper;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.blockr.domain.modifications.definition.Modification;

import java.util.ArrayList;
import java.util.List;

public class ModificationList {

    private List<Modification> modList = new ArrayList<>();
    private GameWorldApi gameworld;

    public int getCurrentModToBeUndone() {
        return currentModToBeUndone;
    }

    public void setCurrentModToBeUndone(int currentModToBeUndone) {
        this.currentModToBeUndone = currentModToBeUndone;
    }

    private int currentModToBeUndone;

    public ModificationList(List<Modification> modList, GameWorldApi gameworld) {
        this.gameworld = gameworld;
        this.currentModToBeUndone=modList.size()-1;
    }

    public void addMod(Modification mod){ //everytime the F5 key is pushed a modification is made and added
                                          //everytime the blockprogram gets changed a modification is made and added
        SafeProgrammingHelper.throwIfNull(mod,mod.toString());
        modList.add(mod);
        setCurrentModToBeUndone(modList.size()-1);
    }
    public Modification getMod(int index){
        if(modList.size()>index && index>=0)
            throw new IllegalArgumentException();
        return modList.get(index);
    }
    public void clearModList(){
        modList.clear();
    }
    public void undo(ExecutionCallStack stack){
        if(currentModToBeUndone>=0) {
            modList.get(currentModToBeUndone).undo(gameworld,stack);
            currentModToBeUndone--;
        }
    }
    public void redo( ExecutionCallStack stack){
        if((currentModToBeUndone +1)< modList.size()){
          modList.get(currentModToBeUndone+1).redo(gameworld,stack);
          currentModToBeUndone++;
        }
    }

}
