package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.menu.Start;

import java.io.IOException;

public class StartController extends Controller<Start> {
    public StartController(Start start) {
        super(start);
    }

    @Override
    public void step(Main game, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousOption();
                break;
            case DOWN:
                getModel().nextOption();
                break;
            case SELECT:
                /*
                if (getModel().isSelectedStart()) game.setState(new LevelState(new Level()));
                else if (getModel().isSelectedInstructions()) game.setState(new InstructionsState(new Instructions()));
                else if (getModel().isSelectedExit()) game.setState(null);
                 */
        }
    }
}
