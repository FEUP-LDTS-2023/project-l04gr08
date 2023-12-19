package com.st.projectst.controller.game;
import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.*;
import com.st.projectst.model.game.*;
import com.st.projectst.states.GameOverState;
import com.st.projectst.states.PauseState;
import com.st.projectst.states.StartState;
import com.st.projectst.states.WinState;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;
import java.net.URISyntaxException;
import java.awt.FontFormatException;
import java.util.Arrays;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class MapControllerTest {
    private MapController mapController;
    private Map map;
    private Main main;

    @BeforeEach
    void setUp() {
        map = mock(Map.class);
        mapController = new MapController(map);
        main = mock(Main.class);
    }

    @Test
    void testStepQuit() throws IOException, URISyntaxException, FontFormatException {
        when(map.isAtDoor(any())).thenReturn(false);

        mapController.step(main, GUI.ACTION.QUIT, 1000);

        verify(main).setState(any(StartState.class));
    }

    @Test
    void testStepPause() throws IOException, URISyntaxException, FontFormatException {
        when(main.getGui()).thenReturn(mock(LanternaGUI.class));

        mapController.step(main, GUI.ACTION.PAUSE, 1000);

        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(PauseState.class));
    }

    @Test
    void testStepWin() throws IOException, URISyntaxException, FontFormatException {
        when(map.isAtDoor(any())).thenReturn(true);
        when(map.getMari()).thenReturn(mock(Mari.class));
        when(map.getCurrentLevel()).thenReturn(1);
        when(map.getMari().getWithKey()).thenReturn(true);
        when(main.getGui()).thenReturn(mock(LanternaGUI.class));

        mapController.step(main, GUI.ACTION.NONE, 1000);

        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(WinState.class));
    }

    @Test
    void testStepGameOver() throws IOException, URISyntaxException, FontFormatException {
        Mari mari = mock(Mari.class);
        when(map.getMari()).thenReturn(mari);
        when(mari.getRemainingLives()).thenReturn(0);
        when(main.getGui()).thenReturn(mock(LanternaGUI.class));

        mapController.step(main, GUI.ACTION.NONE, 1000);

        verify(main).setGui(any(LanternaGUI.class));
        verify(main).setState(any(GameOverState.class));
    }

}

