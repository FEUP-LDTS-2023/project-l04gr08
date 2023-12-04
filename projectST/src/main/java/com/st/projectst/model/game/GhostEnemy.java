package com.st.projectst.model.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.Enemy;

public class GhostEnemy extends Enemy {
    public GhostEnemy(Position position) {
        super(position);
    }
    @Override
    public void move(Position position) {
        setPosition(position);
    }

}
