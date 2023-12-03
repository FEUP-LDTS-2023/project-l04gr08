package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.menu.Level;
import com.st.projectst.model.menu.Win;
import com.st.projectst.states.LevelState;

import java.io.IOException;

public class WinController extends Controller<Win> {
    public WinController(Win win) {
        super(win);
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
                if (getModel().isSelectedGoBackToLevels()) {
                    main.setState(new LevelState(new Map(0)));
                }
        }
    }
}
