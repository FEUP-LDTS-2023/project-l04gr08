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

