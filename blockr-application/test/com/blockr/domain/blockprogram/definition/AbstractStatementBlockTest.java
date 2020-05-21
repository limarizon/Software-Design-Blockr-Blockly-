package com.blockr.domain.blockprogram.definition;

import com.blocker.apiUtilities.Action;
import com.blocker.gameworld.api.GameWorldApi;
import com.ui.components.block.program.AttachLocation;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractStatementBlockTest {

    @Test
    public void addBodyAction() {
        AbstractStatementBlock statementBlock =  Mockito.mock(AbstractStatementBlock.class);
        //GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.NEXT;
        Action action = Mockito.mock(Action.class);
        StatementBlock statement = new GameActionBlock(action);

        StatementListBlock statements = new StatementListBlock();
        statements.add(statementBlock);
        statementBlock.add(statement, location);
        assertEquals(1, statements.getStatementListBlock().getStatements().size());

    }

    @Test
    public void removeBodyAction() {
        AbstractStatementBlock statementBlock =  Mockito.mock(AbstractStatementBlock.class);
        //GameWorldApi gameWorld = Mockito.mock(GameWorldApi.class);
        AttachLocation location = AttachLocation.NEXT;
        Action action = Mockito.mock(Action.class);
        StatementBlock statement = new GameActionBlock(action);

        StatementListBlock statements = new StatementListBlock();
        statements.add(statementBlock);
        statementBlock.add(statement, location);
        statementBlock.getLocation();
        assertEquals(1, statements.getStatementListBlock().getStatements().size());
        statementBlock.removeYourself();
        statementBlock.toString();
        assertEquals(1, statements.getStatementListBlock().getStatements().size());
    }
}