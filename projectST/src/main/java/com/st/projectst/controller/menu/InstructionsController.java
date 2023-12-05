package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.menu.Instructions;
import com.st.projectst.model.menu.Start;
import com.st.projectst.states.StartState;

import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;

public class InstructionsController extends Controller<Instructions> {
    public InstructionsController(Instructions instructions) {
        super(instructions);
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException, URISyntaxException, FontFormatException {
        switch (action) {
            case SELECT:
                if (getModel().isSelectedBack()) {
                    main.getGui().close();
                    LanternaGUI gui = new LanternaGUI(40, 20, 24);
                    main.setGui(gui);
                    main.setState(new StartState(new Start(1)));
                }
                break;
        }
    }
}
