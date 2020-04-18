package com.ui.presenter.command;

public class EmptyCommand implements ProgramModificationCommand {
    @Override
    public boolean execute() {
        return false;
    }

    @Override
    public void undoExecution() {

    }
}
