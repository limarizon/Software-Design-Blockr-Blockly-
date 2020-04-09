package com.ui.presenter;

import com.blockr.domain.blockprogram.definition.ProgramBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;
import com.blockr.domain.blockprogram.definition.StatementListBlock;
import com.ui.components.block.program.AttachLocation;

public class ProgramCreator {
    private ProgramBlock blockToAdd;
    private StatementListBlock mainList = new StatementListBlock();
//    private Stack<BlockAction> doneBlockActions
 //   private Stack<BlockAction> unDoneBlockActions

    public void startDragging(ProgramBlock blockToAdd) {
        this.blockToAdd = blockToAdd;
    }

    public void addToBlock(ProgramBlock destinationBlock, AttachLocation attachLocation) {
        if(isDragging()){
            // can Add
            // add to Parent => via polymorphisme doen, maar dan moet block ook wel link naar parent hebben (zou misschien ook wel handig zijn voor iets anders)
            // of eerder canAdd doen
   //         BlockAction do = new BlockAction();
   //         blockActions.push(toDo)
            destinationBlock.add((StatementBlock)blockToAdd, attachLocation);
        }
    }

    public void undo(){
        //BlockAction toUndo = blockActions.pop()
        //toUndo.undo();

    }

    private boolean isDragging() {
        return blockToAdd != null;
    }
}
