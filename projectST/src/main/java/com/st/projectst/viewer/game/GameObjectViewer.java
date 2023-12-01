package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.GameObject;

public interface GameObjectViewer<T extends GameObject> {
    void draw(T element, GUI gui);
}