package com.blocker.gameworld.api;

public interface GameWorldApi {

    void moveForward();

    void turnLeft();

    void turnRight();

    void moveBackward();

    boolean isWallInFront();
}
