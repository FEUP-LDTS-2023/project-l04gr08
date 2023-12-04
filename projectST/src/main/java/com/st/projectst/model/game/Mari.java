package com.st.projectst.model.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.GameObject;

public class Mari extends GameObject {
    private double speedX;
    private int remainingLives;
    private boolean withKey;
    private boolean isJumping;
    private boolean isGrounded;
    private int jumpCounter;
    private int remainingJumps;
    private static final int MAX_JUMPS = 2;

    public Mari(Position position) {
        super(position);
        speedX = 1;
        remainingLives = 3;
        withKey = false;
        isJumping = false;
        isGrounded = false;
        jumpCounter = 0;
        remainingJumps = MAX_JUMPS;
    }

    public void moveRight() {
        getPosition().setX( getPosition().getX() + (1 * speedX));
    }
    public void moveLeft() {
        getPosition().setX( getPosition().getX() - (1 * speedX));
    }
    public void jump() {
        if (!isJumping && isGrounded)
            isJumping = true;
    }

    public void update() {
        if (isJumping) {
            isGrounded = false;
            getPosition().setY(getPosition().getY()-1);
            jumpCounter++;

            if (jumpCounter >= 6) {
                isJumping = false;
                jumpCounter = 0;
            }

        }
        else if (!isGrounded) {
            getPosition().setY(getPosition().getY()+1);
        }
    }

    public void decreaseLives() {
        this.remainingLives--;
    }
    public void setWithKey() { this.withKey = true; }
    public boolean getWithKey() { return withKey; }

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

    public boolean getGrounded() {
        return isGrounded;
    }
    public void setGrounded(boolean grounded) {
        isGrounded = grounded;
    }
}