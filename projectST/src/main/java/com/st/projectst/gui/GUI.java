package com.st.projectst.gui;

import com.st.projectst.model.Position;

import java.io.IOException;

public interface GUI {
    ACTION getNextAction() throws IOException;
    void drawHero(Position position);

    void drawGhostEnemy(Position position);
    void drawMenu();

    void drawText(Position position, String text, String color);

    void clear();

    void refresh() throws IOException;

    void close() throws IOException;

    enum ACTION {UP, RIGHT, DOWN, LEFT, NONE, QUIT, SELECT}
}
