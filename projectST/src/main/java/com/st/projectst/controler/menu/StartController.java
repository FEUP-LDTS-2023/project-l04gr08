package com.st.projectst.controler.menu;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;

import java.io.IOException;

public class StartController extends Controller<Start>{
    public StartController(Start start) {
        super(start);
    }

    @Override
    public void step(Main game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousEntry();
                break;
            case DOWN:
                getModel().nextEntry();
                break;
            case SELECT:
                if (getModel().isSelectedExit()) game.setState(null);
                if (getModel().isSelectedInstructions()) game.setState(new InstructionsState(new Instructions()));
                if (getModel().isSelectedStart()) game.setState(new LevelsState(new Levels()));
        }
    }
}
