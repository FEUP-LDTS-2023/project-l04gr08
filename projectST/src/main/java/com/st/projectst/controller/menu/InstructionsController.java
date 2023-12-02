package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.menu.Instructions;

import java.io.IOException;

public class InstructionsController extends Controller<Instructions> {
    public InstructionsController(Instructions instructions) {
        super(instructions);
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {

    }
}
