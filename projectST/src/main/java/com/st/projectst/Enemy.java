package com.st.projectst;

import com.googlecode.lanterna.screen.Screen;

public abstract class Enemy extends GameObject{
    public Enemy(Position position) {
        super(position);
    }
    public abstract void move();

    @Override
    public abstract void draw(Screen screen);
}
