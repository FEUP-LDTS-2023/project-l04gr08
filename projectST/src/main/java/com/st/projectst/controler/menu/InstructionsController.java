package com.st.projectst.controler.menu;

import com.st.projectst.gui.GUI;

import java.io.IOException;

public class InstructionsController extends Controller<Instructions>{
    public InstructionsController(Instructions instructions) {
        super(instructions);
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().isSelectedGoBack()) main.setState(new StartState(new Start()));
        }
    }
}
