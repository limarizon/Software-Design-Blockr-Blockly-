package com.blockr.domain;

import com.blockr.domain.blockprogram.definition.GameActionBlockTest;
import com.blockr.domain.blockprogram.definition.IfBlockTest;
import com.blockr.domain.blockprogram.execution.BlockCountTest;
import com.blockr.domain.blockprogram.execution.BlockExecutionTest;
import com.blockr.domain.blockprogram.execution.BlockExecutionUndoRedoTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        BlockCountTest.class,
        BlockExecutionTest.class,
        BlockExecutionUndoRedoTest.class,
        IfBlockTest.class,
        GameActionBlockTest.class
})
public class BlockDomainTest {
}
