package com.st.projectst.model.game;

import com.st.projectst.model.Position;

import java.util.ArrayList;
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
    private Door door;
    private List<Trap> traps;

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

    public boolean isAtDoor(Position position) {
        Position mariPosition = new Position(position);
        mariPosition.setX(mariPosition.getX()+11);
        return door.getPosition().equals(mariPosition);
    }

    public boolean isEmpty(Position position) {
        for (Wall wall : walls)
            if (wall.getPosition().equals(position))
                return false;
        return true;
    }

    public boolean isEnemy(Position position) {
        Position position2 = new Position(position); position2.setX(position.getX()+13);
        for (GhostEnemy enemy : getGhostEnemies()) {
            Position enemyPosition = new Position(enemy.getPosition());
            enemyPosition.setX(enemyPosition.getX()+4);
            if (enemyPosition.equals(position) || enemyPosition.equals(position2))
                return true;
        }
        for (BatEnemy enemy : getBatEnemies()) {
            Position enemyPosition = new Position(enemy.getPosition());
            enemyPosition.setX(enemyPosition.getX()+7);
            if (enemyPosition.equals(position) || enemyPosition.equals(position2))
                return true;
        }
        return false;
    }

    public boolean Grounded() {
        Position floorPosition = new Position(mari.getPosition());
        floorPosition.setY(floorPosition.getY()+14);

        List<Position> floorPositions = new ArrayList<>();
        for (int i = 3; i < 9; i++) {
            Position newFloorPosition = new Position(floorPosition);
            newFloorPosition.setX(newFloorPosition.getX()+i);
            floorPositions.add(newFloorPosition);
        }

        for (Position pos: floorPositions) {
            for (Wall wall : walls) {
                if (wall.getPosition().equals(pos))
                    return true;
            }
        }
        return false;
    }

    public boolean isTrap() {
        Position trapPosition = new Position(mari.getPosition());
        trapPosition.setY(trapPosition.getY()+13);

        List<Position> trapPositions = new ArrayList<>();
        for (int i = 3; i < 9; i++) {
            Position newFloorPosition = new Position(trapPosition);
            newFloorPosition.setX(newFloorPosition.getX()+i);
            trapPositions.add(newFloorPosition);
        }


        for (Position pos: trapPositions) {
            for (Trap trap : traps){
                if (trap.getPosition().equals(pos)) {
                    trap.notifyObservers();
                    return true;
                }
            }
        }
        return false;
    }

    public boolean isKey(Position position) {
        if (key != null) {
            Position pos = new Position(position);
            pos.setY(position.getY()+7);
            return key.getPosition().equals(pos);
        }
        return false;
    }

    public void removeKey() {
        this.key = null;
    }

    public boolean isDoor(Position position) {
        return door.equals(position);
    }

    public List<Trap> getTraps() {
        return traps;
    }

    public void setTraps(List<Trap> traps) {
        this.traps = traps;
    }

    public void setDoor(Door door) {
        this.door = door;
    }

    public Door getDoor() {
        return door;
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
