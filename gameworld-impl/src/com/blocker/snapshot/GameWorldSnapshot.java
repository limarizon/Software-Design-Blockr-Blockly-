package com.blocker.snapshot;

import com.blocker.gameworld.domain.Location;
import com.blocker.snapshot.api.Snapshot;

public class GameWorldSnapshot implements Snapshot {
    public Location getLocation(){return location;};
    private Location location;

    public GameWorldSnapshot(Location location) {
        this.location = location;
    }
}
