package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;

import java.util.ArrayList;
import java.util.List;

public class StatementListBlock implements ControlFlowBlock{
    private List<StatementBlock> statements = new ArrayList<>();

    @Override
    public void step(ExecutionCallStack executionCallStack) {
        executionCallStack.pushFrame(this);
        int lineNumberToExecute = executionCallStack.getCurrentLineNumber();
        var statement = statements.get(lineNumberToExecute);
        statement.step(executionCallStack);
        if(executionCallStack.isCurrentFrame(this)){
            if(wasLastStatement(lineNumberToExecute)){
                executionCallStack.dropFrame();
            }else{
                executionCallStack.nextLineNumberCurrentFrame(++lineNumberToExecute);
            }
        }
    }

    @Override
    public String getName() {
        return "List";
    }

    private boolean wasLastStatement(int executedLineNumber) {
        return statements.size()-1 == executedLineNumber;
    }

    public void add(StatementBlock statementBlock) {
        statements.add(statementBlock);
    }

    @Override
    public String toString() {
        return statements.toString();
    }
}
