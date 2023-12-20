package com.st.projectst.model.game;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MapBuilderTest {

    private MapBuilder mapBuilder;
    private List<String> linesMap;

    @Test
    public void testMapBuilder() {
        int level = 1;
        try {
            MapBuilder mapBuilder = new MapBuilder(level);
            Map map = mapBuilder.buildMap();

            assertNotNull(map);
            assertEquals(map.getWidth(), 320);
            assertEquals(map.getHeight(),61);

        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testLoadFromFile() {
        String filePath = "src/main/resources/levels/map1.txt";

        try (BufferedReader buff = new BufferedReader(new FileReader(filePath))) {
            MapBuilder mapBuilder = new MapBuilder(1);
            List<String> lines = mapBuilder.loadFromFile(buff);

            assertNotNull(lines);
            assertFalse(lines.isEmpty());

        } catch (IOException e) {
            fail("IOException occurred: " + e.getMessage());
        }
    }

    @Test
    public void testCreateMari() throws IOException {
        mapBuilder = new MapBuilder(1);
        Mari mari = mapBuilder.createMari();
        assertNull(mari);
        Map map = mapBuilder.buildMap();
        mari = mapBuilder.createMari();
        assertNotNull(mari);
    }

    @Test
    public void testCreateGhostEnemies() throws IOException {
        mapBuilder = new MapBuilder(1);
        Map map = mapBuilder.buildMap();
        List<GhostEnemy> ghostEnemies = mapBuilder.createGhostEnemies();
        assertNotNull(ghostEnemies);
    }

    @Test
    public void testCreateBatEnemies() throws IOException {
        mapBuilder = new MapBuilder(1);
        Map map = mapBuilder.buildMap();
        List<BatEnemy> batEnemies = mapBuilder.createBatEnemies();
        assertNotNull(batEnemies);
    }

    @Test
    public void testCreatePotions() throws IOException {
        mapBuilder = new MapBuilder(1);
        Map map = mapBuilder.buildMap();
        List<Potion> potions = mapBuilder.createPotions();
        assertNotNull(potions);
    }

    @Test
    public void testCreateWalls() throws IOException {
        mapBuilder = new MapBuilder(1);
        Map map = mapBuilder.buildMap();
        List<Wall> walls = mapBuilder.createWalls();
        assertNotNull(walls);
    }

    @Test
    public void testCreatePlatforms() throws IOException {
        mapBuilder = new MapBuilder(1);
        Map map = mapBuilder.buildMap();
        List<Platform> platforms = mapBuilder.createPlatforms();
        assertNotNull(platforms);
    }

    @Test
    public void testCreateDoor() throws IOException {
        mapBuilder = new MapBuilder(1);
        Door door = mapBuilder.createDoor();
        assertNull(door);
        Map map = mapBuilder.buildMap();
        door = mapBuilder.createDoor();
        assertNotNull(door);
    }

    @Test
    public void testCreateKey() throws IOException {
        mapBuilder = new MapBuilder(1);
        Key key = mapBuilder.createKey();
        assertNull(key);
        Map map = mapBuilder.buildMap();
        key = mapBuilder.createKey();
        assertNotNull(key);
    }
}
