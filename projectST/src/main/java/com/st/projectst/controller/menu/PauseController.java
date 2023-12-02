package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.menu.Pause;

import java.io.IOException;

public class PauseController extends Controller<Pause> {
    public PauseController(Pause pause) {
        super(pause);
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {

    }
}
