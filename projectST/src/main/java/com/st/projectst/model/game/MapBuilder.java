package com.st.projectst.model.game;

import com.st.projectst.model.Position;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MapBuilder {
    private int level;

    private List<String> linesMap;
    private int width;
    private int height;
    //private boolean[][] potionLocations;

    public MapBuilder(int level) throws IOException {
        this.level = level;

        URL resource = MapBuilder.class.getResource("/levels/map" + level + ".txt");
        BufferedReader buff = new BufferedReader(new FileReader(resource.getFile()));
        this.linesMap = loadFromFile(buff);
    }

    public Map buildMap() throws IOException {
        width = linesMap.get(0).length();
        height = linesMap.size();

        Map map = new Map(width, height, level);
        map.setMari(createMari());
        map.setGhostEnemies(createGhostEnemies());
        map.setBatEnemies(createBatEnemies());
        map.setWalls(createWalls());
        map.setKey(createKey());
        map.setTraps(createTraps(map));
        map.setDoor(createDoor());
        //map.setPotionLocations(potionLocations);
        return map;
    }

    public List<String> loadFromFile(BufferedReader buff) throws IOException {
        List<String> lines = new ArrayList<>();

        for (String line; (line = buff.readLine()) != null; )
            lines.add(line);

        return lines;
    }


    private Mari createMari() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (linesMap.get(y).charAt(x) == 'M')
                    return new Mari(new Position(x, y));
            }
        }
        return null;
    }

    private List<Character> getBackgroundSymbols() {
        List<Character> backgroundSymbols = new ArrayList<>();
        for (String line : linesMap) {
            for (char symbol : line.toCharArray()) {
                if (!isPlayerCharacter(symbol) && !isEnemyCharacter(symbol)) {
                    backgroundSymbols.add(symbol);
                }
            }
        }
        return backgroundSymbols;
    }

    private boolean isPlayerCharacter(char symbol) {
        return symbol == 'M';
    }

    private boolean isEnemyCharacter(char symbol) {
        return symbol == 'G' || symbol == 'B' ;
    }
    private List<GhostEnemy> createGhostEnemies() {
        List<GhostEnemy> gEnemies = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (linesMap.get(y).charAt(x) == 'G')
                    gEnemies.add(new GhostEnemy(new Position(x, y)));
            }
        }
        return gEnemies;
    }

    private List<BatEnemy> createBatEnemies() {
        List<BatEnemy> bEnemies = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (linesMap.get(y).charAt(x) == 'B')
                    bEnemies.add(new BatEnemy(new Position(x, y)));
            }
        }
        return bEnemies;
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (linesMap.get(y).charAt(x) == 'W')
                    walls.add(new Wall(new Position(x,y)));
            }
        }
        return walls;
    }
    private Key createKey() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (linesMap.get(y).charAt(x) == 'K')
                    return new Key(new Position(x, y));
            }
        }
        return null;
    }
    private List<Trap> createTraps(Map map) {
        List<Trap> traps = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (linesMap.get(y).charAt(x) == 'X') {
                    Trap newTrap = new Trap(new Position(x,y));
                    newTrap.addObserver(map.getBatEnemies().get(traps.size()));
                    traps.add(newTrap);
                }
            }
        }
        return traps;
    }

    private Door createDoor() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (linesMap.get(y).charAt(x) == 'D')
                    return new Door(new Position(x, y));
            }
        }
        return null;
    }


    private void initializePotionLocations() {
        //potionLocations = new boolean[width][height];
    }

    private void identifyPotions() {
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (linesMap.get(y).charAt(x) == 'P') {
                    //potionLocations[x][y] = true;
                }
            }
        }
    }
}
