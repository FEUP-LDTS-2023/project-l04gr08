package com.st.projectst.model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapBuilder {
    private List<String> linesMap;
    private int width;
    private int height;

    public Map buildMap(String filePath, int level) throws IOException {
        linesMap = loadFromFile(filePath);
        width = linesMap.get(0).length();
        height = linesMap.size();

        Map map = new Map(width, height, level);
        map.setMari(createMari());
        map.setEnemies(createEnemies());
        map.setWalls(createWalls());
        map.setKey(createKey());
        return map;
    }

    public List<String> loadFromFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));

        for (String line; (line = reader.readLine()) != null; )
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
        return symbol == 'G' || symbol == 'W' || symbol == 'K';
    }
    private List<Enemy> createEnemies() {
        List<Enemy> enemies = new ArrayList<>();
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (linesMap.get(y).charAt(x) == 'G')
                    enemies.add(new GhostEnemy(new Position(x, y)));
            }
        }
        return enemies;
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

}
