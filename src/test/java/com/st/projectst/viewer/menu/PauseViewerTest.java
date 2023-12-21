package com.st.projectst.viewer.menu;

import com.groupcdg.pitest.annotations.DoNotMutate;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.menu.Pause;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

public class PauseViewerTest {
    private GUI gui;
    private Pause pause;
    private PauseViewer pauseViewer;

    @BeforeEach
    void setup() {
        gui = Mockito.mock(GUI.class);
        pause = Mockito.mock(Pause.class);
        pauseViewer = new PauseViewer(pause);

        when(pause.getNumberOptions()).thenReturn(2);
        when(pause.getOption(0)).thenReturn("Continue");
        when(pause.getOption(1)).thenReturn("Go back to menu");
    }

    @DoNotMutate
    @Test
    void testDrawObject() {
        pauseViewer.drawObject(gui);

        verify(gui).setBackgroundColor("#BA6156");
        verify(gui).drawText(new Position(7, 5), "OOOOO  OOOOOO  OO  OO  OOOOOO  OOOOOO", "#f9dbbe");
        verify(gui).drawText(new Position(7, 6), "OO OO  OO  OO  OO  OO  OO      OO", "#ffbc6e");
        verify(gui).drawText(new Position(7, 7), "OOOOO  OOOOOO  OO  OO  OOOOOO  OOOOOO", "#FF9966");
        verify(gui).drawText(new Position(7, 8), "OO     OO  OO  OO  OO      OO  OO", "#ff8066");
        verify(gui).drawText(new Position(7, 9), "OO     OO  OO  OOOOOO  OOOOOO  OOOOOO", "#ff9aab");
    }

    @DoNotMutate
    @Test
    void testDrawSelected1() {
        when(pause.isSelected(0)).thenReturn(true);
        when(pause.isSelected(1)).thenReturn(false);

        pauseViewer.drawObject(gui);
        for (int i = 0; i < pause.getNumberOptions(); i++) {
            verify(gui, times(1)).drawText(
                    new Position(21 - (i * 3), 15 + i),
                    pause.getOption(i),
                    pause.isSelected(i) ? "#FFFFFF" : "#F1A55E");
        }
        verify(gui, times(0)).drawText(
                new Position(21 - (pause.getNumberOptions() * 3), 15 + pause.getNumberOptions()),
                pause.getOption(pause.getNumberOptions()),
                pause.isSelected(pause.getNumberOptions()) ? "#FFFFFF" : "#F1A55E");
    }
    @DoNotMutate
    @Test
    void testDrawSelected2() {
        when(pause.isSelected(0)).thenReturn(false);
        when(pause.isSelected(1)).thenReturn(true);

        pauseViewer.drawObject(gui);
        for (int i = 0; i < pause.getNumberOptions(); i++) {
            verify(gui, times(1)).drawText(
                    new Position(21 - (i * 3), 15 + i),
                    pause.getOption(i),
                    pause.isSelected(i) ? "#FFFFFF" : "#F1A55E");
        }
        verify(gui, times(0)).drawText(
                new Position(21 - (pause.getNumberOptions() * 3), 15 + pause.getNumberOptions()),
                pause.getOption(pause.getNumberOptions()),
                pause.isSelected(pause.getNumberOptions()) ? "#FFFFFF" : "#F1A55E");
    }

}
