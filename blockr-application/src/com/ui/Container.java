package com.ui;

import java.util.List;

/**
 * this class a container which contains component object, called children and has a function that gets the windowregion of one of its childs
 */
public abstract class Container extends Component {

    public abstract List<? extends Component> getChildren();

    /**
     * this method calculates the region of a child in the given region of the container
     * @param region the region in which the method has to the child's windowRegion
     * @param child the child of which the windowRegion is asked
     */
    public abstract WindowRegion getChildRegion(WindowRegion region, Component child);
}
