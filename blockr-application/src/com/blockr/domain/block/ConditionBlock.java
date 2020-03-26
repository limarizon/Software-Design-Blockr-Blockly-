package com.blockr.domain.block;

import com.blockr.domain.block.interfaces.markers.ReadOnlyConditionBlock;
import com.blockr.domain.gameworld.GameWorld;

public abstract class ConditionBlock implements ReadOnlyConditionBlock {

    public ControlFlowBlock getParent(){
        return this.parent;
    }
    public void setParent(ControlFlowBlock parent){
        this.parent = parent;
    }

    private ControlFlowBlock parent;

    public ConditionBlock getConditionParent(){
        return this.conditionParent;
    }
    public void setParent(ConditionBlock conditionParent){
        this.conditionParent = conditionParent;
    }

    private ConditionBlock conditionParent;

    public abstract boolean evaluate(GameWorld gameWorld);

}
