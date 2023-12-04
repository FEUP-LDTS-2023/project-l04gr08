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
    private boolean[][] potionLocations;

    public Map (int currentLevel) {
        this.currentLevel = currentLevel;
    }
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

    public boolean isKey(Position position) {
        return key.getPosition().equals(position);
    }

    public boolean Grounded() {
        Position floorPosition = new Position(mari.getPosition());
        floorPosition.setY(floorPosition.getY()+1);

        for (Wall wall : walls)
            if (wall.getPosition().equals(floorPosition))
                return true;

        return false;
    }

    /*
    public void setPotionLocations(boolean[][] potionLocations) {
        this.potionLocations = potionLocations;
    }

    public boolean isPotion(Position position) {
        double x = position.getX();
        double y = position.getY();
        if (x >= 0 && x < potionLocations.length && y >= 0 && y < potionLocations[0].length) {
            return potionLocations[(int) x][(int) y];
        }
        return false;
    }

     */
}
