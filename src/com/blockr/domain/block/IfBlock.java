package com.blockr.domain.block;

import com.blockr.domain.block.interfaces.Block;
import com.blockr.domain.block.interfaces.CompositeBlock;
import com.blockr.domain.block.interfaces.ReadOnlyStatementBlock;
import com.blockr.domain.block.interfaces.markers.ReadOnlyIfBlock;
import com.blockr.domain.gameworld.GameWorld;

public class IfBlock extends ControlFlowBlock implements ReadOnlyIfBlock {

    @Override
    public StatementBlock execute(GameWorld gameWorld) {

        if(getCurrent() == null){

            if(!getCondition().evaluate(gameWorld))
                return getNext();

            setCurrent(getBody());
        }

        var nextBodyStatement = getBody().execute(gameWorld);
        //for loop in loop
        if(!getCondition().evaluate(gameWorld)){setCurrent(null); return getNext();}

        if(nextBodyStatement == null){
            return getNext();
        }

        setCurrent(nextBodyStatement);
        return this;
    }

    @Override
    public void reset() {
        setCurrent(null);
    }

    @Override
    public ReadOnlyStatementBlock getActive() {
        return getCurrent() == null ? this : getCurrent();
    }
}
