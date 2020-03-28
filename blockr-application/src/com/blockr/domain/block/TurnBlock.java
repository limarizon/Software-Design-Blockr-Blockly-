package com.blockr.domain.block;

import com.blocker.gameworld.api.GameWorldApi;
import com.blockr.domain.block.interfaces.markers.ReadOnlyTurnBlock;

public class TurnBlock extends StatementBlock implements ReadOnlyTurnBlock {

    public Direction getDirection(){
        return direction;
    }

    public void setDirection(Direction direction){

        if(direction == null){
            throw new IllegalArgumentException("direction must be effective");
        }

        this.direction = direction;
    }

    private Direction direction = Direction.LEFT;

    public StatementBlock execute(GameWorldApi gameWorld) {
        if(getDirection() == Direction.LEFT)
            gameWorld.turnLeft();
        else
            gameWorld.turnRight();

        return getNext();
    }

    public enum Direction {
        LEFT,
        RIGHT
    }
}
