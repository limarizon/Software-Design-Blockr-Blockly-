package com.ui.presenter.command;

public interface ProgramModificationCommand {
    void execute();

    void undoExecution();
}
