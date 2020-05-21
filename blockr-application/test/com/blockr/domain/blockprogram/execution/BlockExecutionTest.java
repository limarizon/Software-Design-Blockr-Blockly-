package com.blockr.domain.blockprogram.execution;

import com.blockr.domain.blockprogram.definition.IfBlock;
import com.blockr.domain.blockprogram.definition.StatementBlock;
import com.blockr.domain.blockprogram.definition.StatementListBlock;
import com.blockr.domain.blockprogram.definition.WhileBlock;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BlockExecutionTest extends AbstractBlockTest {

    @Test
    public void testRunStatementList(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        assertEquals(false, blockExecution.isStepping());
        blockExecution.step();
        verifyActionTriggered(0);
        assertEquals(true, blockExecution.isStepping());
        blockExecution.step();
        verifyActionTriggered(1);
    }

    @Test
    public void testResetBlockProgram(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0);
        blockExecution.step();
        verifyActionTriggered(1);
        blockExecution.reset();
        blockExecution.step();
        verifyActionTriggered(0,2);
        blockExecution.step();
        verifyActionTriggered(1, 2);
    }

    @Test
    public void testGetCurrentStep(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));
        WhileBlock whileBlock = new WhileBlock();
            whileBlock.setPredicate(gamePredicateBlock(0));
            whileBlock.addStatementBlock(gameActionBlock(1));
        statementListBlock.add(whileBlock);
        statementListBlock.add(gameActionBlock(1));
        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0);
        blockExecution.step();
        verifyActionTriggered(1);
        //its now the while block its turn
        Assert.assertEquals(true, blockExecution.isCurrentStep(statementListBlock.getStatements().get(2)));
    }

    @Test
    public void testRunStatementListAfterLastStatementDoesNotThrowErrorWhenSteppingContinues(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        statementListBlock.add(gameActionBlock(1));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0);

        blockExecution.step();
        verifyActionTriggered(1);

        blockExecution.step();
        blockExecution.step();
    }

    @Test
    public void testRunStatementWithIfList(){
        expectPredicateToReturn(0,true);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
            IfBlock ifBlock = new IfBlock();
            ifBlock.setPredicate(gamePredicateBlock(0));
            ifBlock.addStatementBlock(gameActionBlock(1));
            ifBlock.addStatementBlock(gameActionBlock(2));
            StatementBlock if2 = ifBlock.copy();
        statementListBlock.add(ifBlock);
        statementListBlock.add(gameActionBlock(3));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0);

        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(1);
        assertEquals(false, ifBlock.isNextStepToExecute(1, gameActionBlock(1)));

        blockExecution.step();
        verifyActionTriggered(2);

        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(3);
    }


    @Test
    public void testRunStatementWithIfListNotSatisfied(){
        expectPredicateToReturn(0,false);

        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
                IfBlock ifBlock = new IfBlock();
                ifBlock.setPredicate(gamePredicateBlock(0));
                ifBlock.addStatementBlock(gameActionBlock(1));
                ifBlock.addStatementBlock(gameActionBlock(2));
        statementListBlock.add(ifBlock);
        statementListBlock.add(gameActionBlock(3));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0);

        blockExecution.step();

        blockExecution.step();
        verifyActionTriggered(3);
    }

    @Test
    public void testRunStatementWithWhileCondition(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
            WhileBlock whileBlock = new WhileBlock();
            whileBlock.setPredicate(gamePredicateBlock(0));
            whileBlock.addStatementBlock(gameActionBlock(1));
            whileBlock.addStatementBlock(gameActionBlock(2));
        statementListBlock.add(whileBlock);
        statementListBlock.add(gameActionBlock(3));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0, 1);

        expectPredicateToReturn(0, true);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(1,1);
        blockExecution.step();
        verifyActionTriggered(2, 1);

        expectPredicateToReturn(0, true);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(1, 2);
        blockExecution.step();
        verifyActionTriggered(2, 2);

        expectPredicateToReturn(0, true);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(1, 3);
        blockExecution.step();
        verifyActionTriggered(2, 3);

        expectPredicateToReturn(0, false);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(3, 1);

        blockExecution.step();
        blockExecution.step();
    }

    @Test
    public void testRunStatementWithWhileConditionFalsePredicateToStart(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        WhileBlock whileBlock = new WhileBlock();
        whileBlock.setPredicate(gamePredicateBlock(0));
        whileBlock.addStatementBlock(gameActionBlock(1));
        whileBlock.addStatementBlock(gameActionBlock(2));
        statementListBlock.add(whileBlock);
        statementListBlock.add(gameActionBlock(3));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0, 1);

        expectPredicateToReturn(0, false);
        blockExecution.step();
        blockExecution.step();

        verifyActionTriggered(3, 1);
        verifyActionTriggered(1, 0);
        verifyActionTriggered(2, 0);

        blockExecution.step();
        blockExecution.step();
    }

    @Test
    public void testRunStatementWithWhileAndInsideIfCondition(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        WhileBlock whileBlock = new WhileBlock();
            whileBlock.setPredicate(gamePredicateBlock(0));
            whileBlock.addStatementBlock(gameActionBlock(1));
                IfBlock ifBlock = new IfBlock();
                    ifBlock.setPredicate(gamePredicateBlock(1));
                    ifBlock.addStatementBlock(gameActionBlock(2));
            whileBlock.addStatementBlock(ifBlock);
            whileBlock.addStatementBlock(gameActionBlock(3));
        statementListBlock.add(whileBlock);

        statementListBlock.add(gameActionBlock(4));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0,1);

        expectPredicateToReturn(0, true);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(1, 1);
        expectPredicateToReturn(1, false);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(3, 1);

        expectPredicateToReturn(0, true);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(1, 2);
        expectPredicateToReturn(1, false);
        blockExecution.step();
        blockExecution.step();
        expectPredicateToReturn(0, true);
        verifyActionTriggered(3, 2);

        expectPredicateToReturn(0, false);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(4,1);
    }

    @Test
    public void testRunStatementWithWhileBlockOnTopAndMakeMultipleSteps(){
        var statementListBlock = new StatementListBlock();
        statementListBlock.add(gameActionBlock(0));
        WhileBlock whileBlock = new WhileBlock();
            whileBlock.setPredicate(gamePredicateBlock(0));
            whileBlock.addStatementBlock(gameActionBlock(1));
        statementListBlock.add(whileBlock);

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        blockExecution.step();
        verifyActionTriggered(0,1);

        expectPredicateToReturn(0, true);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(1,1);
        expectPredicateToReturn(0, false);
        verifyActionTriggered(1,1);
        blockExecution.step();

        blockExecution.step();
        blockExecution.step();
    }

    @Test
    public void testRunStatementWithOnlyWhileBlockOnTopAndMakeMultipleSteps(){
        var statementListBlock = new StatementListBlock();
        WhileBlock whileBlock = new WhileBlock();
            whileBlock.setPredicate(gamePredicateBlock(0));
            whileBlock.addStatementBlock(gameActionBlock(0));
        statementListBlock.add(whileBlock);

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        expectPredicateToReturn(0, true);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(0,1);
        expectPredicateToReturn(0, true);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(0,2);
        expectPredicateToReturn(0, false);
        blockExecution.step();

        blockExecution.step();
        blockExecution.step();
    }

    @Test
    public void testRunStatementWithWhileBlockOnTopAndStepAfterwards(){
        var statementListBlock = new StatementListBlock();
        WhileBlock whileBlock = new WhileBlock();
        whileBlock.setPredicate(gamePredicateBlock(0));
        whileBlock.addStatementBlock(gameActionBlock(0));
        statementListBlock.add(whileBlock);
        statementListBlock.add(gameActionBlock(1));

        var blockExecution = new BlockExecution(statementListBlock, gameWorldApi);
        expectPredicateToReturn(0, true);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(0,1);
        expectPredicateToReturn(0, true);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(0,2);
        expectPredicateToReturn(0, false);
        blockExecution.step();
        blockExecution.step();
        verifyActionTriggered(1,1);
        verifyActionTriggered(0,2);

        blockExecution.step();
        blockExecution.step();
    }

}