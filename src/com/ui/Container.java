package com.ui;

import java.util.List;

/**
 * this class a container which contains component object, called children and has a function that gets the windowregion of one of its childs
 */
public abstract class Container extends Component {

    public abstract List<? extends Component> getChildren();

    /**
     * this method calculates the region of a child in the given rigion of the container
     * @param region
     * @param child
     * @return
     */
    public abstract WindowRegion getChildRegion(WindowRegion region, Component child);
}
