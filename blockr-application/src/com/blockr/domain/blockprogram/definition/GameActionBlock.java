package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Action;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

import static com.ui.components.block.program.AttachLocation.NEXT;
import static com.ui.components.block.program.AttachLocation.PREVIOUS;

public class GameActionBlock implements StatementBlock {

    private ContainingStatementBlock parent;
    private Action gameAction;

    public GameActionBlock(Action gameAction) {
        this.gameAction = gameAction;
    }

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushSnapshot();
        executionCallStack.getGameWorld().perform(gameAction);
    }

    @Override
    public StatementBlock copy() {
        return new GameActionBlock(gameAction);
    }

    @Override
    public void add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        if(attachLocation.isContainedIn(NEXT, PREVIOUS) && blockToAdd.isStatementBlock()){
            parent.addToStatementList((StatementBlock) blockToAdd, this, attachLocation);
        }
    }

    @Override
    public void removeYourself() {
        parent.removeFromStatementList(this);
    }

    @Override
    public void setParent(ContainingStatementBlock parent) {
        this.parent = parent;
    }

    @Override
    public String getName() {
        return gameAction.getName();
    }

    @Override
    public String toString() {
        return GameActionBlock.class.getSimpleName() + "[" + getName() + "]";
    }
}
