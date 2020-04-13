package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

import java.util.ArrayList;
import java.util.List;

public class StatementListBlock implements ControlFlowBlock{
    private List<StatementBlock> statements = new ArrayList<>();
    private ControlFlowBlock parent;

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
    public StatementBlock copy() {
        return new StatementListBlock();
    }

    @Override
    public void add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        if(!blockToAdd.isStatementBlock()) return;

        switch (attachLocation){
            case BODY:
                addStatement((StatementBlock) blockToAdd, statements.size());
                break;
        }
    }

    @Override
    public void removeStatement() {

    }

    @Override
    public void removePredicate(PredicateBlock predicate) {
        //do nothing
    }

    @Override
    public void setParent(ControlFlowBlock parent) {
        this.parent = parent;
    }

    @Override
    public void addToStatementList(StatementBlock blockToAdd, StatementBlock referencedBlock, AttachLocation attachLocation) {
        if(! attachLocation.isContainedIn(AttachLocation.PREVIOUS, AttachLocation.NEXT)) return;

        var indexReference = statements.indexOf(referencedBlock);
        if(attachLocation == AttachLocation.NEXT){
            indexReference++;
        }
        addStatement(blockToAdd, indexReference);
    }

    @Override
    public void removeFromStatementList(StatementBlock statementBlock) {
        statementBlock.setParent(null);
        statements.remove(statementBlock);
    }

    private void addStatement(StatementBlock blockToAdd, int indexReference) {
        statements.add(indexReference, blockToAdd);
        blockToAdd.setParent(this);
    }

    @Override
    public String getName() {
        return "List";
    }

    private boolean wasLastStatement(int executedLineNumber) {
        return statements.size()-1 == executedLineNumber;
    }

    public void add(StatementBlock statementBlock) {
        addStatement(statementBlock, 0);
    }

    @Override
    public String toString() {
        return statements.toString();
    }

    @Override
    public StatementListBlock getStatementListBlock() {
        return this;
    }

    @Override
    public PredicateBlock getPredicate() {
        return null;
    }

    public List<StatementBlock> getStatements(){
        return statements;
    }

    public boolean isEmpty() {
        return statements.isEmpty();
    }
}
