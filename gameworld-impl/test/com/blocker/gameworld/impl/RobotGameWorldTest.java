package com.blocker.gameworld.impl;

import com.blocker.gameworld.api.GameWorldApi;
import com.blocker.gameworld.domain.RobotGameWorld;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;


public class RobotGameWorldTest {
     //TO-DO more test on gameworld
    @Test
    public void testRunRestoreCreateSnapshotMoveForward(){
     var game = new RobotGameWorld();
     game.reset();
     var robotPos = game.getRobot().getPosition();
     var robotOr =  game.getRobot().getOrientation();
     var snap = game.createSnapshot();
     game.getRobot().moveForward();
     game.restore(snap);
        assertEquals(robotPos.getX(), game.getRobot().getPosition().getX());
        assertEquals(robotPos.getY(), game.getRobot().getPosition().getY());
        assertSame(robotOr, game.getRobot().getOrientation());
    }
    @Test
    public void testRunRestoreCreateSnapshotTurnLeft(){
        var game = new RobotGameWorld();
        game.reset();
        var robotPos = game.getRobot().getPosition();
        var robotOr =  game.getRobot().getOrientation();
        var snap = game.createSnapshot();
        game.getRobot().turnLeft();
        game.restore(snap);
        assertEquals(robotPos.getX(), game.getRobot().getPosition().getX());
        assertEquals(robotPos.getY(), game.getRobot().getPosition().getY());
        assertSame(robotOr, game.getRobot().getOrientation());
    }
    @Test
    public void testRunRestoreCreateSnapshotTurnRight(){
        var game = new RobotGameWorld();
        game.reset();
        var robotPos = game.getRobot().getPosition();
        var robotOr =  game.getRobot().getOrientation();
        var snap = game.createSnapshot();
        game.getRobot().turnRight();
        game.restore(snap);
        assertEquals(robotPos.getX(), game.getRobot().getPosition().getX());
        assertEquals(robotPos.getY(), game.getRobot().getPosition().getY());
        assertSame(robotOr, game.getRobot().getOrientation());
    }
    @Test
    public void testRunRestoreCreateSnapshotDoNothing(){
        var game = new RobotGameWorld();
        game.reset();
        var robotPos = game.getRobot().getPosition();
        var robotOr =  game.getRobot().getOrientation();
        var snap = game.createSnapshot();
        game.restore(snap);
        assertEquals(robotPos.getX(), game.getRobot().getPosition().getX());
        assertEquals(robotPos.getY(), game.getRobot().getPosition().getY());
        assertSame(robotOr, game.getRobot().getOrientation());
    }
    @Test
    public void testRunRestoreCreateSnapshotDoCombination(){
        var game = new RobotGameWorld();
        game.reset();
        var robotPos = game.getRobot().getPosition();
        var robotOr =  game.getRobot().getOrientation();
        var snap = game.createSnapshot();
        game.getRobot().moveForward();
        game.getRobot().turnLeft();
        game.getRobot().moveForward();
        game.restore(snap);
        assertEquals(robotPos.getX(), game.getRobot().getPosition().getX());
        assertEquals(robotPos.getY(), game.getRobot().getPosition().getY());
        assertSame(robotOr, game.getRobot().getOrientation());
    }
    @Test
    public void testRunRestoreCreateSnapshotMoveForwardApi(){
        var game = new RobotGameWorld();
        game.reset();
        var robotPos = game.getRobot().getPosition();
        var robotOr =  game.getRobot().getOrientation();
        var api = (GameWorldApi) game;
        var snap = api.createSnapshot();
        game.getRobot().moveForward();
        api.restore(snap);
        assertEquals(robotPos.getX(), game.getRobot().getPosition().getX());
        assertEquals(robotPos.getY(), game.getRobot().getPosition().getY());
        assertSame(robotOr, game.getRobot().getOrientation());
    }
    @Test
    public void testRunRestoreCreateSnapshotTurnLeftApi(){
        var game = new RobotGameWorld();
        game.reset();
        var robotPos = game.getRobot().getPosition();
        var robotOr =  game.getRobot().getOrientation();
        var snap = game.createSnapshot();
        game.getRobot().turnLeft();
        game.restore(snap);
        assertEquals(robotPos.getX(), game.getRobot().getPosition().getX());
        assertEquals(robotPos.getY(), game.getRobot().getPosition().getY());
        assertSame(robotOr, game.getRobot().getOrientation());
    }
    @Test
    public void testRunRestoreCreateSnapshotTurnRightApi(){
        var game = new RobotGameWorld();
        game.reset();
        var robotPos = game.getRobot().getPosition();
        var robotOr =  game.getRobot().getOrientation();
        var api = (GameWorldApi) game;
        var snap = api.createSnapshot();
        game.getRobot().turnRight();
        api.restore(snap);
        assertEquals(robotPos.getX(), game.getRobot().getPosition().getX());
        assertEquals(robotPos.getY(), game.getRobot().getPosition().getY());
        assertSame(robotOr, game.getRobot().getOrientation());
    }
    @Test
    public void testRunRestoreCreateSnapshotDoNothingApi(){
        var game = new RobotGameWorld();
        game.reset();
        var robotPos = game.getRobot().getPosition();
        var robotOr =  game.getRobot().getOrientation();
        var api = (GameWorldApi) game;
        var snap = api.createSnapshot();
        api.restore(snap);
        assertEquals(robotPos.getX(), game.getRobot().getPosition().getX());
        assertEquals(robotPos.getY(), game.getRobot().getPosition().getY());
        assertSame(robotOr, game.getRobot().getOrientation());
    }
    @Test
    public void testRunRestoreCreateSnapshotDoCombinationApi(){
        var game = new RobotGameWorld();
        game.reset();
        var robotPos = game.getRobot().getPosition();
        var robotOr =  game.getRobot().getOrientation();
        var api = (GameWorldApi) game;
        var snap = api.createSnapshot();
        game.getRobot().moveForward();
        game.getRobot().turnLeft();
        game.getRobot().moveForward();
        api.restore(snap);
        assertEquals(robotPos.getX(), game.getRobot().getPosition().getX());
        assertEquals(robotPos.getY(), game.getRobot().getPosition().getY());
        assertSame(robotOr, game.getRobot().getOrientation());
    }
}