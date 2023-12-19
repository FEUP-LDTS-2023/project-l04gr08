package com.st.projectst.model.menu;
import com.st.projectst.model.menu.GameOver;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GameOverTest {

    @Test
    void testInitialSelection() {
        GameOver gameOver = new GameOver();
        assertTrue(gameOver.isSelectedGoBack());
    }

    @Test
    void testNextOption() {
        GameOver gameOver = new GameOver();
        assertTrue(gameOver.isSelectedGoBack());
        gameOver.nextOption();
        assertTrue(gameOver.isSelectedGoBack());
    }

    @Test
    void testGetOption() {
        GameOver gameOver = new GameOver();
        String expectedOption = " Return to the Menu ";
        String retrievedOption = gameOver.getOption(0);

        assertEquals(expectedOption, retrievedOption);
    }
    @Test
    void testPreviousOption() {
        GameOver gameOver = new GameOver();
        assertTrue(gameOver.isSelectedGoBack());
        gameOver.previousOption();
        assertTrue(gameOver.isSelectedGoBack());
    }
}

