package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

import static com.ui.components.block.program.AttachLocation.NEXT;
import static com.ui.components.block.program.AttachLocation.PREVIOUS;

public class TurnLeftBlock implements StatementBlock {

    private ControlFlowBlock parent;

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushSnapshot();
        executionCallStack.getGameWorld().turnLeft();
    }

    @Override
    public StatementBlock copy() {
        return new TurnLeftBlock();
    }

    @Override
    public void add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        if(attachLocation.isContainedIn(NEXT, PREVIOUS) && blockToAdd.isStatementBlock()){
            parent.addToStatementList((StatementBlock) blockToAdd, this, attachLocation);
        }
    }

    @Override
    public void setParent(ControlFlowBlock parent) {
        this.parent = parent;
    }

    @Override
    public String getName() {
        return "Turn Left";
    }

    @Override
    public String toString() {
        return TurnLeftBlock.class.getSimpleName();
    }
}
