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
        Position cameraPosition = map.getCamera().getPosition();

        for (GhostEnemy ghostEnemy : map.getGhostEnemies()) {
            Position enemyScreenPosition = new Position(
                    ghostEnemy.getPosition().getX() - cameraPosition.getX() + 5,
                    ghostEnemy.getPosition().getY() - cameraPosition.getY()
            );
            ghostEnemy.setPosition(enemyScreenPosition);
        }

        for (BatEnemy batEnemy : map.getBatEnemies()) {
            Position enemyScreenPosition = new Position(
                    batEnemy.getPosition().getX() - cameraPosition.getX() + 5,
                    batEnemy.getPosition().getY() - cameraPosition.getY()
            );
            batEnemy.setPosition(enemyScreenPosition);
        }

        for (Wall wall : map.getWalls()) {
            Position wallScreenPosition = new Position(
                    wall.getPosition().getX() - cameraPosition.getX() + 5,
                    wall.getPosition().getY() - cameraPosition.getY()
            );
            wall.setPosition(wallScreenPosition);
        }

        for (Trap trap : map.getTraps()){
            Position trapScreenPosition = new Position(
                    trap.getPosition().getX() - cameraPosition.getX() + 5,
                    trap.getPosition().getY() - cameraPosition.getY()
            );
            trap.setPosition(trapScreenPosition);
        }

        Key key = map.getKey();
        Position keyScreenPosition = new Position(
                key.getPosition().getX() - cameraPosition.getX() + 5,
                key.getPosition().getY() - cameraPosition.getY()
        );
        key.setPosition(keyScreenPosition);

        Door door = map.getDoor();
        Position doorScreenPosition = new Position(
                door.getPosition().getX() - cameraPosition.getX() + 5,
                door.getPosition().getY() - cameraPosition.getY()
        );
        door.setPosition(doorScreenPosition);
        main.getGui().drawMap(getModel());
    }
}

