package com.ui.presenter.command;

public class EmptyCommand implements ProgramCreationCommand{
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
