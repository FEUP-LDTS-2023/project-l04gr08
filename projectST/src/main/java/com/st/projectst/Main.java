package com.st.projectst;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine(80, 24);
        gameEngine.run();
    }
}