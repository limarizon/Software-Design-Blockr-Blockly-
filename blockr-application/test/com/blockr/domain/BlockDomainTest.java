package com.blockr.domain;

import com.blockr.domain.blockprogram.definition.*;
import com.blockr.domain.blockprogram.execution.BlockCountTest;
import com.blockr.domain.blockprogram.execution.BlockExecutionTest;
import com.blockr.domain.blockprogram.execution.BlockExecutionUndoRedoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mockito.internal.matchers.Not;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BlockCountTest.class,
        BlockExecutionTest.class,
        BlockExecutionUndoRedoTest.class,
        IfBlockTest.class,
        GameActionBlockTest.class,
        ControlFlowBlockTest.class,
        AbstractStatementBlockTest.class,
        FunctionCallBlockTest.class,
        FunctionDefinitionBlockTest.class,
        GamePredicateBlockTest.class,
        NotBlockTest.class,
        WhileBlockTest.class,
        GameStateTest.class
})
public class BlockDomainTest {
}
