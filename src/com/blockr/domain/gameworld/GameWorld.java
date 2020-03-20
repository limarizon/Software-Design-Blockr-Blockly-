package com.blockr.domain.gameworld;

public class GameWorld implements ReadOnlyGameWorld {

    public int getWidth(){
        return grid[0].length;
    }

    public int getHeight(){
        return grid.length;
    }

    public TileType getTileType(int x, int y){

        if(x < 0 || x >= getWidth()){
            throw new IllegalArgumentException(String.format("x must lie between 0 and %s", getWidth()));
        }

        if(y < 0 || y >= getHeight()){
            throw new IllegalArgumentException(String.format("y must lie between 0 and %s", getHeight()));
        }
        //y is the row
        return grid[y][x];
    }

    public TileType getTileType(Position position){
        return getTileType(position.getX(), position.getY());
    }

    /**
     * Tiletype: ENUM.Free or ENUM.Blocked
     */
    private final TileType[][] grid;

    public Position getStartPosition(){
        return startPosition;
    }

    private final Position startPosition;

    /**
     * returns the orientation of the robot at the start
     * @return startOrientation
     */
    public Orientation getStartOrientation(){
        return startOrientation;
    }

    private final Orientation startOrientation;

    public Position getGoalPosition(){
        return goalPosition;
    }

    private final Position goalPosition;

    public Position getRobotPosition(){
        return robotPosition;
    }

    private Position robotPosition;

    /**
     * Returns the orientation of the robot at runtime
     * @return robotOrientation
     */
    public Orientation getRobotOrientation(){
        return robotOrientation;
    }

    private Orientation robotOrientation;

    /**
     * Contructor for gameworld.
     * @param grid: provide a matrix representing the game world
     * @param startPosition: robot init position is an object of Position
     * @param startOrientation: Initial orientation of the robot
     * @param goalPosition: The endgoal position in the grid, also a Position object
     */
    public GameWorld(TileType[][] grid, Position startPosition, Orientation startOrientation, Position goalPosition){

        throwIfNull(grid, "grid");
        throwIfNull(startPosition, "startPosition");
        throwIfNull(startOrientation, "startDirection");
        throwIfNull(goalPosition, "goalPosition");

        this.grid = grid;
        this.startPosition = startPosition;
        this.startOrientation = startOrientation;
        this.goalPosition = goalPosition;
        this.robotPosition = startPosition;
        this.robotOrientation = startOrientation;
    }

    /**
     * Checker for not having null objects in the world
     * @param object: provide an object of any class
     * @param name: the objects name to identify the same object
     */
    private void throwIfNull(Object object, String name){
        if(object != null)
            return;

        throw new IllegalArgumentException(String.format("%s must be effective", name));
    }

    /**
     * Reset the robot at its starting position.
     */
    public void reset(){
        this.robotPosition = getStartPosition();
        this.robotOrientation = getStartOrientation();
    }

    /**
     * Make robot look left
     */
    public void turnLeft(){
        robotOrientation = robotOrientation.turnLeft();
    }

    /**
     * Make robot look right
     */
    public void turnRight(){
        robotOrientation = robotOrientation.turnRight();
    }

    /**
     * Move the character in the direction of the orientation by using its offsets.
     * Happens by using Position.translate(Orientation)
     */
    public void moveForward(){

        var newPosition = robotPosition.translate(getRobotOrientation().getOffset());

        if(getTileType(newPosition.getX(), newPosition.getY()) == TileType.Blocked)
            return;

        robotPosition = newPosition;
    }
}
