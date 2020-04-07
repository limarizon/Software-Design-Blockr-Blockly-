package com.blocker.gameworld.ui;

import com.blocker.gameworld.domain.GridPosition;
import com.blocker.gameworld.domain.Orientation;
import com.blocker.gameworld.domain.RobotGameWorld;
import com.blocker.gameworld.domain.grid.TileType;

import java.awt.*;

public class GameWorldComponent {

    public void draw(Graphics graphics, RobotGameWorld gameWorld) {

        var windowRegion = WindowRegion.fromGraphics(graphics);

        var gridWidth = gameWorld.getGrid().getWidth();
        var gridHeight = gameWorld.getGrid().getHeight();
        /**
         * hier wordt u tegelgrootte geschaald afh van de aantal tegels in de gameworld
         */
        var tileWidth = windowRegion.getWidth() / gridWidth;
        var tileHeight = windowRegion.getHeight() / gridHeight;

        for(int i = 0; i < gridWidth * gridHeight; i++){

            var tileX = i % gridWidth;
            var tileY = i / gridWidth;

            var offsetX = tileX * tileWidth;
            var offsetY = tileY * tileHeight;

            drawTile(graphics.create(offsetX, offsetY, tileWidth, tileHeight), gameWorld, new GridPosition(tileX, tileY));
        }
    }

    private void drawTile(Graphics graphics, RobotGameWorld gameWorld, GridPosition position){

        var tileType = gameWorld.getGrid().getTileType(position.getX(), position.getY());
        var windowRegion = WindowRegion.fromGraphics(graphics);

        var color = tileTypeToColor(tileType);
        graphics.setColor(color);
        graphics.fillRect(0, 0, windowRegion.getWidth(), windowRegion.getHeight());

        if(gameWorld.getGoalPosition().equals(position)){
            GoalTile.draw(graphics);
        }

        if(gameWorld.getRobot().getPosition().equals(position)){
            RobotTile.draw(graphics, gameWorld.getRobot().getOrientation());
        }
    }

    private static Color tileTypeToColor(TileType type){
        switch(type){
            case FREE:
                return Color.GREEN;
            case BLOCKED:
                return Color.RED;
        }
        return null;
    }
}

class RobotTile {

