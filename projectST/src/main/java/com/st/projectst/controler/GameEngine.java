package com.st.projectst.controler;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.st.projectst.model.Position;
import com.st.projectst.model.Hero;

import java.io.IOException;

public class GameEngine {
    private static Screen screen;
    private static TextGraphics graphics;
    private Terminal terminal;
    private Hero hero;

    public GameEngine(int width, int height){
        try {
            this.terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(terminal);
            screen.startScreen();
            graphics = screen.newTextGraphics();

            TextGraphics graphics = screen.newTextGraphics();

            int screenWidth = width;
            int screenHeight = height;
            hero = new Hero(new Position(screenWidth / 2, screenHeight - 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        hero.update();
        hero.draw(graphics);
        screen.refresh();
    }

    public void run(){
        try {
            while (true) {
                draw();
                KeyStroke keyStroke = terminal.pollInput();
                if (keyStroke != null) {
                    processKey(keyStroke);
                    if (keyStroke.getKeyType() == KeyType.Escape) {
                        break;
                    }
                }
                Thread.sleep(30);
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()){
            case ArrowRight:
                hero.moveRight();
                break;
            case ArrowLeft:
                hero.moveLeft();
                break;
            case Character:
                if (key.getCharacter() == ' ')
                    hero.jump();
                break;
        }
    }
}
