package com.blockr.domain.block;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.block.interfaces.markers.ReadOnlyNotBlock;

public class NotBlock extends ConditionBlock implements ReadOnlyNotBlock {

    public ConditionBlock getCondition(){
        return condition;
    }

    public void setCondition(ConditionBlock condition){
        this.condition = condition;
    }

    private ConditionBlock condition;

    @Override
    public boolean evaluate(GameWorldApi gameWorld) {
        return !getCondition().evaluate(gameWorld);
    }
}
