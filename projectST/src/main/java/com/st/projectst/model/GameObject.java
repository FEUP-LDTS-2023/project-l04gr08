package com.st.projectst.model;

import com.googlecode.lanterna.graphics.TextGraphics;

public abstract class GameObject {
    private Position position;
    public GameObject(Position position){
        this.position = position;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
    public abstract void draw(TextGraphics graphics);
}
