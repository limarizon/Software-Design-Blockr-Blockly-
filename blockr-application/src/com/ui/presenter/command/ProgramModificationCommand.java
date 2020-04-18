package com.ui.presenter.command;

public interface ProgramModificationCommand {
    boolean execute();

    void undoExecution();
}
