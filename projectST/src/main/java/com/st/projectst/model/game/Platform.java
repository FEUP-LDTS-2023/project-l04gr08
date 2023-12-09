package com.st.projectst.model.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.gui2.Direction;
import com.st.projectst.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Platform extends Wall {
    private List<Wall> connectedPlatforms;
    private int directionX;
    private int directionY;
    public Platform(Position position) {

        super(position);
        this.connectedPlatforms = new ArrayList<>();
        this.directionX = 0;
        this.directionY = 1;
    }
    public boolean isOnSameLevel(Wall otherPlatform) {
        return getPosition().getY() == otherPlatform.getPosition().getY();
    }
    public void addConnectedPlatform(Wall wall) {
        connectedPlatforms.add(wall);
    }
    public void moveAllPlatforms(int directionX, int directionY) {
        for (Wall connectedPlatform : connectedPlatforms) {
            Position currentPosition = connectedPlatform.getPosition();
            Position newPosition = new Position(
                    currentPosition.getX() + directionX,
                    currentPosition.getY() + directionY
            );
            connectedPlatform.setPosition(newPosition);
        }
    }

    public void setDirection(int x, int y) {
        this.directionX = x;
        this.directionY = y;
    }
    public List<Wall> getConnectedPlatforms() {
        return connectedPlatforms;
    }

    public void setConnectedPlatforms(List<Wall> connectedPlatforms) {
        this.connectedPlatforms = connectedPlatforms;
    }
}
