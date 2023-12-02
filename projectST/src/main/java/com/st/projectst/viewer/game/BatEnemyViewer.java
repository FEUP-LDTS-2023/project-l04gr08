package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.BatEnemy;

public class BatEnemyViewer implements GameObjectViewer<BatEnemy> {
    @Override
    public void draw(BatEnemy bat, GUI gui) {
        gui.drawBatEnemy(bat.getPosition());
    }
}