package com.st.projectst.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Position;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public interface GUI {
    ACTION getNextAction() throws IOException;
    void drawMari(Position position);

    void drawGhostEnemy(Position position);
    void drawBatEnemy(Position position);
    void drawWall(Position position);
    void drawMenu();
    void drawText(Position position, String text, String color);
    void drawImage(Position pos, String filename);
    void drawPixel(int x, int y,String color, TextGraphics tg);
    void setTextColor(TextGraphics tg, String color);
    BufferedImage loadImage(String filename);
    void drawMap(com.st.projectst.model.Map map);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT, POWER}
}
