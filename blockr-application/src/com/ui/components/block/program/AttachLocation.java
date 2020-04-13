package com.ui.components.block.program;

import java.util.Arrays;

public enum AttachLocation {
    PREVIOUS,
    NEXT,
    BODY,
    CONDITION,
    PREDICATE,
    NONE;

    public boolean isContainedIn(AttachLocation... attachLocations) {
        return Arrays.asList(attachLocations).contains(this);
    }
}