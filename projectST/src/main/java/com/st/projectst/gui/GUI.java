package com.st.projectst.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Map;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public interface GUI {
    enum ACTION {RIGHT, LEFT, UP, DOWN, SELECT, QUIT, PAUSE, NONE}
    ACTION getNextAction() throws IOException;
    void drawMari(Position position) throws IOException, FontFormatException;

    void drawGhostEnemy(Position position);
    void drawBatEnemy(Position position);
    void drawWall(Position position);
    void drawKey(Position position);
    void drawDoor(Position position);
    void drawMenuElements() throws IOException, FontFormatException;
    void drawText(Position position, String text, String color);
    void drawImage(Position pos, String filename, double value) throws IOException, FontFormatException;
    void setBackgroundTransparent(TextGraphics tg);
    void drawPixel(int x, int y,String color, TextGraphics tg);
    void setTextColor(TextGraphics tg, String color);
    BufferedImage loadImage(String filename, double value);
    void drawMap(Map map) throws IOException, FontFormatException;

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

}
