package com.st.projectst.model;

import java.util.List;

public class Map {
    private int width;
    private int height;
    private int currentLevel;

    private Mari mari;
    private List<Enemy> enemies;
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
    public void setMapData(List<String> mapData) {
        this.mapData = mapData;
    }
    public List<String> getMapData() {
        return mapData;
    }
}
