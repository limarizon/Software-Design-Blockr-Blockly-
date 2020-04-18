package com.blockr.domain.blockprogram.definition;

import com.blockr.domain.blockprogram.definition.location.ProgramLocation;
import com.blockr.domain.blockprogram.definition.location.StatementBlockLocation;
import com.blockr.domain.blockprogram.execution.ExecutionCallStack;
import com.ui.components.block.program.AttachLocation;

import java.util.ArrayList;
import java.util.List;

public class StatementListBlock implements ContainingStatementBlock, StatementBlock, SteppableBlock{
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
    public StatementBlock copy() {
        return new StatementListBlock();
    }

    @Override
    public boolean add(ProgramBlock blockToAdd, AttachLocation attachLocation) {
        if(!blockToAdd.isStatementBlock()) return false;

        if (attachLocation == AttachLocation.BODY) {
            addStatement((StatementBlock) blockToAdd, statements.size());
            return true;
        }
        return false;
    }

    @Override
    public void removeYourself() {
        //top-level list, niet nodig
    }

    @Override
    public ProgramLocation getLocation() {
        //top-level list, niet nodig
        return null;
    }

    @Override
    public void setParent(ContainingStatementBlock parent) {
        //top-level list, niet nodig
    }

    @Override
    public void addToStatementList(StatementBlock blockToAdd, StatementBlock referencedBlock, AttachLocation attachLocation) {
        if(! attachLocation.isContainedIn(AttachLocation.PREVIOUS, AttachLocation.NEXT)) return;

        var indexReference = getLineNumber(referencedBlock);
        if(attachLocation == AttachLocation.NEXT){
            indexReference++;
        }
        addStatement(blockToAdd, indexReference);
    }

    @Override
    public void addToStatementList(StatementBlock block, int lineNumber) {
        addStatement(block, lineNumber);
    }


    @Override
    public ProgramLocation getLocation(StatementBlock statementBlock) {
        return new StatementBlockLocation(this, getLineNumber(statementBlock));
    }

    public int getLineNumber(StatementBlock referencedBlock) {
        return statements.indexOf(referencedBlock);
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
        addStatement(statementBlock, statements.size());
    }

    @Override
    public String toString() {
        return statements.toString();
    }

    @Override
    public StatementListBlock getStatementListBlock() {
        return this;
    }

    public List<StatementBlock> getStatements(){
        return statements;
    }

    public boolean isEmpty() {
        return statements.isEmpty();
    }


}
