package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.AttachLocation;

public class TurnLeftBlock implements StatementBlock {

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.getGameWorld().turnLeft();
    }

    @Override
    public String getName() {
        return "Turn Left";
    }

    @Override
    public void add(StatementBlock blockToAdd, AttachLocation attachLocation) {
        //TODO: nog te implementeren
    }

    @Override
    public String toString() {
        return TurnLeftBlock.class.getSimpleName();
    }
}
