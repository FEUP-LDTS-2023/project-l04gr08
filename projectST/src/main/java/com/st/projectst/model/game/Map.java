package com.st.projectst.model.game;

import com.st.projectst.model.Position;

import java.util.List;

public class Map {
    private int width;
    private int height;
    private int currentLevel;

    private Mari mari;
    private List<GhostEnemy> gEnemies;
    private List<BatEnemy> bEnemies;
    private List<Wall> walls;
    private Key key;
    private List<String> mapData;

    public Map (int width, int height, int currentLevel) {
        this.width = width;
        this.height = height;
        this.currentLevel = currentLevel;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }

    public Mari getMari() {
        return mari;
    }

    public void setMari(Mari mari) {
        this.mari = mari;
    }

    public List<GhostEnemy> getGhostEnemies() {
        return gEnemies;
    }
    public List<BatEnemy> getBatEnemies() {
        return bEnemies;
    }

    public void setGhostEnemies(List<GhostEnemy> gEnemies) {
        this.gEnemies = gEnemies;
    }
    public void setBatEnemies(List<BatEnemy> bEnemies) {
        this.bEnemies = bEnemies;
    }

    public List<Wall> getWalls() {
        return walls;
    }

    public void setWalls(List<Wall> walls) {
        this.walls = walls;
    }

    public Key getKey() {
        return key;
    }

    public void setKey(Key key) {
        this.key = key;
    }
    public void setMapData(List<String> mapData) {
        this.mapData = mapData;
    }
    public List<String> getMapData() {
        return mapData;
    }
    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }

    public boolean isEnemy(Position position) {
        for (GhostEnemy enemy : getGhostEnemies())
            if (enemy.getPosition().equals(position))
                return true;
        for (BatEnemy enemy : getBatEnemies())
            if (enemy.getPosition().equals(position))
                return true;
        return false;
    }
}
