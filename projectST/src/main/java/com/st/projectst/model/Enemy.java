package com.st.projectst.model;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class Enemy extends GameObject {
    public Enemy(Position position) {
        super(position);
    }
    public abstract void move();

    @Override
    public abstract void draw(TextGraphics graphics);
}
