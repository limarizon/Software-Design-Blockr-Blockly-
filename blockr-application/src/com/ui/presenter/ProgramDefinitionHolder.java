package com.ui.presenter;

import com.blockr.domain.blockprogram.definition.FunctionDefinitionBlock;
import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.StatementListBlock;
import com.ui.components.block.program.AttachLocation;
import com.ui.presenter.command.CommandFactory;
import com.ui.presenter.command.ProgramModificationCommand;

import java.util.Stack;

/**
 * This class creates ui commands in order to control the Program Area environment correctly.
 * It implements the command pattern in order to allow for undo and redo functionality of commands which have been executed successfully prior.
 */
public class ProgramDefinitionHolder {
    public StatementListBlock programDefinition = new StatementListBlock();
    public FunctionDefinitionBlock functionDefinitionBlock = new FunctionDefinitionBlock();

    public void setProgramDefinition(StatementListBlock programDefinition) {
        this.programDefinition = programDefinition;
    }

    public StatementListBlock getProgramDefinition() {
        return programDefinition;
    }

    public void setFunctionDefinitionBlock(FunctionDefinitionBlock functionDefinitionBlock) {
        this.functionDefinitionBlock = functionDefinitionBlock;
    }

    public FunctionDefinitionBlock getFunctionDefinition() {
        return functionDefinitionBlock;
    }
}
