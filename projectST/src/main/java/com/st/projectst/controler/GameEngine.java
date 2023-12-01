package com.st.projectst.controler;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.st.projectst.gui.LanternaGUI;
import com.st.projectst.model.Map;
import com.st.projectst.model.MapBuilder;
import com.st.projectst.model.Position;
import com.st.projectst.model.Mari;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class GameEngine {
    private static Screen screen;
    private static TextGraphics graphics;
    private Terminal terminal;
    private Mari mari;
    private boolean isMovingLeft;
    private boolean isMovingRight;
    int mariHeight;
    int screenWidth;
    int screenHeight;
    private Map gameMap;
    private String[] imagePaths = {"mari1.png", "mari3.png", "mari4.png"};
    private int currentImageIndex = 0;
    private final MapBuilder mapBuilder = new MapBuilder();
    private LanternaGUI lanternaGUI;


    public GameEngine(int width, int height){
        try {
            this.terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(terminal);
            screen.startScreen();
            graphics = screen.newTextGraphics();

            screenWidth = width;
            screenHeight = height;

            MapBuilder mapBuilder = new MapBuilder();
            String filePath = Objects.requireNonNull(MapBuilder.class.getClassLoader().getResource("map1.txt")).getPath();
            gameMap = mapBuilder.buildMap(filePath, 1);

            lanternaGUI = new LanternaGUI(screen);
            mariHeight = lanternaGUI.loadImage("mari1.png").getHeight();
            mari = new Mari(new Position(screenWidth / 2, screenHeight - mariHeight - 1));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void drawMari(Position position) {
        String currentImagePath = imagePaths[currentImageIndex];
        lanternaGUI.drawImage(position, currentImagePath);
    }

    private void draw() throws IOException {
        screen.clear();
        mari.update();
        lanternaGUI.drawMap(gameMap);
        drawMari(mari.getPosition());
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
                Thread.sleep(20);
            }
        }
        catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        currentImageIndex = (currentImageIndex + 1) % imagePaths.length;
        switch (key.getKeyType()) {
            case ArrowRight:
                mari.moveRight();
                break;
            case ArrowLeft:
                mari.moveLeft();
                break;
            case ArrowUp:
                mari.jump();
                break;
        }
        boolean isLeft = key.getKeyType() == KeyType.ArrowLeft;
        boolean isRight = key.getKeyType() == KeyType.ArrowRight;
        boolean isJump = key.getKeyType() == KeyType.ArrowUp;

        if (isLeft) {
            isMovingLeft = true;
            isMovingRight = false;
        } else if (isRight) {
            isMovingRight = true;
            isMovingLeft = false;
        } else if (isJump) {
            mari.jump();
        } else if (key.getKeyType() == KeyType.ArrowDown) {
            // Handle arrow down or other keys if needed
        } else {
            isMovingLeft = false;
            isMovingRight = false;
        }

        if (isMovingLeft || isMovingRight) {
            if (isMovingLeft) {
                mari.moveLeft();
            } else {
                mari.moveRight();
            }
        }
    }

    public static void main(String[] args) {
        GameEngine gameEngine = new GameEngine(80, 24);
        gameEngine.run();
    }
}