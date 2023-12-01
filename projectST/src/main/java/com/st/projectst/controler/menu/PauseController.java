package com.st.projectst.controler.menu;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;

import java.io.IOException;

public class PauseController extends Constroller<Pause>{
    public PauseController(Pause pause) {
        super(win);
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
                if (getModel().isSelectedGoBackToLevels()) {
                    game.setState(new LevelsState(new Levels()));
                }
        }
    }
}
