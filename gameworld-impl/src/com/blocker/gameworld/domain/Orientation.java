package com.blocker.gameworld.domain;

public enum Orientation {

    NORTH(new Offset(0, -1)),
    EAST(new Offset(1, 0)),
    SOUTH(new Offset(0, 1)),
    WEST(new Offset(-1, 0));

    public Offset getOffset(){
        return offset;
    }

    private final Offset offset;

    Orientation(Offset offset){

        this.offset = offset;
    }

    /**
     * Make robot look left depending on current orientation
     * @return orientation value after having turned left
     */
    public Orientation turnLeft(){
        int index = indexOf(this);
        if(index == 0){
            index = 4;
        }
        return Orientation.values()[(index - 1) % Orientation.values().length];
    }

    /**
     * Make robot look right depending on current orientation
     * @return orientation value after having turned right
     */
    public Orientation turnRight(){
        return Orientation.values()[(indexOf(this) + 1) % Orientation.values().length];
    }

    private static int indexOf(Orientation orientation){
        for(var i = 0; i < Orientation.values().length; i++){
            if(Orientation.values()[i] == orientation)
                return i;
        }

        return -1;
    }

    public static class Offset {

        public int getX(){
            return x;
        }

        private final int x;

        public int getY(){
            return y;
        }

        private final int y;

        public Offset(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
