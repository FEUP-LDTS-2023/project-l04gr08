package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MariControllerTest {
    private Map map;
    private MariController mariController;
    private Mari mari;
    private Main main;

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
        map.setPlatforms(Arrays.asList());
        map.setPotions(Arrays.asList());
        mariController = new MariController(map);
        main = mock(Main.class);
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
    void testUpdateMari_WithPotion() {
        Position newPosition = new Position(10, 10);
        Potion potion = new Potion(newPosition);
        mariController.getModel().setPotions(List.of(potion));

        Position initialPosition = new Position(10, 10);
        mariController.getModel().getMari().setPosition(initialPosition);
        mariController.updateMari(100);

        assertTrue(mariController.getModel().getMari().getIsWithPotion());
    }

    @Test
    void testMoveMariWithKey() {
        Position newPosition = new Position(10, 18);
        Key key = new Key(newPosition);
        mariController.getModel().setKey(key);

        Position initialPosition = new Position(10, 10);
        mariController.getModel().getMari().setPosition(initialPosition);
        mariController.updateMari(100);

        assertTrue(mariController.getModel().getMari().getWithKey());
    }
    /*
     @Test
     public void testPlatformPosition() {
         Position newPosition = new Position(43, 18);
         Wall wall = new Wall(newPosition);
         Platform platform = new Platform(newPosition);
         platform.addConnectedPlatform(wall);
         mariController.getModel().setPlatforms(List.of(platform));

         Position initialPosition = new Position(40, 5);
         mariController.getModel().getMari().setPosition(initialPosition);

         mariController.updateMari(100);
         assertTrue(mariController.getModel().Grounded());
     }

     */

    @Test
    void testStepMoveToEnemyPosition() throws IOException {
        MariController mariController = new MariController(map);

        GhostEnemy ghostEnemy = Mockito.mock(GhostEnemy.class);
        BatEnemy batEnemy = Mockito.mock(BatEnemy.class);

        Position position = new Position(10, 10);
        Position position2 = new Position(10, 10);

        Mockito.when(ghostEnemy.getPosition()).thenReturn(position);
        Mockito.when(batEnemy.getPosition()).thenReturn(position2);

        mariController.getModel().setGhostEnemies(List.of(ghostEnemy));
        mariController.getModel().setBatEnemies(List.of(batEnemy));

        mariController.step(main, GUI.ACTION.NONE, 100);

        assertTrue(mariController.getModel().isEnemy(new Position(14, 10)));
        assertTrue(mariController.getModel().isEnemy(new Position(17, 10)));
        assertFalse(mariController.getModel().isEnemy(new Position(15, 10)));
    }

}
