package com.blocker.gameworld.domain;
import com.blocker.apiUtilities.actions.MoveForwardAction;
import com.blocker.apiUtilities.actions.TurnLeftAction;
import com.blocker.apiUtilities.actions.TurnRightAction;
import com.blocker.apiUtilities.predicates.GoalisReachedPredicate;
import com.blocker.apiUtilities.predicates.RobotIsFacingAWallPredicate;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.*;

public class ApiUtilitiesTest {

    @Test
    public void evaluateTest(){
        RobotGameWorld game = mock(RobotGameWorld.class);
        var gir = new GoalisReachedPredicate(game);
        var risfw = new RobotIsFacingAWallPredicate(game);
        gir.evaluate();
        verify(game,times(1)).isGoalReached();
        risfw.evaluate();
        verify(game,times(1)).isFacingAWall();
    }
    @Test
    public void executeTest(){
       RobotGameWorld game = mock(RobotGameWorld.class);
        var mf = new MoveForwardAction(game);
        var tl = new TurnLeftAction(game);
        var tr = new TurnRightAction(game);
        mf.execute();
        verify(game,times(1)).moveForward();
        tl.execute();
        verify(game,times(1)).turnLeft();
        tr.execute();
        verify(game,times(1)).turnRight();
    }

    @Test
    public void getNameA(){
        RobotGameWorld game = mock(RobotGameWorld.class);
        Assert.assertEquals("MoveForward", (new MoveForwardAction(game)).getName());
        Assert.assertEquals("Turn Left", (new TurnLeftAction(game)).getName());
        Assert.assertEquals("Turn Right", (new TurnRightAction(game)).getName());
    }

    @Test
    public void getNameP(){
        RobotGameWorld game = mock(RobotGameWorld.class);
        Assert.assertEquals("Wall In F.", (new RobotIsFacingAWallPredicate(game)).getName());
        Assert.assertEquals("On Goal", (new GoalisReachedPredicate(game)).getName());

    }

}
