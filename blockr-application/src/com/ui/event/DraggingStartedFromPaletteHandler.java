package com.ui.event;

import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.*;
import com.ui.presenter.ProgramCreationController;

public class DraggingStartedFromPaletteHandler implements UiEventHandler<DraggingStartedFromPaletteHandler.Command, Void> {

    private final ProgramCreationController programCreator;
    private final GameState gameState;

    public DraggingStartedFromPaletteHandler( GameState gameState, ProgramCreationController programCreator) {
        this.programCreator = programCreator;
        this.gameState = gameState;
    }

    @Override
    public Void handle(Command draggingStarted) {
        int i = 0;
        for(StatementBlock statementBlock : gameState.getProgramDefinition().getStatements()){
            if (statementBlock instanceof ControlFlowBlock){
                i += getStatementListBlockSize((ControlFlowBlock)statementBlock);
            }
            else{
                i += getStatementListBlockSize((GameActionBlock)statementBlock);
            }

        }
        if(this.gameState.getMaxBlocks() <= i){
            return null;
        }else {
            programCreator.startDraggingFromPalette(draggingStarted.blockToAdd);
            return null;
        }
    }

    public int getStatementListBlockSize(GameActionBlock statementBlock){return 1;}

    public int getStatementListBlockSize(ControlFlowBlock controlFlowBlock) {
        StatementListBlock statements = controlFlowBlock.getStatementListBlock();
        //Block itself counts as 1
        int counter = 1;


        //CFB has a body
        if (!statements.isEmpty()) {
            for (StatementBlock statement : statements.getStatements()) {
                if (statement instanceof ControlFlowBlock) {
                    counter += getStatementListBlockSize((ControlFlowBlock) statement);
                } else {
                    counter += getStatementListBlockSize((GameActionBlock) statement);
                }
            }
        }
        
        //check if cfb had predicates
        if (controlFlowBlock.getPredicate() != null) {
            counter++;
            if (controlFlowBlock.getPredicate().hasSubPredicate())
                counter++;
        }

        return counter;
    }

    public static class Command implements UiEvent<Void> {
        private final ProgramBlock blockToAdd;

        public Command(ProgramBlock blockToAdd) {
            this.blockToAdd = blockToAdd;
        }
    }
}
