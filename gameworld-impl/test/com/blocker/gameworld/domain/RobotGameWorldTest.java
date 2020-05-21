package com.blocker.gameworld.domain;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.gameworld.api.GameWorldApi;
import com.blocker.gameworld.domain.grid.GameGrid;
import org.junit.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


public class RobotGameWorldTest {
    @Test
    public void getGridTest(){
        RobotGameWorld game = new RobotGameWorld();
        game.reset();
        assertEquals(game.getGrid().getClass(),new GameGrid().getClass());
    }
    @Test
    public void getGoalPositionTest(){
        RobotGameWorld game = new RobotGameWorld();
        game.reset();
        assertEquals(game.getGoalPosition().getClass(),new GridPosition(0,0).getClass());
    }
    @Test
    public void performAndEvaluateTest(){
        Action action = mock(Action.class);
        Predicate predicate = mock(Predicate.class);
        RobotGameWorld game = new RobotGameWorld();
        game.reset();
        game.perform(action);
        verify(action,times(1)).execute();
        game.evaluate(predicate);
        verify(predicate,times(1)).evaluate();
    }
    @Test
    public void moveforwardTestValid(){
        RobotGameWorld game = new RobotGameWorld();
        game.reset();
        var pos = game.getRobot().getPosition();
        var or =  game.getRobot().getOrientation();
        game.moveForward();
        assertEquals(game.getRobot().getLocation().getOrientation(),or);
        assertNotEquals(game.getRobot().getLocation().getGridPosition(),pos);
    }
    @Test
    public void moveforwardTestWall(){
        RobotGameWorld game = new RobotGameWorld();
        game.reset();
        game.moveForward();
        game.moveForward();
        game.moveForward();
        var pos = game.getRobot().getPosition();
        var or =  game.getRobot().getOrientation();
        game.moveForward();
        assertEquals(game.getRobot().getLocation().getGridPosition(),pos);
    }
    @Test
    public void moveforwardTestGoal(){
        RobotGameWorld game = new RobotGameWorld();
        game.reset();
        game.moveForward();
        game.moveForward();
        game.moveForward();
        game.turnRight();
        game.moveForward();
        game.moveForward();
        game.moveForward();
        var pos = game.getRobot().getPosition();
        game.moveForward();
        assertEquals(game.getRobot().getLocation().getGridPosition(),pos);
    }
    @Test
    public void turnLeftTestValid(){
        RobotGameWorld game = new RobotGameWorld();
        game.reset();
        var pos = game.getRobot().getPosition();
        var or =  game.getRobot().getOrientation();
        game.turnLeft();
        assertNotEquals(game.getRobot().getLocation().getOrientation(),or);
        assertEquals(game.getRobot().getLocation().getGridPosition(),pos);
    }
    @Test
    public void turnLeftTestGoal(){
        RobotGameWorld game = new RobotGameWorld();
        game.reset();
        game.moveForward();
        game.moveForward();
        game.moveForward();
        game.turnRight();
        game.moveForward();
        game.moveForward();
        game.moveForward();
        var or =  game.getRobot().getOrientation();
        game.turnLeft();
        assertEquals(game.getRobot().getLocation().getOrientation(),or);
    }
    @Test
    public void turnRightTestValid(){
        RobotGameWorld game = new RobotGameWorld();
        game.reset();
        var pos = game.getRobot().getPosition();
        var or =  game.getRobot().getOrientation();
        game.turnRight();
        assertNotEquals(game.getRobot().getLocation().getOrientation(),or);
        assertEquals(game.getRobot().getLocation().getGridPosition(),pos);
    }
    @Test
    public void turnRightTestGoal(){
        RobotGameWorld game = new RobotGameWorld();
        game.reset();
        game.moveForward();
        game.moveForward();
        game.moveForward();
        game.turnRight();
        game.moveForward();
        game.moveForward();
        game.moveForward();
        var or =  game.getRobot().getOrientation();
        game.turnRight();
        assertEquals(game.getRobot().getLocation().getOrientation(),or);
    }
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