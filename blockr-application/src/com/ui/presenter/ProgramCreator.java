package com.ui.presenter;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;
import com.ui.presenter.command.CommandFactory;
import com.ui.presenter.command.ProgramModificationCommand;

import java.util.Stack;

public class ProgramCreator {
    private CommandFactory commandFactory = new CommandFactory();
    private Stack<ProgramModificationCommand> doneBlockActions = new Stack<>();
    private Stack<ProgramModificationCommand> unDoneBlockActions = new Stack<>();

    public void startDraggingFromPalette(ProgramBlock blockToAdd) {
        commandFactory.startFromPalette(blockToAdd);
    }

    public void startDraggingFromProgramArea(ProgramBlock blockToAdd) {
        commandFactory.startFromProgramArea(blockToAdd);
    }

    public void handleDraggingStoppedForAddingOrMoving(ProgramBlock destinationBlock, AttachLocation attachLocation) {
        execute(commandFactory.createAddOrMoveCommand(destinationBlock, attachLocation));
    }

    private void execute(ProgramModificationCommand command) {
        command.execute();
        doneBlockActions.push(command);
        if(command.isOriginalModification()){
            unDoneBlockActions.clear();
        }
    }

    public void handleDraggingStoppedForRemoval() {
        execute(commandFactory.createRemovalCommand());
    }

    public void undo(){
        ProgramModificationCommand toUndo = doneBlockActions.pop();
        toUndo.undoExecution();
        unDoneBlockActions.push(toUndo);
    }

    public void redo(){
        ProgramModificationCommand toRedo = unDoneBlockActions.pop();
        toRedo.execute();
        doneBlockActions.push(toRedo);
    }


}
