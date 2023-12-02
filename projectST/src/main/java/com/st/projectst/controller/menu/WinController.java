package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.menu.Win;

import java.io.IOException;

public class WinController extends Controller<Win> {
    public WinController(Win win) {
        super(win);
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {

    }
}
