package com.blockr.ui.components.block.graphics.BlockSizes;
import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.GameState;
import com.blockr.domain.blockprogram.definition.*;
import com.ui.UiMediator;
import com.ui.WindowPosition;
import com.ui.components.block.program.ProgramControlFlowBlockComponent;
import org.junit.Test;
import static org.junit.Assert.*;

import static org.mockito.Mockito.*;

public class CalculateBlockHeightTest {

    @Test
    public void testHeightStatementList(){
        var gameWorldApi = mock(GameWorldApi.class);
        var ProgramControlFlowBlockComponent = mock(ProgramControlFlowBlockComponent.class);
        var gameState = new GameState(gameWorldApi);
        var mediator = mock(UiMediator.class);
        var windowPos = mock(WindowPosition.class);
        var statementListBlock = new StatementListBlock();

        WhileBlock whileBlock = new WhileBlock();
        whileBlock.setPredicate(new WallInFrontBlock());
        whileBlock.addStatementBlock(new MoveForwardBlock());
        whileBlock.addStatementBlock(new TurnRightBlock());

        IfBlock ifBlock = new IfBlock();
        NotBlock notBlock = new NotBlock();
        notBlock.setPredicateToNegate(new WallInFrontBlock());
        ifBlock.setPredicate(notBlock);
        ifBlock.addStatementBlock(new TurnLeftBlock());

        whileBlock.addStatementBlock(ifBlock);

        statementListBlock.add(whileBlock);

        IfBlock otherIfBlock = new IfBlock();
        otherIfBlock.setPredicate(new WallInFrontBlock());
        otherIfBlock.addStatementBlock(new MoveForwardBlock());
        statementListBlock.add(otherIfBlock);


        var source =(ControlFlowBlock) statementListBlock.getStatements().get(0);
        var uiControlFlowBlockComponent = new ProgramControlFlowBlockComponent(gameState, source, mediator, windowPos);

        int expected = 200;
        //System.out.print(String.format("%d", (int)(BlockSizes.BLOCK_HEIGHT * 0.8f)));
        assertEquals(expected, uiControlFlowBlockComponent.getHeight());

    }
}
