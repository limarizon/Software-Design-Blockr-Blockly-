package com.ui.components.container;

import com.ui.Component;
import com.ui.WindowPosition;
import com.ui.WindowRegion;
import com.ui.components.block.palette.DraggableStatementBlockComponent;
import com.ui.components.container.div.FlexAxis;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreePositionComponent extends com.ui.Container {

    private Map<Component, WindowRegion> children;
    private DraggableStatementBlockComponent draggable = new DraggableStatementBlockComponent( new WindowPosition(0, 0));

    public List<Component> getChildren() {
        return new ArrayList<>(children.keySet());
    }


    private FlexAxis flexAxis;

    public FreePositionComponent(){
        this.children = new HashMap<>();
    }

    /**
     * Returns the region where the given child component should be drawn
     *
     * @param   region
     *          The WindowRegion where this component is drawn
     * @param   child
     *          The given child
     * @return  A WindowRegion representing the region where the child should be drawn
     */
    public WindowRegion getChildRegion(WindowRegion region, Component child) {
        return children.get(child);
    }

    public void draw(Graphics graphics) {
        for(Map.Entry<Component, WindowRegion>  entry : children.entrySet()){
            entry.getKey().draw(graphics);
        }
    }

    public void putChild(Component child, WindowRegion windowRegion) {
        this.children.put(child, windowRegion);
    }

    public void moveDraggable(WindowPosition windowPosition) {
        draggable.setUpperLeft(windowPosition);
        putChild(draggable, draggable.getWindowRegion());
    }

    public void stopDraggable() {
        this.children.remove(draggable);
    }
}
