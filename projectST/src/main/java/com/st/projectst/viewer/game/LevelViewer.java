package com.st.projectst.viewer.game;

import com.googlecode.lanterna.TerminalPosition;
import com.st.projectst.gui.GUI;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Camera;
import com.st.projectst.model.game.GameObject;
import com.st.projectst.model.game.Map;
import com.st.projectst.viewer.Viewer;

import java.awt.*;
import java.io.IOException;
import java.util.List;

public class LevelViewer extends Viewer<Map> {
    public LevelViewer(Map model) {
        super(model);
    }

    @Override
    public void drawObject(GUI gui) throws IOException, FontFormatException {
        if (getModel().getMari().getRemainingLives() == 3){
            gui.drawImage(new Position(1, -2), "life3.png", 1);
        }
        else if (getModel().getMari().getRemainingLives() == 2){
            gui.drawImage(new Position(1, -2), "life2.png", 1);
        }
        else if (getModel().getMari().getRemainingLives() == 1){
            gui.drawImage(new Position(1, -2), "life1.png", 1);
        }

        gui.drawText(new Position(1, 4), "" + getModel().getMari().getRemainingLives(), "#FFFFFF");
        drawGameObject(gui, getModel().getMari(), new MariViewer());
        drawGameObjects(gui, getModel().getWalls(), new WallViewer());
        drawGameObjects(gui, getModel().getBatEnemies(), new BatEnemyViewer());
        drawGameObjects(gui, getModel().getGhostEnemies(), new GhostEnemyViewer());
        drawGameObject(gui, getModel().getKey(), new KeyViewer());
        drawGameObject(gui, getModel().getDoor(), new DoorViewer());
    }

    private <T extends GameObject> void drawGameObjects(GUI gui, List<T> gameObjects, GameObjectViewer<T> viewer) throws IOException, FontFormatException {
        for (T object : gameObjects)
            drawGameObject(gui, object, viewer);
    }

    private <T extends GameObject> void drawGameObject(GUI gui, T element, GameObjectViewer<T> viewer) throws IOException, FontFormatException {
        viewer.draw(element, gui);
    }
}
