package com.st.projectst.model.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.GameObject;

public class Mari extends GameObject {
    private static final int screenHeight = 17;
    private double speedX;
    private double speedY;
    private int remainingLives;
    private boolean withKey;
    private boolean isJumping;
    private int jumpCounter;
    private int remainingJumps;
    private static final int MAX_JUMPS = 2;

    public Mari(Position position) {
        super(position);
        speedX = 1; speedY = 0;
        remainingLives = 3;
        withKey = false;
        isJumping = false;
        jumpCounter = 0;
        remainingJumps = MAX_JUMPS;
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

    public void decreaseLives() {
        this.remainingLives--;
    }

    public int getRemainingLives() {
        return remainingLives;
    }
    public void decreaseJumps() {
        remainingJumps--;
    }

    public int getRemainingJumps() {
        return remainingJumps;
    }

    public boolean getIsJumping() {return isJumping;}
    public int getJumpCounter() {return jumpCounter;}

    @Override
    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.ANSI.CYAN);
        graphics.putString(new TerminalPosition((int) getPosition().getX(), (int) getPosition().getY()), " ");
    }
}