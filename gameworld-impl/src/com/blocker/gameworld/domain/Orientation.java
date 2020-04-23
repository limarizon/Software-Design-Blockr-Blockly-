package com.blocker.gameworld.domain;

/**
 * The enum Orientation.
 */
public enum Orientation {

    /**
     * The North.
     */
    NORTH(new Offset(0, -1)),
    /**
     * The East.
     */
    EAST(new Offset(1, 0)),
    /**
     * The South.
     */
    SOUTH(new Offset(0, 1)),
    /**
     * The West.
     */
    WEST(new Offset(-1, 0));

    /**
     * Get offset offset.
     *
     * @return the offset
     */
    public Offset getOffset(){
        return offset;
    }

    private final Offset offset;

    Orientation(Offset offset){

        this.offset = offset;
    }

    /**
     * Make robot look left depending on current orientation
     *
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
     *
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

    /**
     * The class Offset used for providing offsets to an Orientation value
     */
    public static class Offset {

        /**
         * Get x.
         *
         * @return the x coordinate offset
         */
        public int getX(){
            return x;
        }

        private final int x;

        /**
         * Get y.
         *
         * @return the y coordinate offset
         */
        public int getY(){
            return y;
        }

        private final int y;

        /**
         * Instantiates a new Offset.
         *
         * @param x the x coordinate offset
         * @param y the y coordinate offset
         */
        public Offset(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
}
