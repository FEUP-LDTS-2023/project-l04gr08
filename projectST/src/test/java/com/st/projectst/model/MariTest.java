package com.st.projectst.model;


import com.st.projectst.model.game.Mari;
import com.st.projectst.model.game.Wall;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class MariTest {
    Mari mari;

    @BeforeEach
    public void setup() {
        Position initialPosition = new Position(10,10);
        mari = new Mari(initialPosition);
    }


    @Test
    public void mariMoveRight() {
        Position position = mari.moveRight();
        Position expected = new Position(11,10);

        assertEquals(expected, position);
    }

    @Test
    public void mariMoveLeft() {
        Position position = mari.moveLeft();
        Position expected = new Position(9,10);

        assertEquals(expected, position);
    }

    @Test
    public void testMoveLeftWithSubtraction() {
        mari.setPosition(new Position(0, 0));

        Position position = mari.moveLeft();
        Position expected = new Position(-1, 0);

        assertEquals(expected, position);
    }

    @Test
    public void mariJumpNotGrounded() {
        mari.jump();
        assertFalse(mari.getIsJumping());

        mari.setGrounded(false);
        mari.jump();
        assertFalse(mari.getIsJumping());
    }

    @Test
    public void mariJumpGrounded() {
        mari.setGrounded(true);
        mari.jump();
        assertTrue(mari.getIsJumping());
    }

    @Test
    public void mariUpdateNoJumpingNoGrounded() {
        mari.update();
        Position expected = new Position(10,11);

        assertEquals(expected, mari.getPosition());
    }

    @Test
    public void mariUpdateNoJumpingGrounded() {
        mari.setGrounded(true);
        mari.update();
        Position expected = new Position(10,10);

        assertEquals(expected, mari.getPosition());
    }

    /*
    @Test
    public void mariUpdateJumping() {
        mari.setGrounded(true);
        mari.jump();
        assertTrue(mari.getIsJumping());
        assertEquals(0, mari.getJumpCounter());

        // Testing the character moving up on the jump
        Position expected = new Position(40,17);
        for (int i = 1; i <= 5; i++) {
            mari.update();

            assertTrue(mari.getIsJumping());
            expected.setY(expected.getY()-1);
            assertEquals(expected, mari.getPosition());
            assertEquals(i, mari.getJumpCounter());
        }

        // Testing the character reaching the highest position on the jump
        mari.update();
        assertFalse(mari.getIsJumping());
        expected.setY(expected.getY()-1);
        assertEquals(expected, mari.getPosition());
        assertEquals(0, mari.getJumpCounter());

        // Testing the character moving down
        for (int i = 1; i <= 5; i++) {
            mari.update();
            expected.setY(expected.getY() + 1);

            assertEquals(expected, mari.getPosition());
            assertFalse(mari.getIsJumping());
            assertEquals(0, mari.getJumpCounter());
        }

        // Testing the character reach the ground
        mari.update();
        assertEquals(expected, mari.getPosition());

    }
     */

    @Test
    public void mariLives() {
        assertEquals(3, mari.getRemainingLives());

        for (int i = 2; i >= (-2); i--) {
            mari.decreaseLives();
            assertEquals(i, mari.getRemainingLives());
        }
    }

    @Test
    public void mariKey() {
        assertFalse(mari.getWithKey());
        mari.setWithKey(true);
        assertTrue(mari.getWithKey());
    }

}
