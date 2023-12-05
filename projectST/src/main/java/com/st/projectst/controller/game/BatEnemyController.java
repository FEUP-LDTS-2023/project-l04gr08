package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.BatEnemy;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.Position;

import java.io.IOException;

public class BatEnemyController extends LevelController{
    private long lastMove;

    public BatEnemyController(Map map) {
        super(map);
        this.lastMove = 0;
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {
        if (time - lastMove > 300) {
            for (BatEnemy enemy : getModel().getBatEnemies()) {
                enemy.setPosition(enemy.move());
                if (getModel().getMari().getPosition().equals(enemy.getPosition()))
                    getModel().getMari().decreaseLives();
            }
            this.lastMove = time;
        }


    }

}
