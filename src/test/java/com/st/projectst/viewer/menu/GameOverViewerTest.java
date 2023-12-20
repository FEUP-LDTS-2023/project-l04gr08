package com.st.projectst.viewer.menu;

import com.groupcdg.pitest.annotations.DoNotMutate;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.menu.GameOver;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.verify;

public class GameOverViewerTest {
    private GUI gui;
    private GameOver gameOver;

    @BeforeEach
    void setup() {
        gui = Mockito.mock(GUI.class);
        gameOver = Mockito.mock(GameOver.class);
    }
    @DoNotMutate
    @Test
    void testDrawObject() {
        GameOverViewer gameOverViewer = new GameOverViewer(gameOver);
        gameOverViewer.drawObject(gui);

        verify(gui).setBackgroundColor("#BA6156");
        verify(gui).drawText(new Position(15, 3), "  OOOO                                  " , "#f9dbbe");
    }

}

