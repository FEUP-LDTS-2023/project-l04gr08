package com.st.projectst.states;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.menu.Start;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.mockito.Mockito.*;

public class StateTest {

    @Test
    void testStepWithStartState() throws IOException, URISyntaxException, FontFormatException {
        Start start = Mockito.mock(Start.class);
        StartState startState = new StartState(start);

        Main main = mock(Main.class);
        GUI gui = Mockito.mock(GUI.class);
        long time = 0;

        when(gui.getNextAction()).thenReturn(GUI.ACTION.NONE);
        startState.step(main, gui, time);
    }

}
