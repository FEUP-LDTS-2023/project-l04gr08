package com.st.projectst.viewer.game;

import com.st.projectst.gui.GUI;
import com.st.projectst.model.game.Door;
import com.st.projectst.model.game.Potion;

import java.awt.*;
import java.io.IOException;

public class PotionViewer implements GameObjectViewer<Potion>{
    @Override
    public void draw(Potion potion, GUI gui) {
        gui.drawPotion(potion.getPosition());
    }
}
