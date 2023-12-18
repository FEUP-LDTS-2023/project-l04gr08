package com.st.projectst.gui;

import com.googlecode.lanterna.screen.Screen;
import com.st.projectst.model.Position;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;


public class LanternaGUITest {

    @Test
    void teste() throws IOException {

    }

    /*
    @Test
    void testStartScreen() throws IOException {
        Screen mockedScreen = mock(Screen.class);
        LanternaGUI lanternaGUI = new LanternaGUI(mockedScreen);
        lanternaGUI.startScreen();
        verify(mockedScreen).startScreen();
    }


    @Test
    void testGetNextAction() throws IOException {
        Screen mockedScreen = mock(Screen.class);
        LanternaGUI lanternaGUI = new LanternaGUI(mockedScreen);
        when(mockedScreen.pollInput()).thenReturn(null);
        assertEquals(GUI.ACTION.NONE, lanternaGUI.getNextAction());


    }

    @Test
    void testClear() throws IOException {
        LanternaGUI lanternaGUI = mock(LanternaGUI.class);
        lanternaGUI.clear();
        verify(lanternaGUI).clear();
    }

    @Test
    void testClose() throws IOException {
        LanternaGUI lanternaGUI = mock(LanternaGUI.class);
        lanternaGUI.close();
        verify(lanternaGUI).close();
    }

    @Test
    void testRefresh() throws IOException {
        LanternaGUI lanternaGUI = mock(LanternaGUI.class);
        lanternaGUI.refresh();
        verify(lanternaGUI).refresh();
    }


     */
}

