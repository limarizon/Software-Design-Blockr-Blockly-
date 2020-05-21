package com.blocker.gameworld.domain;

import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class GameWorldTypeTest {
    private RobotGameWorldType gt=new RobotGameWorldType();
    @Test
    public void getActionsTest() {
       assertEquals (gt.getActions().size(),3);
    }

    @Test
    public void getPredicatesTest() {
        assertEquals(gt.getPredicates().size(),2);
    }

    @Test
    public void createGameWorldInstanceTest() {
    assertNotEquals(gt.createGameWorldInstance(),null);
    }
}
