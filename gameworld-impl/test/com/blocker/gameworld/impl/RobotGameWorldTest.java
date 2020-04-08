package com.blocker.gameworld.impl;

import com.blocker.gameworld.api.GameWorldApi;
import com.blocker.gameworld.domain.RobotGameWorld;
import com.blocker.snapshot.GameWorldSnapshot;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertTrue;


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
     assertTrue(robotPos.getX() == game.getRobot().getPosition().getX());
     assertTrue(robotPos.getY() == game.getRobot().getPosition().getY());
     assertTrue(robotOr == game.getRobot().getOrientation());
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
        assertTrue(robotPos.getX() == game.getRobot().getPosition().getX());
        assertTrue(robotPos.getY() == game.getRobot().getPosition().getY());
        assertTrue(robotOr == game.getRobot().getOrientation());
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
        assertTrue(robotPos.getX() == game.getRobot().getPosition().getX());
        assertTrue(robotPos.getY() == game.getRobot().getPosition().getY());
        assertTrue(robotOr == game.getRobot().getOrientation());
    }
    @Test
    public void testRunRestoreCreateSnapshotDoNothing(){
        var game = new RobotGameWorld();
        game.reset();
        var robotPos = game.getRobot().getPosition();
        var robotOr =  game.getRobot().getOrientation();
        var snap = game.createSnapshot();
        game.restore(snap);
        assertTrue(robotPos.getX() == game.getRobot().getPosition().getX());
        assertTrue(robotPos.getY() == game.getRobot().getPosition().getY());
        assertTrue(robotOr == game.getRobot().getOrientation());
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
        assertTrue(robotPos.getX() == game.getRobot().getPosition().getX());
        assertTrue(robotPos.getY() == game.getRobot().getPosition().getY());
        assertTrue(robotOr == game.getRobot().getOrientation());
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
        assertTrue(robotPos.getX() == game.getRobot().getPosition().getX());
        assertTrue(robotPos.getY() == game.getRobot().getPosition().getY());
        assertTrue(robotOr == game.getRobot().getOrientation());
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
        assertTrue(robotPos.getX() == game.getRobot().getPosition().getX());
        assertTrue(robotPos.getY() == game.getRobot().getPosition().getY());
        assertTrue(robotOr == game.getRobot().getOrientation());
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
        assertTrue(robotPos.getX() == game.getRobot().getPosition().getX());
        assertTrue(robotPos.getY() == game.getRobot().getPosition().getY());
        assertTrue(robotOr == game.getRobot().getOrientation());
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
        assertTrue(robotPos.getX() == game.getRobot().getPosition().getX());
        assertTrue(robotPos.getY() == game.getRobot().getPosition().getY());
        assertTrue(robotOr == game.getRobot().getOrientation());
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
        assertTrue(robotPos.getX() == game.getRobot().getPosition().getX());
        assertTrue(robotPos.getY() == game.getRobot().getPosition().getY());
        assertTrue(robotOr == game.getRobot().getOrientation());
    }
}