package com.blockr.ui.components.block.graphics.BlockSizes;

import com.blocker.gameworldType.api.GameWorldTypeApi;
import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.*;
import com.blockr.domain.blockprogram.execution.AbstractBlockTest;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.components.block.program.ProgramControlFlowBlockComponent;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.mockito.Mockito.mock;

public class CalculateBlockHeightTest extends AbstractBlockTest {

    @Test
    public void testHeightStatementList(){
        var gameWorldApi = mock(GameWorldTypeApi.class);
        var ProgramControlFlowBlockComponent = mock(ProgramControlFlowBlockComponent.class);
        var gameState = new GameState(gameWorldApi);
        var mediator = mock(UiMediator.class);
        var windowPos = mock(WindowPosition.class);
        var statementListBlock = new StatementListBlock();

        WhileBlock whileBlock = new WhileBlock();
        whileBlock.setPredicate(gamePredicateBlock(0));
        whileBlock.addStatementBlock(gameActionBlock(0));
        whileBlock.addStatementBlock(gameActionBlock(1));

        IfBlock ifBlock = new IfBlock();
        NotBlock notBlock = new NotBlock();
        notBlock.setPredicateToNegate(gamePredicateBlock(1));
        ifBlock.setPredicate(notBlock);
        ifBlock.addStatementBlock(gameActionBlock(3));

        whileBlock.addStatementBlock(ifBlock);

        statementListBlock.add(whileBlock);

        IfBlock otherIfBlock = new IfBlock();
        otherIfBlock.setPredicate(gamePredicateBlock(2));
        otherIfBlock.addStatementBlock(gameActionBlock(4));
        statementListBlock.add(otherIfBlock);


        var source =(ControlFlowBlock) statementListBlock.getStatements().get(0);
        var uiControlFlowBlockComponent = new ProgramControlFlowBlockComponent(gameState, source, mediator, windowPos);

        int expected = 200;
        //System.out.print(String.format("%d", (int)(BlockSizes.BLOCK_HEIGHT * 0.8f)));
        Assertions.assertEquals(expected, uiControlFlowBlockComponent.getHeight());

    }

}
