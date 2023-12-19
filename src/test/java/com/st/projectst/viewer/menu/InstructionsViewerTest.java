package com.st.projectst.viewer.menu;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.menu.Instructions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.io.IOException;

import static org.mockito.Mockito.verify;

public class InstructionsViewerTest {
    private GUI gui;
    private Instructions instructions;

    @BeforeEach
    void setup() {
        gui = Mockito.mock(GUI.class);
        instructions = Mockito.mock(Instructions.class);
    }

    @Test
    void testDrawObject() throws IOException, FontFormatException {
        InstructionsViewer instructionsViewer = new InstructionsViewer(instructions);
        instructionsViewer.drawObject(gui);

        verify(gui).setBackgroundColor("#BA6156");
    }
}
