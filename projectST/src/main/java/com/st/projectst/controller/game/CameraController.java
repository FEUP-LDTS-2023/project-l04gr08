package com.st.projectst.controller.game;

import com.st.projectst.Main;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.*;

import java.io.IOException;

public class CameraController extends LevelController {
    public CameraController(Map map) {
        super(map);
    }

    @Override
    public void step(Main main, GUI.ACTION action, long time) throws IOException {

        Map map = getModel();

        for (GhostEnemy ghostEnemy : map.getGhostEnemies()) {
            Position enemyScreenPosition = new Position(
                    ghostEnemy.getPosition().getX() - getModel().getMari().getPosition().getX() ,
                    ghostEnemy.getPosition().getY()
            );
            ghostEnemy.setPosition(enemyScreenPosition);
        }

        for (BatEnemy batEnemy : map.getBatEnemies()) {
            Position enemyScreenPosition = new Position(
                    batEnemy.getPosition().getX() - getModel().getMari().getPosition().getX() ,
                    batEnemy.getPosition().getY()
            );
            batEnemy.setPosition(enemyScreenPosition);
        }

        for (Wall wall : map.getWalls()) {
            Position wallScreenPosition = new Position(
                    wall.getPosition().getX() - getModel().getMari().getPosition().getX()  ,
                    wall.getPosition().getY()
            );
            wall.setPosition(wallScreenPosition);
        }

        for (Trap trap : map.getTraps()){
            Position trapScreenPosition = new Position(
                    trap.getPosition().getX() - getModel().getMari().getPosition().getX()  ,
                    trap.getPosition().getY()
            );
            trap.setPosition(trapScreenPosition);
        }

        Key key = map.getKey();
        Position keyScreenPosition = new Position(
                key.getPosition().getX() - getModel().getMari().getPosition().getX()  ,
                key.getPosition().getY()
        );
        key.setPosition(keyScreenPosition);

        Door door = map.getDoor();
        Position doorScreenPosition = new Position(
                door.getPosition().getX() - getModel().getMari().getPosition().getX() ,
                door.getPosition().getY()
        );
        door.setPosition(doorScreenPosition);
    }
}

