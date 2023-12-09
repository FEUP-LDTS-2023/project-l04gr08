package com.st.projectst.viewer;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.*;
import com.st.projectst.viewer.game.LevelViewer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.util.Arrays;

public class LevelViewerTest {
    private Map map;
    private LevelViewer levelViewer;
    private GUI gui;

    @BeforeEach
    void setUp() {
        map = new Map(320, 60, 1);
        gui = Mockito.mock(GUI.class);

        map.setMari(new Mari(new Position(10,10)));
        map.setKey(new Key(new Position(15,10)));
        map.setDoor(new Door(new Position(40,10)));
        map.setWalls(Arrays.asList(new Wall(new Position(0,0))));
        map.setTraps(Arrays.asList(new Trap(new Position(20,20))));
        map.setBatEnemies(Arrays.asList(new BatEnemy(new Position(20,5))));
        map.setGhostEnemies(Arrays.asList(new GhostEnemy(new Position(25,10))));

        levelViewer = new LevelViewer(map);
    }

    @Test
    void testDrawMari() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, Mockito.times(1)).drawMari(new Position(10,10));
    }

    @Test
    void testDrawKey() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, Mockito.times(1)).drawKey(new Position(15,10));
    }

    @Test
    void testDrawDoor() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, Mockito.times(1)).drawDoor(new Position(40,10));
    }

    @Test
    void testDrawWalls() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, Mockito.times(1)).drawWall(new Position(0,0));
    }

    @Test
    void testDrawTraps() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, Mockito.times(1)).drawTrap(new Position(20,20));
    }

    @Test
    void testDrawBatEnemies() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, Mockito.times(1)).drawBatEnemy(new Position(20,5));
    }

    @Test
    void testDrawGhostEnemies() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, Mockito.times(1)).drawGhostEnemy(new Position(25,10));
    }

    @Test
    void testDrawLives() throws IOException, FontFormatException {
        levelViewer.drawObject(gui);
        Mockito.verify(gui, Mockito.times(1)).drawImage(new Position(1, -2), "gameObjects/life3.png", 1);

        map.getMari().decreaseLives();
        levelViewer.drawObject(gui);
        Mockito.verify(gui, Mockito.times(1)).drawImage(new Position(1, -2), "gameObjects/life2.png", 1);

        map.getMari().decreaseLives();
        levelViewer.drawObject(gui);
        Mockito.verify(gui, Mockito.times(1)).drawImage(new Position(1, -2), "gameObjects/life1.png", 1);
    }

}
