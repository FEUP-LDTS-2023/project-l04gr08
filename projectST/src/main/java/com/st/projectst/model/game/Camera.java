package com.st.projectst.model.game;

import com.st.projectst.model.Position;

public class Camera extends GameObject{
    public Camera(Position position) {
        super(position);
        position.setX(position.getX() + 0);
        setPosition(position);
    }
}

