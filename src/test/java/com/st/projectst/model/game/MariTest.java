package com.st.projectst.model.game;

import com.st.projectst.model.Position;
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
    void testUpdateWhenJumpingAndMovingLeft() {
        Mari mari = new Mari(new Position(0, 0));
        mari.jump();
        mari.setJumpRight(false);
        mari.update();

        assertEquals(0, mari.getJumpCounter());
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
    public void mariDoubleJumpNotGrounded() {
        mari.doubleJump();
        assertFalse(mari.getIsJumping());

        mari.setGrounded(false);
        mari.doubleJump();
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
        Position position = mari.update();
        Position expected = new Position(10,11);
        assertEquals(expected, position);
    }

    @Test
    public void mariUpdateNoJumpingGrounded() {
        mari.setGrounded(true);
        mari.update();
        Position expected = new Position(10,10);

        assertEquals(expected, mari.getPosition());
    }

    @Test
    void testDoubleJump() {
        Mari mari = new Mari(new Position(0, 0));
        mari.setGrounded(true);
        mari.jump();
        mari.update();
        Position newPosition = mari.doubleJump();
        assertTrue(mari.getIsJumping());
        assertFalse(mari.getGrounded());
        assertEquals(-1, newPosition.getY());
    }

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
