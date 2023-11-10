package com.st.projectst;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class GameObject {
    private Position position;
    public GameObject(int x, int y){
        this.position = new Position(x, y);
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    public abstract void draw(TextGraphics graphics);
}
