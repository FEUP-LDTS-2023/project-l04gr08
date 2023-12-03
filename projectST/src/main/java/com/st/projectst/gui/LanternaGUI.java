package com.st.projectst.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.st.projectst.model.*;
import com.st.projectst.model.game.*;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;

public class LanternaGUI implements GUI{
    private Screen screen;

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        AWTTerminalFontConfiguration fontConfig = loadSquareFont();
        Terminal terminal = createTerminal(width, height, fontConfig);
        this.screen = createScreen(terminal);
    }


    public void startScreen() throws IOException { screen.startScreen(); }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();

        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory().setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();

        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws FontFormatException, IOException {
        InputStream fontStream = getClass().getClassLoader().getResourceAsStream("Super Mario Bros. 2.ttf");
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontStream);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 24);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        switch (keyStroke.getKeyType()) {
            case EOF:
                return ACTION.QUIT;

            case ArrowRight:
                return ACTION.RIGHT;
            case ArrowLeft:
                return ACTION.LEFT;
            case ArrowUp:
                return ACTION.UP;
            case ArrowDown:
                return ACTION.DOWN;

            case Escape:
                return ACTION.PAUSE;
            case Enter:
                return ACTION.SELECT;

            default:
                break;
        }

        return ACTION.NONE;
    }


    @Override
    public void drawMari(Position position) {
        drawImage(position, "mari1.png");
    }

    @Override
    public void drawGhostEnemy(Position position) {
        drawCharacter((int) position.getX(), (int) position.getY(), 'G', "#CC0000");
    }

    @Override
    public void drawBatEnemy(Position position) {
        drawCharacter((int) position.getX(), (int) position.getY(), 'B', "#CC0000");
    }

    @Override
    public void drawMenu() throws IOException {
        TextGraphics tg = screen.newTextGraphics();
        setTextColor(tg, "#BA6156");
        tg.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(1024, 512), ' ');
        screen.refresh();
        drawImage(new Position(20, 2), "key.png");
    }


    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.setBackgroundColor(TextColor.Factory.fromString("#BA6156"));
        tg.putString((int) position.getX(), (int) position.getY(), text);
    }


    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y + 1, color);
    }
    public void drawImage(Position pos, String filename) {
        BufferedImage image = loadImage(filename);
        TextGraphics tg = screen.newTextGraphics();

        Color backgroundColor = new Color(image.getRGB(0, 0));
        String backgroundHex = "#" + Integer.toHexString(backgroundColor.getRGB()).substring(2);

        for (int x = 0; x < image.getWidth(); x++) {
            for (int y = 0; y < image.getHeight(); y++) {
                Color pixelColor = new Color(image.getRGB(x, y));
                String colorHex = "#" + Integer.toHexString(pixelColor.getRGB()).substring(2);

                if (!colorHex.equals(backgroundHex)) {
                    drawPixel((int) pos.getX() + x, (int) pos.getY() + y, colorHex, tg);
                }
            }
        }
    }

    @Override
    public void drawWall(Position position) {
        TextGraphics tg = screen.newTextGraphics();
        setTextColor(tg, "#808080");

        tg.putString((int) position.getX(), (int) position.getY(), "W");
    }


    public void drawPixel(int x, int y, String color, TextGraphics tg) {
        setTextColor(tg, color);
        tg.putString(x, y, ".");
    }
        
    public void setTextColor(TextGraphics tg, String color) {
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.setBackgroundColor(TextColor.Factory.fromString(color));
    }

    public void setBackgroundTransparent(TextGraphics tg) {
        tg.setBackgroundColor(TextColor.ANSI.DEFAULT);
    }


    public BufferedImage loadImage(String filename) {
        try (InputStream imageStream = getClass().getResourceAsStream("/" + filename)) {
            if (imageStream != null) {
                BufferedImage originalImage = ImageIO.read(imageStream);

                int targetWidth = originalImage.getWidth();
                int targetHeight = originalImage.getHeight();
                double aspectRatio = (double) targetWidth / targetHeight;

                int newWidth = targetWidth;
                int newHeight = targetHeight;

                if (aspectRatio > 1) {
                    newWidth = (int) (targetHeight * aspectRatio);
                } else {
                    newHeight = (int) (targetWidth / aspectRatio);
                }

                BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(originalImage, 0, 0, newWidth, newHeight, null);
                g.dispose();

                return resizedImage;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void drawMap(Map map) {
        Mari mari = map.getMari();
        Key key = map.getKey();
        for (int y = 0; y < map.getHeight(); y++) {
            for (int x = 0; x < map.getWidth(); x++) {
                Position currentPosition = new Position(x, y);
                if (mari != null && mari.getPosition().equals(currentPosition)) {
                    drawMari(currentPosition);
                }
                else {
                    for (GhostEnemy enemy : map.getGhostEnemies()) {
                        if (enemy.getPosition().equals(currentPosition)) {
                            drawGhostEnemy(enemy.getPosition());
                        }
                    }
                    for (BatEnemy enemy : map.getBatEnemies()) {
                        if (enemy.getPosition().equals(currentPosition)) {
                            drawBatEnemy(enemy.getPosition());
                        }
                    }
                    for (Wall wall : map.getWalls()) {
                        if (wall.getPosition().equals(currentPosition)) {
                            drawWall(currentPosition);
                            break;
                        }
                    }

                    if (key != null && key.getPosition().equals(currentPosition)) {
                        //drawKey(currentPosition);
                    }
                }
            }
        }
    }


    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException { screen.refresh(); }

    @Override
    public void close() throws IOException { screen.close(); }
}
