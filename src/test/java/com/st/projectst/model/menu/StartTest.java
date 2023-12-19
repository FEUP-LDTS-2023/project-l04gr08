package com.st.projectst.model.menu;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class StartTest {

    @Test
    void testPreviousOption() {
        Start start = new Start(1);
        start.previousOption();
        assertTrue(start.isSelectedStart());
    }

    @Test
    void testNextOption() {
        Start start = new Start(0);
        start.nextOption();
        assertTrue(start.isSelectedInstructions());
    }

    @Test
    void testGetOption() {
        Start start = new Start(2);
        String option = start.getOption(0);
        assertEquals("Start", option);
    }
    @Test
    void testIsSelectedExit() {
        Start start = new Start(2);
        assertTrue(start.isSelectedExit());
    }

    @Test
    void testGetNumberOptions() {
        Start start = new Start(0);
        int numberOfOptions = start.getNumberOptions();
        assertEquals(3, numberOfOptions);
    }
}

