package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.AttachLocation;

public class MoveForwardBlock implements StatementBlock {

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushSnapshot();
        executionCallStack.getGameWorld().moveForward();
    }

    @Override
    public String getName() {
        return "Move Forward";
    }

    @Override
    public void add(StatementBlock blockToAdd, AttachLocation attachLocation) {
        //TODO: nog te implementeren
    }

    @Override
    public String toString() {
        return MoveForwardBlock.class.getSimpleName();
    }
}
