package com.blocker.gameworld.domain;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;


public class RobotOrientationAndPositionTest {
    @Test
    public void equalsTest(){
        assertEquals(new GridPosition(2,2),new GridPosition(2,2));
        assertNotEquals(new GridPosition(2,2),new GridPosition(3,3));
    }
    @Test
    public void hashcodeTest(){
        assertEquals(new GridPosition(2,2).hashCode(),new GridPosition(2,2).hashCode());
    }
    @Test
    public void translateTest(){
        assertEquals(new GridPosition(2,2).translate(new Orientation.Offset(1, 1)),new GridPosition(3,3));
    }

    @Test
    public void turnleftTest(){
        assertEquals(Orientation.NORTH.turnLeft(),Orientation.WEST);
    }
}
