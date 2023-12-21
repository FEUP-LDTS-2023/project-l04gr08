package com.st.projectst.model.game;

import com.st.projectst.Main;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

import com.st.projectst.model.Position;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;


public class MapTest {
    private Map map;
    private Mari mari;

    @BeforeEach
    public void setup() {
        map = new Map(320, 60, 1);
        Position position = new Position(10, 10);
        mari = new Mari(position);
        map.setMari(mari);
        map.setKey(new Key(new Position(1,1)));
        map.setTraps(List.of());
        map.setBatEnemies(List.of());
        map.setGhostEnemies(List.of());
        map.setWalls(List.of());
        map.setPlatforms(List.of());
        map.setPotions(List.of());
    }

    @Test
    public void testIsTrap() {
        Position trapPosition = new Position(13, 33);
        Trap trap = new Trap(trapPosition);
        List<Trap> traps = new ArrayList<>();
        traps.add(trap);
        map.setTraps(traps);
        Position expectedTrapPosition = new Position(10, 20);
        map.getMari().setPosition(expectedTrapPosition);
        assertTrue(map.isTrap());

        map.getMari().setPosition(new Position(10, 25));
        assertFalse(map.isTrap());
    }

    @Test
    public void testIsTrapMock() {
        Mari mari = new Mari(new Position(10, 20));
        map.setMari(mari);
        Trap mockTrap = Mockito.mock(Trap.class);
        Mockito.when(mockTrap.getPosition()).thenReturn(new Position(13, 33));
        List<Trap> traps = new ArrayList<>();
        traps.add(mockTrap);
        map.setTraps(traps);
        verify(mockTrap, times(0)).notifyObservers();
        assertTrue(map.isTrap());
    }

    @Test
    public void testIsKey() {
        Key key = new Key(new Position(1,7));
        map.setKey(key);
        assertTrue(map.isKey(new Position(1, 0)));
        assertFalse(map.isKey(new Position(2, 0)));
        map.removeKey();
        assertFalse(map.isKey(new Position(1, 0)));
    }

    @Test
    public void testIsAtPlatform() {
        Platform platform = new Platform(new Position(14,34));
        List<Platform> platforms = new ArrayList<>();
        platform.setConnectedPlatforms(Arrays.asList(new Wall(new Position(14, 34))));
        platforms.add(platform);
        map.setPlatforms(platforms);
        Position expectedMariPosition = new Position(11, 21);
        map.getMari().setPosition(expectedMariPosition);
        assertTrue(map.isAtPlatform(new Position(11, 21)));

        expectedMariPosition = new Position(17, 25);
        map.getMari().setPosition(expectedMariPosition);
        assertFalse(map.isAtPlatform(new Position(17, 25)));
    }

    @Test
    public void testTouchPotion() {
        Potion potion = new Potion(new Position(7,7));
        List<Potion> potions = new ArrayList<>();
        potions.add(potion);
        map.setPotions(potions);
        assertTrue(map.touchPotion(new Position(7,7)));
        assertFalse(map.touchPotion(new Position(6,7)));
    }

    @Test
    public void testIsAtDoor() {
        Position doorPosition = new Position(12, 10);
        Door door = new Door(doorPosition);
        map.setDoor(door);
        Position expectedPosition = new Position(1, 10);
        assertTrue(map.isAtDoor(expectedPosition));
        assertFalse(map.isAtDoor(doorPosition));
    }

    @Test
    public void testGroundedWalls() {
        Position mariPosition = new Position(5, 10);
        map.setMari(new Mari(mariPosition));
        List<Wall> walls = new ArrayList<>();
        for (int i = 3; i < 9; i++) {
            walls.add(new Wall(new Position(5+i, 24)));
        }
        map.setWalls(walls);
        assertTrue(map.Grounded());

        mariPosition = new Position(20, 12);
        map.setMari(new Mari(mariPosition));
        assertFalse(map.Grounded());
    }

    @Test
    public void testGroundedPlatforms(){
        Position mariPosition = new Position(5, 12);
        map.setMari(new Mari(mariPosition));
        Platform platform = new Platform(new Position(8, 26));
        for (int i = 3; i < 9; i++) {
            platform.addConnectedPlatform(new Wall(new Position(i+5, 26)));
        }

        List<Platform> platforms = new ArrayList<>();
        platforms.add(platform);
        map.setPlatforms(platforms);
        assertTrue(map.Grounded());

        mariPosition = new Position(20, 12);
        map.setMari(new Mari(mariPosition));
        assertFalse(map.Grounded());
    }

    @Test
    public void testIsEmpty() {
        Position emptyPosition = new Position(10, 10);
        assertTrue(map.isEmpty(emptyPosition));
        Position wallPosition = new Position(5, 5);
        Wall wall = new Wall(wallPosition);
        List<Wall> walls = new ArrayList<>();
        walls.add(wall);
        map.setWalls(walls);
        assertFalse(map.isEmpty(wallPosition));
        Position connectedPlatformPosition = new Position(8, 8);
        Platform platform = new Platform(connectedPlatformPosition);
        Wall connectedWall = new Wall(new Position(9, 8));
        platform.addConnectedPlatform(connectedWall);
        List<Platform> platforms = new ArrayList<>();
        platforms.add(platform);
        map.setPlatforms(platforms);
        assertTrue(map.isEmpty(connectedPlatformPosition));
        assertFalse(map.isEmpty(new Position(9, 8)));
    }

    @Test
    public void testGetCurrentLevel() {
        int initialLevel = 5;
        Map map = new Map(10, 10, initialLevel);
        Assertions.assertEquals(initialLevel, map.getCurrentLevel());
        int updatedLevel = 8;
        map.setCurrentLevel(updatedLevel);
        Assertions.assertEquals(updatedLevel, map.getCurrentLevel());
    }

    @Test
    public void testGetPlatformsNotNull() {
        List<Platform> platforms = Arrays.asList(new Platform(new Position(10, 10)));
        map.setPlatforms(platforms);
        assertNotEquals(platforms, Collections.emptyList());
        assertNotNull(platforms);
    }

    @Test
    public void testGetPotionsNotNull() {
        List<Potion> potions = Arrays.asList(new Potion(new Position(15, 15)));
        map.setPotions(potions);
        assertNotEquals(potions, Collections.emptyList());
        assertNotNull(potions);
    }

    @Test
    void testIsEnemy() {
        GhostEnemy ghostEnemy = new GhostEnemy(new Position(5, 5));
        BatEnemy batEnemy = new BatEnemy(new Position(10, 10));
        List<GhostEnemy> ghostEnemies = new ArrayList<>();
        ghostEnemies.add(ghostEnemy);
        map.setGhostEnemies(ghostEnemies);

        List<BatEnemy> batEnemies = new ArrayList<>();
        batEnemies.add(batEnemy);
        map.setBatEnemies(batEnemies);
        assertTrue(map.isEnemy(new Position(9, 5)));
        assertTrue(map.isEnemy(new Position(17, 10)));
        assertFalse(map.isEnemy(new Position(15, 15)));
        assertTrue(map.isEnemy(new Position(-4, 5)));
    }
}
