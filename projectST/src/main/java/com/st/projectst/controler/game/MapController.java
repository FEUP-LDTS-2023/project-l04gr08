package com.st.projectst.controler.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Map;

import java.io.IOException;

public class MapController extends LevelController{
    private final MariController mariController;
    private final GhostEnemyController ghostController;
    private final BatEnemyController batController;

    public MapController(Map map) {
        super(map);

        this.mariController = new MariController(map);
        this.ghostController = new GhostEnemyController(map);
        this.batController = new BatEnemyController(map);
    }

    public void step(Main main, GUI.ACTION action, long time) throws IOException {
        if (action == GUI.ACTION.QUIT || getModel().getMari().getRemainingLives() == 0){
            //main.setState(new MenuState(new Menu()));
        }
        else {
            mariController.step(main, action, time);
            ghostController.step(main, action, time);
            batController.step(main, action, time);
        }
    }
}
