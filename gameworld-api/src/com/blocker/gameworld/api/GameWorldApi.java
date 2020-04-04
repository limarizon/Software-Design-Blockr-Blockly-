package com.blocker.gameworld.api;

import java.awt.*;

public interface GameWorldApi {

    void draw(Graphics graphics);

    void moveBackward();

    void moveForward();

    void turnLeft();

    void turnRight();

    boolean isFacingAWall();

    boolean isGoalReached();

    void reset();
}
