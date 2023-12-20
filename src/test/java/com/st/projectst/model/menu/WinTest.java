package com.st.projectst.model.menu;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class WinTest {

    @Test
    void testGetLevel() {
        int level = 5;
        Win win = new Win(level);
        assertEquals(level, win.getLevel());
    }

    @Test
    void testIsSelectedGoBackToLevels() {
        Win win = new Win(1);
        assertTrue(win.isSelectedGoBackToLevels());
        win.isSelectedGoBackToLevels();
        assertTrue(win.isSelected(0));
        assertFalse(win.isSelected(1));
        win.setCurrentOption(1);
        assertFalse(win.isSelectedGoBackToLevels());
    }

    @Test
    void testIsSelectedContinue() {
        Win win = new Win(1);
        win.nextOption();
        assertTrue(win.isSelectedContinue());
        win.previousOption();
        assertTrue(win.isSelectedGoBackToLevels());
    }

    @Test
    void testNextOption(){
        Win win = new Win(1);
        win.setCurrentOption(0);
        win.nextOption();
        assertEquals(win.getCurrentOption(), 1);
        win.setCurrentOption(win.getNumberOptions()-1);
        assertEquals(win.getCurrentOption(), win.getNumberOptions()-1);
        win.setCurrentOption(win.getNumberOptions());
        assertEquals(win.getCurrentOption(), win.getNumberOptions());

        win = new Win(5);
        win.setCurrentOption(win.getNumberOptions() - 2);
        win.nextOption();
        assertEquals(win.getNumberOptions() - 1, win.getCurrentOption());
        win.setCurrentOption(1);
        assertEquals(1, win.getCurrentOption());
    }
    @Test
    void testNumberOptions() {
        Win win = new Win(1);
        assertEquals(2, win.getNumberOptions());
    }

    @Test
    void testPreviousOption(){
        Win win = new Win(1);
        win.setCurrentOption(0);
        win.previousOption();
        assertEquals(win.getCurrentOption(), 0);

        win.setCurrentOption(1);
        win.previousOption();
        assertEquals(win.getCurrentOption(), 0);

    }

    @Test
    void testIsSelected(){
        Win win = new Win(1);
        win.isSelected(1);
        assertEquals(win.getCurrentOption(), 0);
        assertNotEquals(win.getCurrentOption(), 1);
        assertEquals(win.isSelected(0), win.isSelectedGoBackToLevels());
        assertEquals(win.isSelected(1), win.isSelectedContinue());
        assertNotEquals(win.isSelected(0), win.isSelectedContinue());
        assertNotEquals(win.isSelected(1), win.isSelectedGoBackToLevels());
    }

    @Test
    void testGetNumberOptions() {
        Win win = new Win(1);
        int numberOfOptions = win.getNumberOptions();
        assertEquals(2, numberOfOptions);
    }
    @Test
    void testGetOption() {
        List<String> options = Arrays.asList(" Go Back ", "Next Level");
        Win win = new Win(1);
        for (int i = 0; i < options.size(); i++) {
            assertEquals(options.get(i), win.getOption(i));
        }
    }
}

