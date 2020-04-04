package com.ui.mouseevent;

import com.ui.WindowPosition;

import java.util.Arrays;

/**
 * in this mouseEvent, the windowsposition is extra instead of the int x and int y in the provided canvaswindow
 */
public class MouseEvent {

    public Type getType(){
        return type;
    }

    private final Type type;
    private final WindowPosition absoluteWindowPosition;
    private final WindowPosition relativeWindowPosition;

    public WindowPosition getAbsoluteWindowPosition() {
        return absoluteWindowPosition;
    }

    public MouseEvent(Type type, WindowPosition absoluteWindowPosition, WindowPosition relativeWindowPosition){
        this.type = type;
        this.absoluteWindowPosition = absoluteWindowPosition;
        this.relativeWindowPosition = relativeWindowPosition;
    }

    public WindowPosition getRelativePosition() {
        return relativeWindowPosition;
    }

    public enum Type {
        NONE(-1),
        MOUSE_DOWN(501),
        MOUSE_UP(502),
        MOUSE_DRAG(506);

        public int getId(){
            return id;
        }

        private final int id;

        Type(int id){
            this.id = id;
        }

        public static Type getTypeById(int id){
            return Arrays.stream(Type.values()).filter(t -> t.getId() == id).findFirst().orElse(NONE);
        }
    }

}
