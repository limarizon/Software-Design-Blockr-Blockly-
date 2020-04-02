package com.ui.components.div;

/**
 * the padding of opvulling is the invisible area under the border with the same with on each side
 */
public class Padding extends BoxProperty {

    public Padding(int top, int right, int bottom, int left) {
        super(top, right, bottom, left);
    }

    public Padding(int padding){
        super(padding);
    }
}
