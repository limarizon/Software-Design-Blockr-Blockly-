package com.ui.presenter;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.ui.components.block.program.AttachLocation;
import com.ui.presenter.command.CommandFactory;
import com.ui.presenter.command.ProgramCreationCommand;

import java.util.Stack;

public class ProgramCreator {
    private CommandFactory draggingState = new CommandFactory();
    private Stack<ProgramCreationCommand> doneBlockActions = new Stack<>();
    private Stack<ProgramCreationCommand> unDoneBlockActions = new Stack<>();

    public void startDraggingFromPalette(ProgramBlock blockToAdd) {
        draggingState.startFromPalette(blockToAdd);
    }

    public void startDraggingFromProgramArea(ProgramBlock blockToAdd) {
        draggingState.startFromProgramArea(blockToAdd);
    }

    public void addToBlock(ProgramBlock destinationBlock, AttachLocation attachLocation) {
        if(draggingState.isDragging()){
            var command = draggingState.createCommand(destinationBlock, attachLocation);
            command.execute();
            doneBlockActions.push(command);
        }
    }



    public void undo(){
        ProgramCreationCommand toUndo = doneBlockActions.pop();
        toUndo.undoExecution();
        unDoneBlockActions.push(toUndo);
    }

    public void redo(){
        ProgramCreationCommand toRedo = unDoneBlockActions.pop();
        toRedo.execute();
        doneBlockActions.push(toRedo);
    }

}
