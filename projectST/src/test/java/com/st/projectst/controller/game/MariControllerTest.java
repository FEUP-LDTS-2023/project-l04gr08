package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MariControllerTest {
    private Map map;
    private MariController mariController;
    private Mari mari;

    @BeforeEach
    public void setup() {
        map = new Map(320, 60, 1);
        Position position = new Position(10, 10);
        mari = new Mari(position);
        map.setMari(mari);
        map.setKey(new Key(new Position(1,1)));
        map.setTraps(Arrays.asList());
        map.setBatEnemies(Arrays.asList());
        map.setGhostEnemies(Arrays.asList());
        map.setWalls(Arrays.asList());
        mariController = new MariController(map);
    }

    @Test
    public void testMoveMariRight() {
        Position initialPosition = mari.getPosition();
        mariController.moveMariRight();
        Position newPosition = mari.getPosition();
        assertEquals(initialPosition.getX() + 1, 11);
        assertEquals(initialPosition.getY(), 10);
    }

    @Test
    public void testMoveMariLeft() {
        Position initialPosition = mari.getPosition();
        mariController.moveMariLeft();
        Position newPosition = mari.getPosition();
        assertEquals(initialPosition.getX() - 1, 9);
        assertEquals(initialPosition.getY(), 10);
    }

    @Test
    public void testMoveMariUp() {
        Position initialPosition = mari.getPosition();
        mariController.moveMariUp();
        Position newPosition = mari.getPosition();
        assertEquals(initialPosition.getX() , 10);
        assertEquals(initialPosition.getY() - 1, 9);
    }

    @Test
    public void testMoveMariWhenEmpty() {
        Position newPosition = new Position(15, 15);
        mariController.moveMari(newPosition);
        assertEquals(newPosition, mariController.getModel().getMari().getPosition());
    }

    @Test
    public void testMoveMariWhenNotEmpty() {
        Position newPosition = new Position(20, 20);
        Wall wall = new Wall(newPosition);
        map.setWalls(List.of(wall));
        mariController.moveMari(newPosition);
        assertNotEquals(newPosition, mariController.getModel().getMari().getPosition());
    }

    @Test
    public void testUpdateMariGrounded() {
        Position newPosition = new Position(43, 18);
        Wall wall = new Wall(newPosition);
        mariController.getModel().setWalls(List.of(wall));

        Position initialPosition = new Position(40, 4);
        mariController.getModel().getMari().setPosition(initialPosition);
        mariController.updateMari(100);

        assertTrue(mariController.getModel().Grounded());

        newPosition = new Position(49, 18);
        wall = new Wall(newPosition);
        mariController.getModel().setWalls(List.of(wall));

        assertFalse(mariController.getModel().Grounded());

    }

    @Test
    public void testStepActionUp() {
        Wall groundWall = new Wall(new Position(43, 18));
        map.setWalls(List.of(groundWall));

        Position initialPosition = new Position(40, 18 - 14);
        mari.setPosition(initialPosition);

        Main main = mock(Main.class);
        mariController.updateMari(100);
        mari.jump();
        assertTrue(mari.getIsJumping());
        for (int i = 0; i < 6; i++) {
            mari.update();
        }

        mariController.step(main, GUI.ACTION.UP, 100);

        mari.setGrounded(true);
        for (int i = 0; i < 5; i++) {
            mari.update();
        }

        mariController.step(main, GUI.ACTION.UP, 100);
        assertFalse(mari.getIsJumping());
    }


    @Test
    public void testStepActionRight() {
        Position initialPosition = new Position(40, 4);
        mari.setPosition(initialPosition);

        Main main = mock(Main.class);

        mariController.step(main, GUI.ACTION.RIGHT, 100);
        assertEquals(41, mari.getPosition().getX());
    }

    @Test
    public void testStepActionLeft() {
        Position initialPosition = new Position(40, 4);
        mari.setPosition(initialPosition);

        Main main = mock(Main.class);

        mariController.step(main, GUI.ACTION.LEFT, 100);
        assertEquals(39, mari.getPosition().getX());
    }
     @Test
     public void testCollectKey() {
         Position keyPosition = new Position(5+6, 5+8);
         Key key = new Key(keyPosition);

         Map model = mock(Map.class);
         MariController mariController = new MariController(model);

         Position initialPosition = new Position(5, 5);
         Mari mari = new Mari(initialPosition);
         model.setMari(mari);
         assertFalse(mari.getWithKey());
         model.setKey(key);

         when(model.isKey(eq(keyPosition))).thenReturn(true);
         when(model.getMari()).thenReturn(mari);
         mariController.moveMari(keyPosition);

         assertTrue(mari.getWithKey());
         verify(model, times(1)).removeKey();
     }

}

