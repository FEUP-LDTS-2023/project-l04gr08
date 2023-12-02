package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.GameObject;
import com.st.projectst.model.game.Map;
import com.st.projectst.viewer.Viewer;

import java.util.List;

public class LevelViewer extends Viewer<Map> {
    public LevelViewer(Map model) {
        super(model);
    }

    @Override
    public void drawObject(GUI gui) {

        //drawObject(gui, getModel().getWalls(), new WallViewer());
        //drawElements(gui, getModel().getMonsters(), new MonsterViewer());
        drawGameObject(gui, getModel().getMari(), new MariViewer());
    }

    private <T extends GameObject> void drawGameObjects(GUI gui, List<T> gameObjects, GameObjectViewer<T> viewer) {
        for (T object : gameObjects)
            drawGameObject(gui, object, viewer);
    }

    private <T extends GameObject> void drawGameObject(GUI gui, T element, GameObjectViewer<T> viewer) {
        viewer.draw(element, gui);
    }
}
