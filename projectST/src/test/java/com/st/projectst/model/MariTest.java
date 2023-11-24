package com.st.projectst.model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Mari;
import com.st.projectst.model.Position;
import com.st.projectst.model.Wall;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

public class MariTest {
    Mari mari;
    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;

    @BeforeEach
    public void setup() {
        Position initialPosition = new Position(40,23);
        mari = new Mari(initialPosition);
        SCREEN_WIDTH = 80;
        SCREEN_HEIGHT = 24;
    }


    @Test
    public void mariMoveRight() {
        mari.moveRight();
        Position expected = new Position(41,23);

        assertEquals(expected, mari.getPosition());
    }

    @Test
    public void mariMoveLeft() {
        mari.moveLeft();
        Position expected = new Position(39,23);
        assertEquals(expected, mari.getPosition());
    }

    @Test
    public void mariJump() {
        mari.jump();
        assertTrue(mari.getIsJumping());
    }

    @Test
    public void mariUpdateNoJumping() {
        mari.update();
        Position expected = new Position(40,23);

        assertEquals(expected, mari.getPosition());
    }

    @Test
    public void mariUpdateJumping() {
        mari.jump();
        assertTrue(mari.getIsJumping());
        assertEquals(0, mari.getJumpCounter());

        // Testing the character moving up on the jump
        Position expected = new Position(40,23);
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
        for (int i = 1; i <= 6; i++) {
            mari.update();
            expected.setY(expected.getY()+1);
            assertEquals(expected, mari.getPosition());

            assertFalse(mari.getIsJumping());
            assertEquals(0, mari.getJumpCounter());
        }

        // Testing the character reach the ground
        mari.update();
        assertEquals(expected, mari.getPosition());
    }
    @Test
    public void testMoveLeftWithSubtraction() {
        Mari mari = new Mari(new Position(0, 0));
        mari.moveLeft();
        Position expectedPosition = new Position(-1, 0);
        assertEquals(expectedPosition, mari.getPosition());
    }

    @Test
    public void testIsJumpingNegated() {
        Mari mari = new Mari(new Position(0, 0));
        assertFalse(mari.getIsJumping());
        mari.jump();
        assertTrue(mari.getIsJumping());
    }

    @Test
    public void mariCollisionWithObstacle() {
        // Mocking the Obstacle class
        Wall wall = Mockito.mock(Wall.class);
        Mockito.when(wall.getPosition()).thenReturn(new Position(41, 23));

        // Move Mari to the obstacle position
        while (!mari.getPosition().equals(wall.getPosition())) {
            if (mari.getPosition().getX() < wall.getPosition().getX()) {
                mari.moveRight();
            } else {
                mari.moveLeft();
            }
        }

        assertEquals(mari.getPosition(), wall.getPosition());
    }

    @Test
    public void mariDraw() {
        TextGraphics mockGraphics = Mockito.mock(TextGraphics.class);

        mari.draw(mockGraphics);

        Mockito.verify(mockGraphics, Mockito.times(1)).setBackgroundColor(TextColor.ANSI.CYAN);
        Mockito.verify(mockGraphics, Mockito.times(1)).putString(
                Mockito.eq(new TerminalPosition((int) mari.getPosition().getX(), (int) mari.getPosition().getY())),
                Mockito.eq(" ")
        );
    }
}
