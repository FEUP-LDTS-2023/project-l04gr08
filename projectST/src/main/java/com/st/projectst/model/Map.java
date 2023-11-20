package com.st.projectst.model;

import java.util.List;

public class Map {
    private int width;
    private int height;
    private int currentLevel;

    private Hero mari;
    private List<Enemy> enemies;
    private List<Wall> walls;
    private Key key;

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

    public Hero getMari() {
        return mari;
    }

    public void setMari(Hero mari) {
        this.mari = mari;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = enemies;
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
}