    public static void draw(Graphics graphics, Orientation robotOrientation) {

        var windowRegion = WindowRegion.fromGraphics(graphics);

        float antennaCenterRadius = 0.2f;
        FloatPosition antennaCenter = new FloatPosition(0.5f - antennaCenterRadius / 2, 1f - 0.1f - antennaCenterRadius / 2);

        FloatPosition antennaPoleStart = new FloatPosition(0.5f, 0.9f - antennaCenterRadius / 2);
        FloatPosition antennaPoleEnd = new FloatPosition(0.5f, 0.6f);

        FloatPosition robotFaceStart = new FloatPosition(0.15f, 0.15f);
        FloatPosition robotFaceEnd = new FloatPosition(0.85f, 0.6f);
        FloatPosition robotFaceSize = new FloatPosition(robotFaceEnd.x - robotFaceStart.x, robotFaceEnd.y - robotFaceStart.y);

        float robotEyeSize = 0.3f;
        FloatPosition robotLeftEye = new FloatPosition(0.35f - robotEyeSize / 2, 0.25f);
        FloatPosition robotRightEye = new FloatPosition(0.65f - robotEyeSize / 2, 0.25f);

        float innerRobotEyeSize = 0.15f;
        FloatPosition innerRobotLeftEye = new FloatPosition(0.35f - innerRobotEyeSize / 2, 0.25f);
        FloatPosition innerRobotRightEye = new FloatPosition(0.65f - innerRobotEyeSize / 2, 0.25f);

        switch (robotOrientation){
            case NORTH:
                break;
            case EAST:
                innerRobotLeftEye = new FloatPosition(0.45f - innerRobotEyeSize / 2, 0.35f);
                innerRobotRightEye = new FloatPosition(0.75f - innerRobotEyeSize / 2, 0.35f);
                break;
            case SOUTH:
                innerRobotLeftEye = new FloatPosition(0.35f - innerRobotEyeSize / 2, 0.40f);
                innerRobotRightEye = new FloatPosition(0.65f - innerRobotEyeSize / 2, 0.40f);
                break;
            case WEST:
                innerRobotLeftEye = new FloatPosition(0.25f - innerRobotEyeSize / 2, 0.35f);
                innerRobotRightEye = new FloatPosition(0.55f - innerRobotEyeSize / 2, 0.35f);
                break;
        }
        graphics.setColor(Color.ORANGE);
        graphics.fillArc((int)(windowRegion.getWidth()*antennaCenter.x),
                (int)(windowRegion.getHeight()*antennaCenter.y),
                (int)(windowRegion.getWidth()*antennaCenterRadius),
                (int)(windowRegion.getHeight()*antennaCenterRadius),
                0,360);
        graphics.setColor(Color.BLACK);
        graphics.drawArc((int)(windowRegion.getWidth()*antennaCenter.x),
                (int)(windowRegion.getHeight()*antennaCenter.y),
                (int)(windowRegion.getWidth()*antennaCenterRadius),
                (int)(windowRegion.getHeight()*antennaCenterRadius),
                0,360);
        graphics.drawLine(antennaPoleStart.getX(windowRegion.getWidth()),antennaPoleStart.getY(windowRegion.getHeight()),
                antennaPoleEnd.getX(windowRegion.getWidth()),antennaPoleEnd.getY(windowRegion.getHeight()));
        graphics.setColor(Color.CYAN);
        graphics.fillRect(robotFaceStart.getX(windowRegion.getWidth()),robotFaceStart.getY(windowRegion.getHeight()),
                robotFaceSize.getX(windowRegion.getWidth()),robotFaceSize.getY(windowRegion.getHeight()));
        graphics.setColor(Color.BLACK);
        graphics.drawRect(robotFaceStart.getX(windowRegion.getWidth()),robotFaceStart.getY(windowRegion.getHeight()),
                robotFaceSize.getX(windowRegion.getWidth()),robotFaceSize.getY(windowRegion.getHeight()));
        graphics.setColor(Color.white);
        graphics.fillArc(robotLeftEye.getX(windowRegion.getWidth()),
                robotLeftEye.getY(windowRegion.getHeight()),
                (int)(windowRegion.getWidth()*robotEyeSize),
                (int)(windowRegion.getHeight()*robotEyeSize),
                0,360);
        graphics.fillArc(robotRightEye.getX(windowRegion.getWidth()),
                robotRightEye.getY(windowRegion.getHeight()),
                (int)(windowRegion.getWidth()*robotEyeSize),
                (int)(windowRegion.getHeight()*robotEyeSize),
                0,360);
        graphics.setColor(Color.black);
        graphics.drawArc(robotLeftEye.getX(windowRegion.getWidth()),
                robotLeftEye.getY(windowRegion.getHeight()),
                (int)(windowRegion.getWidth()*robotEyeSize),
                (int)(windowRegion.getHeight()*robotEyeSize),
                0,360);
        graphics.drawArc(robotRightEye.getX(windowRegion.getWidth()),
                robotRightEye.getY(windowRegion.getHeight()),
                (int)(windowRegion.getWidth()*robotEyeSize),
                (int)(windowRegion.getHeight()*robotEyeSize),
                0,360);
        graphics.fillArc(innerRobotLeftEye.getX(windowRegion.getWidth()),
                innerRobotLeftEye.getY(windowRegion.getHeight()),
                (int)(windowRegion.getWidth()*innerRobotEyeSize),
                (int)(windowRegion.getHeight()*innerRobotEyeSize),
                0,360);
        graphics.fillArc(innerRobotRightEye.getX(windowRegion.getWidth()),
                innerRobotRightEye.getY(windowRegion.getHeight()),
                (int)(windowRegion.getWidth()*innerRobotEyeSize),
                (int)(windowRegion.getHeight()*innerRobotEyeSize),
                0,360);
    }

    public static class FloatPosition {

        public int getX(int regionWidth) {
            return ((int)(x * regionWidth));
        }

        private final float x;

        public int getY(int regionHeight) {
            return ((int)(y * regionHeight));
        }

        private final float y;

        FloatPosition(float x, float y){
            this.x = x;
            this.y = y;
        }


    }
}

class GoalTile {

    public static void draw(Graphics graphics) {

        var windowRegion = WindowRegion.fromGraphics(graphics);
        var min = Math.min(windowRegion.getWidth(), windowRegion.getHeight());

        float radius = 0.9f;
        float mod = 0.1f;
        for (int i = 1; i < 10; i++) {
            if(i % 2 == 0){
                graphics.setColor(Color.white);
            }else{
                graphics.setColor(Color.red);
            }
            graphics.fillArc((int)((0.5-radius/2) * min),(int)((0.5-radius/2) * min),(int)(radius * min),(int)(radius * min),0,360);
            radius -= mod;
        }
    }
}