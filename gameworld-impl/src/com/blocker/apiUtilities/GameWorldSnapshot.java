package com.blocker.apiUtilities;

import com.blocker.gameworld.domain.Location;

/**
 * The Game world snapshot class used for creating undo/redo mods in the client.
 */
public class GameWorldSnapshot implements Snapshot {
    /**
     * Get the location.
     *
     * @return the location
     */
    public Location getLocation(){return location;};
    private Location location;

    /**
     * Instantiates a new Game world snapshot.
     *
     * @param location the location of type Location
     */
    public GameWorldSnapshot(Location location) {
        this.location = location;
    }
}
