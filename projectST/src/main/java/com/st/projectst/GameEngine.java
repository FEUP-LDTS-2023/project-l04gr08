package com.st.projectst;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class GameEngine {
    private static Screen screen;
    private Terminal terminal;
    private Position position;

    public GameEngine(int width, int height){
        try {
            TerminalSize terminalSize = new TerminalSize(width, height);
            this.terminal = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize).createTerminal();
            this.screen = new TerminalScreen(terminal);
            position.setX(width/2);
            position.setY(height/2);

            screen.setCursorPosition(null);
            screen.startScreen();
            screen.doResizeIfNecessary();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('H')[0]);
        screen.refresh();
    }

    public void run(){
        try {
            while (true) {
                draw();
                KeyStroke key = screen.readInput();
                processKey(key);
                if (key.getKeyType() == KeyType.EOF || (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q')) {
                    break;
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        switch (key.getKeyType()){
            case ArrowRight:
                position = new Position(position.getX() + 1, position.getY());
                break;
            case ArrowLeft:
                position = new Position(position.getX()-1, position.getY());
                break;
            case ArrowDown:
                position = new Position(position.getX(), position.getY() + 1);
                break;
            case ArrowUp:
                position = new Position(position.getX(), position.getY() - 1);
                break;
        }
    }
}
