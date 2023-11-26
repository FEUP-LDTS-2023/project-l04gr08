package com.st.projectst.model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Mari extends GameObject {
    private static final int screenHeight = 23;
    private double speedX;
    private double speedY;
    private int remainingLives;
    private boolean withKey;
    private boolean isJumping;

    private int jumpCounter;

    public Mari(Position position) {
        super(position);
        speedX = 1; speedY = 0;
        remainingLives = 3;
        withKey = false;
        isJumping = false;
        jumpCounter = 0;
    }

    public void moveRight() {getPosition().setX( getPosition().getX() + (1 * speedX));}
    public void moveLeft() {getPosition().setX( getPosition().getX() - (1 * speedX));}

    public void update() {
        if (isJumping) {
            getPosition().setY(getPosition().getY()-1);
            jumpCounter++;

            if (jumpCounter >= 6) {
                isJumping = false;
                jumpCounter = 0;
            }
        } else if (getPosition().getY() < screenHeight - 1) {
            getPosition().setY(getPosition().getY()+1);
        }
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
        }
    }

    public boolean getIsJumping() {return isJumping;}
    public int getJumpCounter() {return jumpCounter;}

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.ANSI.CYAN);
        graphics.putString(new TerminalPosition((int) getPosition().getX(), (int) getPosition().getY()), " ");
    }
}