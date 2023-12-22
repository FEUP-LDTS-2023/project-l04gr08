package com.st.projectst.controller.game;
import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.Platform;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class PlatformControllerTest {
    private Main main;
    private Map mockedMap;
    private PlatformController platformController;

    @BeforeEach
    void setUp() {
        main = Mockito.mock(Main.class);
        mockedMap = Mockito.mock(Map.class);
        platformController = new PlatformController(mockedMap);
    }

    @Test
    void testStep() throws IOException {
        List<Platform> platformList = new ArrayList<>();
        Platform mockedPlatform1 = Mockito.mock(Platform.class);
        Platform mockedPlatform2 = Mockito.mock(Platform.class);
        platformList.add(mockedPlatform1);
        platformList.add(mockedPlatform2);

        when(mockedMap.getPlatforms()).thenReturn(platformList);

        long time = 200;
        GUI.ACTION action = GUI.ACTION.NONE;
        platformController.step(main, action, time);
        verify(mockedPlatform1, times(1)).moveAllPlatforms();
        verify(mockedPlatform2, times(1)).moveAllPlatforms();

    }
}
