package com.blocker.gameworld.impl;

import com.blocker.gameworld.api.GameWorldApi;

public class RobotGameWorld implements GameWorldApi {

    public static void main(String[] args) {
        System.out.println("test");
    }

    @Override
    public void moveForward() {

    }

    @Override
    public void turnLeft() {

    }

    @Override
    public void turnRight() {

    }

    @Override
    public void moveBackward() {

    }

    @Override
    public boolean isWallInFront() {
        return false;
    }
}
