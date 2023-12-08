package com.st.projectst.model.game;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Position;

import java.util.ArrayList;
import java.util.List;

public class Platform extends Wall {
    private List<Wall> connectedPlatforms;
    public Platform(Position position) {

        super(position);
        this.connectedPlatforms = new ArrayList<>();
    }
    public boolean isOnSameLevel(Wall otherPlatform) {
        return getPosition().getY() == otherPlatform.getPosition().getY();
    }
    public void addConnectedPlatform(Wall wall) {
        connectedPlatforms.add(wall);
    }
    public void moveAllPlatforms() {
        int n = (int) (Math.random() * 2);
        Position newPosition = new Position(0, 0);
        switch (n) {
            case 0:
                newPosition = getPosition().getRandomHorizontal();
            default:
                newPosition = getPosition().getRandomVertical();
        }
        for (Wall connectedPlatform : connectedPlatforms) {
            connectedPlatform.setPosition(newPosition);
        }
    }
    public List<Wall> getConnectedPlatforms() {
        return connectedPlatforms;
    }

    public void setConnectedPlatforms(List<Wall> connectedPlatforms) {
        this.connectedPlatforms = connectedPlatforms;
    }
}
