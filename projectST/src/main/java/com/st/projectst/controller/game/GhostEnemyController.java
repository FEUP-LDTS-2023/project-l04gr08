package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.GhostEnemy;
import com.st.projectst.model.game.Map;
import com.st.projectst.model.Position;

import java.io.IOException;

public class GhostEnemyController extends LevelController{
    private long lastMove;
    private long lastAttack;

    public GhostEnemyController(Map map) {
        super(map);
        this.lastMove = 0;
        this.lastAttack = 0;
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {
        /*
        if (getModel().getMari().getPosition().equals(enemy.getPosition())) {
            if (time - lastAttack > 3000000) {
                System.out.println("AAAAAAAAAAAAAAAA");
                getModel().getMari().decreaseLives();
                this.lastAttack = time;
            }
        }

         */

        if ((time - lastMove) > 500) {
            for (GhostEnemy enemy : getModel().getGhostEnemies())  {
                Position newPosition = new Position(enemy.move());
                if (getModel().isEmpty(newPosition))
                    enemy.setPosition(newPosition);
            }
            this.lastMove = time;
        }
    }

}
