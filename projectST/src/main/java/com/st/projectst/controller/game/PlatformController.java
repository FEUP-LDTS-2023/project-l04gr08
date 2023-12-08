package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.Platform;

import java.io.IOException;

public class PlatformController extends LevelController{
    private long lastMove;

    public PlatformController(Map map) {
        super(map);
        this.lastMove = 0;
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {
        if (time - lastMove > 500) {
            for (Platform platform : getModel().getPlatforms()){
                platform.moveAllPlatforms();
            }
            this.lastMove = time;
        }
    }
}
