package com.blocker.gameworld.domain.robot;

import com.blocker.gameworld.domain.GridPosition;
import com.blocker.gameworld.domain.Location;
import com.blocker.gameworld.domain.Orientation;

public class Robot {
    private Location localisation;

    public Robot(Location startPosition) {
        this.localisation = startPosition;
    }

    public GridPosition getPosition() {
        return localisation.getGridPosition();
    }

    public Orientation getOrientation() {
        return localisation.getOrientation();
    }
}
