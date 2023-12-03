package com.st.projectst.gui;

import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Map;

import java.awt.image.BufferedImage;
import java.io.IOException;

public interface GUI {
    enum ACTION {RIGHT, LEFT, UP, DOWN, SELECT, QUIT, PAUSE, NONE}
    ACTION getNextAction() throws IOException;
    void drawMari(Position position);

    void drawGhostEnemy(Position position);
    void drawBatEnemy(Position position);
    void drawWall(Position position);
    void drawKey(Position position);
    void drawMenuElements() throws IOException;
    void drawText(Position position, String text, String color);
    void drawImage(Position pos, String filename);
    void setBackgroundTransparent(TextGraphics tg);
    void drawPixel(int x, int y,String color, TextGraphics tg);
    void setTextColor(TextGraphics tg, String color);
    BufferedImage loadImage(String filename);
    void drawMap(Map map);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

}
