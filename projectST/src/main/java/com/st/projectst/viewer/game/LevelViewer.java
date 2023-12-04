package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
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
        drawGameObject(gui, getModel().getMari(), new MariViewer());
        drawGameObjects(gui, getModel().getWalls(), new WallViewer());
        drawGameObjects(gui, getModel().getBatEnemies(), new BatEnemyViewer());
        drawGameObjects(gui, getModel().getGhostEnemies(), new GhostEnemyViewer());
        drawGameObject(gui, getModel().getKey(), new KeyViewer());
    }

    private <T extends GameObject> void drawGameObjects(GUI gui, List<T> gameObjects, GameObjectViewer<T> viewer) throws IOException, FontFormatException {
        for (T object : gameObjects)
            drawGameObject(gui, object, viewer);
    }

    private <T extends GameObject> void drawGameObject(GUI gui, T element, GameObjectViewer<T> viewer) throws IOException, FontFormatException {
        viewer.draw(element, gui);
    }
}
