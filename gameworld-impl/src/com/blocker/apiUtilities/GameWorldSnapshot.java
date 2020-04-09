package com.blocker.apiUtilities;

import com.blocker.gameworld.domain.Location;

public class GameWorldSnapshot implements Snapshot {
    public Location getLocation(){return location;};
    private Location location;

    public GameWorldSnapshot(Location location) {
        this.location = location;
    }
}
