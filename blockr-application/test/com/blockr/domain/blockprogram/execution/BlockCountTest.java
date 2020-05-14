package com.blockr.domain.blockprogram.execution;

import com.blockr.domain.blockprogram.definition.IfBlock;
import com.blockr.domain.blockprogram.definition.StatementsListBlock;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockCountTest extends BlockTest{

    @Test
    public void testCountStatementList(){
        var statementListBlock = new StatementsListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));

        assertEquals(2, statementListBlock.countBlocks());
    }

    @Test
    public void testCountStatementWithIfList(){
        var statementListBlock = new StatementsListBlock();
        statementListBlock.add(gameActionBlock(0));
            IfBlock ifBlock = new IfBlock();
            ifBlock.setPredicate(gamePredicateBlock(0));
            ifBlock.addStatementBlock(gameActionBlock(1));
            ifBlock.addStatementBlock(gameActionBlock(2));
        statementListBlock.add(ifBlock);
        statementListBlock.add(gameActionBlock(3));

        assertEquals(6, statementListBlock.countBlocks());
    }

}