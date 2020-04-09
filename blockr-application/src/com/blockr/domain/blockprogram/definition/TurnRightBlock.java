package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

public class TurnRightBlock implements StatementBlock {

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushSnapshot();
        executionCallStack.getGameWorld().turnRight();
    }

    @Override
    public StatementBlock copy() {
        return new TurnRightBlock();
    }

    @Override
    public String getName() {
        return "Turn Right";
    }

    @Override
    public void add(StatementBlock blockToAdd, AttachLocation attachLocation) {
        //TODO: nog te implementeren
    }

    @Override
    public String toString() {
        return TurnRightBlock.class.getSimpleName();
    }
}
