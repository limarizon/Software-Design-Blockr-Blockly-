package com.ui.components.block.program;

import java.util.Arrays;

/**
 * The enum Attach location.
 * This class is used to define the connection type between blocks in the Program Area
 */
public enum AttachLocation {
    /**
     * Previous attach location.
     */
    PREVIOUS,
    /**
     * Next attach location.
     */
    NEXT,
    /**
     * Body attach location.
     */
    BODY,
    /**
     * Condition attach location.
     */
    CONDITION,
    /**
     * None attach location.
     */
    NONE;

    /**
     * isContainedIn boolean. Needed to check whether a valid AttachLocation was provided to a block needed to be added
     * to another block which only accepts certain types of AttachLocations.
     *
     * @param attachLocations the attach locations
     * @return true if the AttachLocation is one of the given arguments.
     */
    public boolean isContainedIn(AttachLocation... attachLocations) {
        return Arrays.asList(attachLocations).contains(this);
    }
}