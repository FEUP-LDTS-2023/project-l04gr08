package com.st.projectst.controller.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.*;
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
    public void testUpdateMariAttacked() {
        GhostEnemy enemy = new GhostEnemy(new Position(40, 17));
        map.setGhostEnemies(List.of(enemy));
        Position initialPosition = new Position(40, 18 - 14);
        mari.setPosition(initialPosition);
        int initialRemainingLives = mari.getRemainingLives();
        long attackThreshold = 600;
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;

        while (elapsedTime < attackThreshold) {
            mariController.updateMari(startTime + elapsedTime);
            elapsedTime = System.currentTimeMillis() - startTime;
        }
        assertEquals(initialRemainingLives , mari.getRemainingLives());
    }



    @Test
    public void testUpdateMariAttackedTimeElapsed() {
        // Set up an enemy position near Mari
        GhostEnemy enemy = new GhostEnemy(new Position(40, 17));
        map.setGhostEnemies(List.of(enemy));

        Position initialPosition = new Position(40, 18 - 14);
        mari.setPosition(initialPosition);

        long attackThreshold = 600;
        long startTime = System.currentTimeMillis();
        long elapsedTime = 0;
        while (elapsedTime < attackThreshold) {
            mariController.updateMari(startTime + elapsedTime);
            elapsedTime = System.currentTimeMillis() - startTime;
        }

        mariController.updateMari(700); // Ensure more than 600ms have passed

        assertEquals(2, mari.getRemainingLives()); // An attack should reduce lives by 1
    }

    @Test
    public void testStepActionUp() {
        // Ensure Mari is grounded and make her jump by triggering the step action UP
        Wall groundWall = new Wall(new Position(40, 18));
        map.setWalls(List.of(groundWall));

        Position initialPosition = new Position(40, 18 - 14);
        mari.setPosition(initialPosition);

        //mariController.step(main, GUI.ACTION.UP, 100);
        assertTrue(mari.getIsJumping()); // Mari should start jumping
    }

    @Test
    public void testStepActionRight() {
        Position initialPosition = new Position(40, 18 - 14);
        mari.setPosition(initialPosition);

        //mariController.step(main, GUI.ACTION.RIGHT, 100);
        assertEquals(new Position(41, 4), mari.getPosition());
    }

    @Test
    public void testStepActionLeft() {
        Position initialPosition = new Position(40, 18 - 14);
        mari.setPosition(initialPosition);

        //mariController.step(main, GUI.ACTION.LEFT, 100);
        assertEquals(new Position(39, 4), mari.getPosition());
    }

}

