package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.model.game.Map;
import org.junit.jupiter.api.BeforeEach;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.GhostEnemy;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GhostEnemyControllerTest {
    private GhostEnemyController controller;
    private Map map;
    private Main main;

    @BeforeEach
    void setUp() {
        map = mock(Map.class);
        controller = new GhostEnemyController(map);
        main = mock(Main.class);
    }

    @Test
    void testStepMove() throws IOException {
        GhostEnemy ghostEnemy = new GhostEnemy(new Position(5, 5));
        when(map.getGhostEnemies()).thenReturn(Arrays.asList(ghostEnemy));

        controller.step(main, GUI.ACTION.NONE, 400);

        assertEquals(new Position(5, 5), ghostEnemy.getPosition());
    }

    @Test
    void testStepMoveToEmptyPosition() throws IOException {
        GhostEnemy ghostEnemy = new GhostEnemy(new Position(5, 5));
        when(map.getGhostEnemies()).thenReturn(Arrays.asList(ghostEnemy));
        when(map.isEmpty(any(Position.class))).thenReturn(true);

        controller.step(main, GUI.ACTION.NONE, 400);

        assertNotEquals(new Position(5, 5), ghostEnemy.getPosition());
        verify(map, times(1)).isEmpty(any(Position.class));
    }

    @Test
    void testStepMoveToNonEmptyPosition() throws IOException {
        GhostEnemy ghostEnemy = new GhostEnemy(new Position(5, 5));
        when(map.getGhostEnemies()).thenReturn(Arrays.asList(ghostEnemy));
        when(map.isEmpty(any(Position.class))).thenReturn(false);

        controller.step(main, GUI.ACTION.NONE, 400);

        assertEquals(new Position(5, 5), ghostEnemy.getPosition());
        verify(map, times(1)).isEmpty(any(Position.class));
    }
}

