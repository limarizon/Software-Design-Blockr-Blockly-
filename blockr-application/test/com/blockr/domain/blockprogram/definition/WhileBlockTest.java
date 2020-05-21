package com.blockr.domain.blockprogram.definition;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

public class WhileBlockTest {

    @Test
    public void getName() {
        assertEquals("While", new WhileBlock().getName());
    }

    @Test
    public void testToString() {
        assertEquals("WhileBlock StatementListBlock[]", new WhileBlock().toString());
    }

    @Test
    public void testCopy(){
        WhileBlock whileBlock = new WhileBlock();
        assertEquals(whileBlock.getClass(), whileBlock.copy().getClass());
    }
}