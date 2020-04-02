package com.ui.components.div;

/**
 * the margin is an invisible area around the border with the same width on each side
 */
public class Margin extends BoxProperty {

    public static final Margin ZERO_MARGIN = new Margin(0);

    public Margin(int top, int right, int bottom, int left) {
        super(top, right, bottom, left);
    }

    public Margin(int margin){
        super(margin);
    }
}
