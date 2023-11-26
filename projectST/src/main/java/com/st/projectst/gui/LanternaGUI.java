package com.st.projectst.gui;

import com.googlecode.lanterna.SGR;
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
import com.googlecode.lanterna.terminal.swing.AWTTerminalFontConfiguration;
import com.st.projectst.model.Position;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class LanternaGUI implements GUI{
    private final Screen screen;
    final Map<Character, String> CHARACTER_MAP = createCharacterMap();

    public LanternaGUI(Screen screen) {
        this.screen = screen;
    }

    public LanternaGUI(int width, int height) throws IOException, FontFormatException, URISyntaxException {
        TerminalSize terminalSize = new TerminalSize(width, height);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        terminalFactory.setInitialTerminalSize(terminalSize);

        AWTTerminalFontConfiguration squareFont = loadSquareFont();
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(squareFont);

        Terminal terminal = terminalFactory.createTerminal();
        this.screen = new TerminalScreen(terminal);
    }


    public void startScreen() throws IOException {
        screen.startScreen();
    }

    private Screen createScreen(Terminal terminal) throws IOException {
        final Screen screen;
        screen = new TerminalScreen(terminal);

        screen.setCursorPosition(null);
        screen.startScreen();
        screen.doResizeIfNecessary();
        return screen;
    }

    private Terminal createTerminal(int width, int height, AWTTerminalFontConfiguration fontConfig) throws IOException {
        TerminalSize terminalSize = new TerminalSize(width, height + 1);
        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                .setInitialTerminalSize(terminalSize);
        terminalFactory.setForceAWTOverSwing(true);
        terminalFactory.setTerminalEmulatorFontConfiguration(fontConfig);
        Terminal terminal = terminalFactory.createTerminal();
        return terminal;
    }

    private AWTTerminalFontConfiguration loadSquareFont() throws URISyntaxException, FontFormatException, IOException {
        URL resource = getClass().getClassLoader().getResource("square.ttf");
        File fontFile = new File(resource.toURI());
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        ge.registerFont(font);

        Font loadedFont = font.deriveFont(Font.PLAIN, 25);
        AWTTerminalFontConfiguration fontConfig = AWTTerminalFontConfiguration.newInstance(loadedFont);
        return fontConfig;
    }

    public ACTION getNextAction() throws IOException {
        KeyStroke keyStroke = screen.pollInput();
        if (keyStroke == null) return ACTION.NONE;

        switch (keyStroke.getKeyType()) {
            case EOF:
                return ACTION.QUIT;
            case Character:
                if (keyStroke.getCharacter() == 'q') {
                    return ACTION.QUIT;
                }
                if (keyStroke.getCharacter() == ' ') {
                    return ACTION.POWER;
                }
                break;
            case ArrowUp:
                return ACTION.UP;
            case ArrowRight:
                return ACTION.RIGHT;
            case ArrowDown:
                return ACTION.DOWN;
            case ArrowLeft:
                return ACTION.LEFT;
            case Enter:
                return ACTION.SELECT;
            default:
                break;
        }

        return ACTION.NONE;
    }


    @Override
    public void drawMari(Position position) {
        drawImage(position, "./src/main/resources/mari1.png");
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
    public void drawMenu() {
        screen.clear();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setForegroundColor(TextColor.ANSI.WHITE);
        graphics.setBackgroundColor(TextColor.ANSI.BLUE);

        graphics.putString(2, 2, "=== Game Menu ===");
        graphics.putString(2, 4, "[1] Map 1");
        graphics.putString(2, 6, "[2] Load Game");
        graphics.putString(2, 8, "[3] Options");
        graphics.putString(2, 10, "[0] Exit");

        try {
            screen.refresh();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void drawText(Position position, String text, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString((int) position.getX(), (int) position.getY(), text);
    }


    private void drawCharacter(int x, int y, char c, String color) {
        TextGraphics tg = screen.newTextGraphics();
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.putString(x, y + 1, getMappedCharacter(c));
    }
    public void drawImage(Position pos, String filename) {
        BufferedImage image = loadImage(filename);
        TextGraphics tg = screen.newTextGraphics();

        Color backgroundColor = new Color(image.getRGB(0, 0)); // Assuming top-left pixel represents the background
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


    public void drawPixel(int x, int y, String color, TextGraphics tg) {
        setTextColor(tg, color);
        tg.putString(x, y, ".");
    }
        
    public void setTextColor(TextGraphics tg, String color) {
        tg.setForegroundColor(TextColor.Factory.fromString(color));
        tg.setBackgroundColor(TextColor.Factory.fromString(color));
    }

    public BufferedImage loadImage(String filename) {
        try {
            BufferedImage originalImage = ImageIO.read(new File(filename));
            int scaledWidth = originalImage.getWidth();
            double scaledHeight = originalImage.getHeight()/1.7 ;

            BufferedImage scaledImage = new BufferedImage(scaledWidth, (int) scaledHeight, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = scaledImage.createGraphics();
            g2d.drawImage((Image) originalImage, 0, 0, scaledWidth, (int) scaledHeight, null);
            g2d.dispose();

            return scaledImage;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void drawMap(Map map) {

    }


    public String getMappedCharacter(char character) {
        return CHARACTER_MAP.getOrDefault(character, String.valueOf('-'));
    }
    public Map<Character, String> createCharacterMap() {
        Map<Character, String> charMap = new HashMap<>();
        charMap.put(' ', " ");
        charMap.put('.', "..");
        charMap.put(':', "::");
        return charMap;
    }

    @Override
    public void clear() {
        screen.clear();
    }

    @Override
    public void refresh() throws IOException {
        screen.refresh();
    }

    @Override
    public void close() throws IOException {
        screen.close();
    }
}
