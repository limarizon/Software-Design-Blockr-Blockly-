package com.ui.presenter.command;

public class EmptyCommand implements ProgramModificationCommand {
    @Override
    public boolean isOriginalModification(){
        return false;
    }

    @Override
    public void execute() {

    }

    @Override
    public void undoExecution() {

    }
}
