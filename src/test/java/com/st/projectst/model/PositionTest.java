package com.st.projectst.model;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;

public class PositionTest {

    @Test
    void testGetLeft() {
        Position pos = new Position(5, 5);
        Position left = pos.getLeft();
        assertEquals(4, left.getX());
        assertEquals(5, left.getY());
    }

    @Test
    void testGetRight() {
        Position pos = new Position(5, 5);
        Position right = pos.getRight();
        assertEquals(6, right.getX());
        assertEquals(5, right.getY());
    }

    @Test
    void testEquals() {
        Position pos1 = new Position(3, 4);
        Position pos2 = new Position(3, 4);
        Position pos3 = new Position(3, 5);

        assertEquals(pos1, pos2);
        assertNotEquals(pos1, pos3);
    }

    @Test
    void testEqualsReturnsTrueForEqualPositions() {
        Position position1 = new Position(5, 5);
        Position position2 = new Position(5, 5);
        assertTrue(position1.equals(position2));
    }

    @Test
    void testEqualsReturnsFalseForEqualPositions() {
        Position position1 = new Position(4, 5);
        Position position2 = new Position(5, 5);
        assertFalse(position1.equals(position2));
    }

    @Test
    void testGetRandomHorizontal() {
        Position pos = new Position(5, 5);
        Position random = pos.getRandomHorizontal();
        assertTrue(random.equals(pos.getLeft()) || random.equals(pos.getRight()));
    }

    @Test
    void testGetRandomHorizontalNotNull() {
        Position pos = new Position(5, 5);
        Position random = pos.getRandomHorizontal();
        assertNotNull(random);
    }



}

