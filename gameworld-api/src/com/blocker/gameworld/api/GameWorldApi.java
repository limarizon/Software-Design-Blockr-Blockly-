package com.blocker.gameworld.api;

import com.blocker.apiUtilities.Action;
import com.blocker.apiUtilities.Predicate;
import com.blocker.apiUtilities.Snapshot;

import java.awt.*;

public interface GameWorldApi {

    void draw(Graphics graphics);

    void reset();

    void restore(Snapshot snapshot);

    Snapshot createSnapshot();

    boolean perform(Action action);

    boolean evaluate(Predicate predicate);

    boolean isGoalReached();
}
