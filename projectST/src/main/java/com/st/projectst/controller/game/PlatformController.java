package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.game.Platform;
import com.st.projectst.model.game.Wall;

import java.io.IOException;

public class PlatformController extends LevelController{
    private long lastMove;

    public PlatformController(Map map) {
        super(map);
        this.lastMove = 0;
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {
        int movementDuration = 500;
        double currentTime = (time % (2 * movementDuration));
        int directionY = currentTime < movementDuration ? -1 : 1;

        for (Platform platform : getModel().getPlatforms()) {
            platform.moveAllPlatforms(0, directionY);
        }
        this.lastMove = time;
    }
}
