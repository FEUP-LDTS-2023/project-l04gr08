package com.st.projectst;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.TextCharacter;

public class GhostEnemy extends Enemy{
    public GhostEnemy(Position position) {
        super(position);
    }
    @Override
    public void move() {
    }
    @Override
    public void draw(Screen screen) {
        screen.setCharacter(getPosition().getX(), getPosition().getY(), TextCharacter.fromCharacter('G')[0]);
    }
}
