package com.blocker.gameworld.api;

import com.blocker.apiUtilities.Snapshot;

import java.awt.*;

public interface GameWorldApi {

    void draw(Graphics graphics);

    boolean moveForward();

    boolean turnLeft();

    boolean turnRight();

    boolean isFacingAWall();

    boolean isGoalReached();

    void reset();

    void restore(Snapshot snapshot);

    Snapshot createSnapshot();
}
