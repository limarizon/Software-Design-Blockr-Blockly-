package com.blockr.domain.block;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.block.interfaces.ReadOnlyStatementBlock;
import com.blockr.domain.block.interfaces.markers.ReadOnlyWhileBlock;
import com.blockr.domain.blockprogram.definition.WallInFrontBlock;

public class WhileBlock extends ControlFlowBlock implements ReadOnlyWhileBlock {

    @Override
    public StatementBlock execute(GameWorldApi gameWorld) {

        if(getCurrent() == null){

            var result = getCondition().evaluate(gameWorld);

            if(!result)
                return getNext();

            setCurrent(getBody());
            return this;
        }
        //for loop in loop
        if(!getCondition().evaluate(gameWorld)){setCurrent(null); return getNext();}

        setCurrent(getCurrent().execute(gameWorld));

        return this;
    }

    @Override
    public void reset() {
        setCurrent(null);
    }

    @Override
    public ReadOnlyStatementBlock getActive() {
        return getCurrent() == null ? getBody() : getCurrent();
    }

    public void setPredicateBlock(WallInFrontBlock wallInFrontBlock) {

    }
}
