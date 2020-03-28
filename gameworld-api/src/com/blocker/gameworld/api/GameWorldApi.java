package com.blocker.gameworld.api;

import java.awt.*;

public interface GameWorldApi {

    void drawOnCanvas(Graphics graphics);

    void moveForward();

    void turnLeft();

    void turnRight();

    boolean isFacingAWall();

    boolean isGoalReached();

    void reset();
}
