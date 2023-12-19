package com.st.projectst.gui;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Mari;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;


public class LanternaGUITest {

    private Screen screen;
    private GUI gui;
    private TextGraphics tg;

    @BeforeEach
    void setUp() {
        screen = Mockito.mock(Screen.class);
        tg = Mockito.mock(TextGraphics.class);
        Mockito.when(screen.newTextGraphics()).thenReturn(tg);
        gui = Mockito.mock(GUI.class);
    }

    @Test
    void testDrawImage() {
        Screen mockScreen = mock(Screen.class);
        LanternaGUI lanternaGUI = new LanternaGUI(screen);

        BufferedImage testImage = new BufferedImage(5, 5, BufferedImage.TYPE_INT_ARGB);
        Graphics2D graphics = testImage.createGraphics();
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, 5, 5);
        graphics.setColor(Color.RED);
        graphics.fillRect(1, 1, 3, 3);

        when(mockScreen.newTextGraphics()).thenReturn(mock(TextGraphics.class));
        Position position = new Position(10, 10);

        lanternaGUI.drawImage(position, "gameObjects/mari1.png", 1);
        verify(gui, times(0)).drawPixel(anyInt(), anyInt(), anyString(), any(TextGraphics.class));

    }

    @Test
    void testDrawMari() throws IOException, FontFormatException {
        Screen mockScreen = mock(Screen.class);
        LanternaGUI lanternaGUI = new LanternaGUI(screen);
        Position position = new Position(10, 10);
        lanternaGUI.drawMari(position);
        verify(gui, times(0)).drawImage(position, "gameObjects/mari1.png", 1);

        lanternaGUI.drawMariDoubleJump(position);
        verify(gui, times(0)).drawImage(position, "gameObjects/mari3.png", 1);

        lanternaGUI.drawMariJump(position);
        verify(gui, times(0)).drawImage(position, "gameObjects/mari2.png", 1);
    }

    @Test
    void testDrawEnemies() throws IOException, FontFormatException {
        Screen mockScreen = mock(Screen.class);
        LanternaGUI lanternaGUI = new LanternaGUI(screen);
        Position position = new Position(10, 10);
        lanternaGUI.drawGhostEnemy(position);
        verify(gui, times(0)).drawImage(position, "gameObjects/ghost.png", 1);

        lanternaGUI.drawBatEnemy(position);
        verify(gui, times(0)).drawImage(position, "gameObjects/bat.png", 1);

    }

    @Test
    void testDrawObjects() throws IOException, FontFormatException {
        Screen mockScreen = mock(Screen.class);
        LanternaGUI lanternaGUI = new LanternaGUI(screen);
        Position position = new Position(10, 10);
        lanternaGUI.drawKey(position);
        verify(gui, times(0)).drawImage(position, "gameObjects/miniKey.png", 1);

        lanternaGUI.drawPotion(position);
        verify(gui, times(0)).drawImage(position, "gameObjects/potion.png", 1);

        lanternaGUI.drawDoor(position);
        verify(gui, times(0)).drawImage(position, "gameObjects/door.png", 1);

    }

    @Test
    void testDrawWall() {
        LanternaGUI lanternaGUI = new LanternaGUI(screen);
        Position position = new Position(10, 10);
        lanternaGUI.drawWall(position);
        verify(tg, times(1)).setForegroundColor(TextColor.Factory.fromString("#663B17"));
        verify(tg, times(1)).setBackgroundColor(TextColor.Factory.fromString("#CB762E"));
        verify(tg, times(1)).putString(10, 10, "W");
    }

    @Test
    void testDrawTrap() {
        LanternaGUI lanternaGUI = new LanternaGUI(screen);
        Position position = new Position(10, 10);
        lanternaGUI.drawTrap(position);
        verify(tg, times(1)).setForegroundColor(TextColor.Factory.fromString("#663B17"));
        verify(tg, times(1)).setBackgroundColor(TextColor.Factory.fromString("#CB762E"));
        verify(tg, times(1)).putString(10, 10, "X");
    }



    @Test
    void testClear() throws IOException {
        LanternaGUI lanternaGUI = mock(LanternaGUI.class);
        lanternaGUI.clear();
        verify(lanternaGUI, times(1)).clear();
    }

    @Test
    void testClose() throws IOException {
        LanternaGUI lanternaGUI = mock(LanternaGUI.class);
        lanternaGUI.close();
        verify(lanternaGUI, times(1)).close();
    }

    @Test
    void testRefresh() throws IOException {
        LanternaGUI lanternaGUI = mock(LanternaGUI.class);
        lanternaGUI.refresh();
        verify(lanternaGUI, times(1)).refresh();
    }

    @Test
    void testSetBackgroundColor() {

        LanternaGUI lanternaGUI = new LanternaGUI(screen);
        String color = "#BA6156";

        lanternaGUI.setBackgroundColor(color);

        verify(tg, times(1)).setForegroundColor(new TextColor.RGB(186, 97, 86));
        verify(tg, times(1)).setBackgroundColor(new TextColor.RGB(186, 97, 86));
        verify(tg, times(1)).fillRectangle(any(TerminalPosition.class), any(TerminalSize.class), eq(' '));
    }

    @Test
    void testDrawText() {
        LanternaGUI lanternaGUI = new LanternaGUI(screen);
        String color = "#FF0000";
        Position position = new Position(10, 10);
        String text = "Hello";
        lanternaGUI.drawText(position, text, color);
        verify(tg, times(1)).setForegroundColor(TextColor.Factory.fromString(color));
        verify(tg, times(1)).setBackgroundColor(TextColor.Factory.fromString("#BA6156"));
        verify(tg, times(1)).putString((int) position.getX(), (int) position.getY(), text);
    }

    @Test
    void testDrawCharacter() {
        LanternaGUI lanternaGUI = new LanternaGUI(screen);
        String color = "#FF0000";
        Position position = new Position(10, 10);
        lanternaGUI.drawCharacter(0,0, ' ', color, "#BA6156");
        verify(tg, times(1)).setForegroundColor(TextColor.Factory.fromString(color));
        verify(tg, times(1)).setBackgroundColor(TextColor.Factory.fromString("#BA6156"));
        verify(tg, times(1)).putString(0, 0, " ");
    }

    void testNextAction(){

    }


}

