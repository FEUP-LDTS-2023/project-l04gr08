package com.st.projectst.controller.menu;

import com.st.projectst.Main;
import com.st.projectst.controller.Controller;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.MapBuilder;
import com.st.projectst.model.menu.Level;
import com.st.projectst.model.menu.Start;
import com.st.projectst.model.menu.Win;
import com.st.projectst.states.LevelState;
import com.st.projectst.states.StartState;

import java.io.IOException;

public class WinController extends Controller<Win> {
    private Map level;
    public WinController(Win win, Map level) {
        super(win);
        this.level = level;
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
                if (getModel().isSelectedContinue()){
                    int nextLevel = level.getCurrentLevel() + 1;
                    if (nextLevel == 3){
                        // Go back to the beginning of the game
                        main.setState(new StartState(new Start(0)));
                    }
                    main.setState(new LevelState(new MapBuilder(nextLevel).buildMap()));
                }
        }
    }
}
