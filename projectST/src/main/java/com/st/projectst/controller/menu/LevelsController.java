package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.MapBuilder;
import com.st.projectst.model.menu.Level;
import com.st.projectst.model.menu.Start;
import com.st.projectst.states.LevelState;
import com.st.projectst.states.StartState;

import java.io.IOException;

public class LevelsController extends Controller<Level> {
    public LevelsController(Level levels) {
        super(levels);
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {
        switch (action) {
            case UP:
                getModel().previousOption();
                break;
            case DOWN:
                getModel().nextOption();
                break;
            case SELECT:
                if (getModel().isSelectedGoBack()) {
                    main.setState(new StartState(new Start(0)));
                    break;
                }

                if (getModel().transitionToNextLevel()) {
                    int selectedOption = getModel().getCurrentOption();
                    main.setState(new LevelState(new MapBuilder(selectedOption + 1).buildMap()));
                }
                break;
        }
    }
}
