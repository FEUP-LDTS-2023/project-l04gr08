package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.Mari;

public class MariViewer implements GameObjectViewer<Mari> {
    @Override
    public void draw(Mari mari, GUI gui) {
        gui.drawMari(mari.getPosition());
    }
}