package com.blockr.domain.blockprogram.definition;

import org.junit.Test;

import static org.junit.Assert.*;

public class IfBlockTest {

    @Test
    public void getName() {
        assertEquals("If", new IfBlock().getName());
    }

    @Test
    public void testToString() {
        assertEquals("IfBlock StatementListBlock[]", new IfBlock().toString());
    }
}