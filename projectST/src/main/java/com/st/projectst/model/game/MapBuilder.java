package com.st.projectst.model.game;

import com.st.projectst.model.Position;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
        map.setGhostEnemies(createGhostEnemies());
        map.setBatEnemies(createBatEnemies());
        map.setWalls(createWalls());
        map.setKey(createKey());
        return map;
    }

    public List<String> loadFromFile(String filePath) throws IOException {
        return Files.readAllLines(Paths.get(filePath), StandardCharsets.UTF_8);
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

}
