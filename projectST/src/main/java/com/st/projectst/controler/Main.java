package com.st.projectst.controler;

import com.st.projectst.controler.GameEngine;
import com.st.projectst.gui.LanternaGUI;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine(80, 24);
        gameEngine.run();
    }
}