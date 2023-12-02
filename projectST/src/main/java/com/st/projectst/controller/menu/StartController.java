package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.MapBuilder;
import com.st.projectst.model.menu.Instructions;
import com.st.projectst.model.menu.Start;
import com.st.projectst.states.InstructionsState;
import com.st.projectst.states.LevelState;

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
                if (getModel().isSelectedStart()) game.setState(new LevelState(new MapBuilder(1).buildMap()));
                else if (getModel().isSelectedInstructions()) game.setState(new InstructionsState(new Instructions()));
                else if (getModel().isSelectedExit()) game.setState(null);

        }
    }
}
