package com.ui.components.container.div;

import com.ui.Component;
import com.ui.WindowRegion;

import java.awt.*;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * The Div component class
 */
public class DivComponent extends com.ui.Container {

    /**
     * The constant DEFAULT_MARGIN.
     */
    public static Margin DEFAULT_MARGIN = new Margin(0);
    /**
     * The constant DEFAULT_BORDER.
     */
    public static Border DEFAULT_BORDER = new Border(Color.BLACK, 0);
    /**
     * The constant DEFAULT_PADDING.
     */
    public static Padding DEFAULT_PADDING = new Padding(5);

    public List<com.ui.Component> getChildren() {
        return Collections.unmodifiableList(children);
    }

    private List<com.ui.Component> children;

    /**
     * Get margin.
     *
     * @return the margin
     */
    public Margin getMargin(){
        return margin;
    }

    private final Margin margin;

    /**
     * Get the border.
     *
     * @return the border
     */
    public Border getBorder(){
        return border;
    }

    private final Border border;

    /**
     * Get padding.
     *
     * @return the padding
     */
    public Padding getPadding(){
        return padding;
    }

    private final Padding padding;

    /**
     * Get flex axis.
     *
     * @return the flex axis
     */
    public FlexAxis getFlexAxis(){
        return flexAxis;

    }

    private FlexAxis flexAxis;

    private DivComponent(List<com.ui.Component> children, Margin margin, Border border, Padding padding, FlexAxis flexAxis){

        throwIfNull(margin, "margin");
        throwIfNull(border, "border");
        throwIfNull(padding, "padding");
        throwIfNull(flexAxis, "flexAxis");
      
        this.children = new LinkedList<>(children);

        this.margin = margin;
        this.border = border;
        this.padding = padding;
        this.flexAxis = flexAxis;
    }

    private void throwIfNull(Object object, String name){
        if(object != null)
            return;

        throw new IllegalArgumentException(String.format("%s must be effective", name));
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
    public WindowRegion getChildRegion(WindowRegion region, com.ui.Component child) {

        region = getContentRegion(region);

        var childIndex = getChildren().indexOf(child);

        if(childIndex == -1){
            throw new IllegalArgumentException("The given component is not a child of this component");
        }

        var flexHorizontal = getFlexAxis() == FlexAxis.Horizontal;

        var axisLength = flexHorizontal ? region.getWidth() : region.getHeight();
        var childLength = axisLength / getChildren().size();

        if(flexHorizontal){
            return new WindowRegion(
                    region.getMinX() + childIndex * childLength,
                    region.getMinY(),
                    region.getMinX() + childIndex* childLength + childLength,
                    region.getMaxY());
        }

        return new WindowRegion(
                region.getMinX(),
                region.getMinY() + childIndex * childLength,
                region.getMaxX(),
                region.getMinY() + childIndex * childLength + childLength
        );
    }

    private WindowRegion getContentRegion(WindowRegion region){

        var props = Arrays.asList(getMargin(), getBorder(), getPadding());

        for(var prop : props){

            if(region.isEmpty()){
                return WindowRegion.EMPTY;
            }

            region = region.shrinkRegion(prop);
        }

        return region;
    }

    /**
     * This method will draw the component from which it is called from
     * @param graphics java Graphics object
     */
    public void draw(Graphics graphics) {
      
        var region = WindowRegion.fromGraphics(graphics);

        //margin
        region = region.shrinkRegion(margin);
        graphics = shrinkGraphics(graphics, region);

        //border
        graphics.setColor(getBorder().getColor());

        graphics.fillRect(0, 0, region.getWidth(), getBorder().getTop());
        graphics.fillRect(region.getWidth() - getBorder().getRight(), 0, getBorder().getRight(), region.getHeight());
        graphics.fillRect(0, region.getHeight() - getBorder().getBottom(), region.getWidth(), getBorder().getBottom());
        graphics.fillRect(0, 0, getBorder().getLeft(), region.getHeight());
    }

    private Graphics shrinkGraphics(Graphics graphics, WindowRegion region){
        return graphics.create(region.getMinX(), region.getMinY(), region.getWidth(), region.getHeight());
    }

    /**
     * Builder for building the component and its properties/children.
     *
     * @return the builder
     */
    public static Builder builder(){
        return new Builder();
    }

    /**
     * The builder class which creates a class needed for when building a Ui component.
     * All the ui components will have its properties like margins, borders, padding etc set to Default values at creation unless changed afterwards.
     *
     */
    public static class Builder {

        private Margin margin = DEFAULT_MARGIN;

        private Border border = DEFAULT_BORDER;

        private Padding padding = DEFAULT_PADDING;

        private FlexAxis flexAxis = FlexAxis.Horizontal;

        private List<com.ui.Component> children = new LinkedList<>();

        /**
         * change the margin of the Builder, given a margin.
         *
         * @param margin the margin
         * @return the builder
         */
        public Builder withMargin(Margin margin){
            this.margin = margin;
            return this;
        }

        /**
         * change the border of the Builder, given a border.
         *
         * @param border the border
         * @return the builder
         */
        public Builder withBorder(Border border){
            this.border = border;
            return this;
        }

        /**
         * change the padding of the Builder, given a padding.
         *
         * @param padding the padding
         * @return the builder
         */
        public Builder withPadding(Padding padding){
            this.padding = padding;
            return this;
        }

        /**
         * add child components to the Builder, given children.
         *
         * @param children the children
         * @return the builder
         */
        public Builder addChildren(Component... children){

            this.children.addAll(Arrays.asList(children));

            return this;
        }

        /**
         * change the flexAxis of the Builder, given a flexAxis.
         *
         * @param flexAxis the flex axis
         * @return the builder
         */
        public Builder withFlexAxis(FlexAxis flexAxis){
            this.flexAxis = flexAxis;
            return this;
        }

        /**
         * Build the div component.
         *
         * @return the div component with all its properties
         */
        public DivComponent build(){
            return new DivComponent(children, margin, border, padding, flexAxis);
        }
    }
}
