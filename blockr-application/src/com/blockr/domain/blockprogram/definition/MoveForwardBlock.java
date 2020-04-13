package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

import static com.ui.components.block.program.AttachLocation.NEXT;
import static com.ui.components.block.program.AttachLocation.PREVIOUS;

public class MoveForwardBlock implements StatementBlock {

    private ControlFlowBlock parent;

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushSnapshot();
        executionCallStack.getGameWorld().moveForward();
    }

    @Override
    public StatementBlock copy() {
        return new MoveForwardBlock();
    }

    @Override
    public void add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        if(attachLocation.isContainedIn(NEXT, PREVIOUS) && blockToAdd.isStatementBlock()){
            parent.addToStatementList((StatementBlock) blockToAdd, this, attachLocation);
        }
    }

    @Override
    public void removeFromProgram() {
        parent.removeFromStatementList(this);
    }

    @Override
    public void setParent(ControlFlowBlock parent) {
        this.parent = parent;
    }

    @Override
    public String getName() {
        return "Move Forward";
    }

    @Override
    public String toString() {
        return MoveForwardBlock.class.getSimpleName();
    }
}
