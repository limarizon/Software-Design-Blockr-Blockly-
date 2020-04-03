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
     */
    public Orientation turnLeft(){

        return Orientation.values()[(indexOf(this) - 1) % Orientation.values().length];
    }

    /**
     * Make robot look right depending on current orientation
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
        public Offset reverseOffset(){
            return new Offset(this.getX()*-1,this.getY()*-1);
        }
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
