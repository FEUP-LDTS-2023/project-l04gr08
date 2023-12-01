package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;

public class PlatformViewer implements GameObjectViewer<Platform> {
    @Override
    public void draw(Platform platform, GUI gui) {
        gui.drawPlatform(platform.getPosition());
    }
}
