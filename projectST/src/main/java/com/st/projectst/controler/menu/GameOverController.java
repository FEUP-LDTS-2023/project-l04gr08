package com.st.projectst.controler.menu;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;

import java.io.IOException;

public class GameOverController extends Controller<GameOver>{
    public GameOverController(GameOver gameover) {
        super(gameover);
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
