package com.st.projectst.model.game;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.st.projectst.model.game.EnemyObserver;
import com.st.projectst.model.Position;
import com.st.projectst.model.game.GameObject;

import java.util.List;

public class Mari extends GameObject {
    private int remainingLives;
    private boolean withKey;
    private boolean withPotion;
    private boolean isJumping;
    private boolean isGrounded;
    private boolean jumpRight;
    private int jumpCounter;
    private int remainingJumps;
    private static final int MAX_JUMPS = 2;

    public Mari(Position position) {
        super(position);
        remainingLives = 3;
        withKey = false;
        isJumping = false;
        isGrounded = false;
        jumpRight = true;
        jumpCounter = 0;
        remainingJumps = MAX_JUMPS;
        withPotion = false;
    }

    public Position moveRight() {
        jumpRight = true;
        Position newPosition = new Position(getPosition());
        newPosition.setX( getPosition().getX() + 1);
        return newPosition;
    }
    public Position moveLeft() {
        jumpRight = false;
        Position newPosition = new Position(getPosition());
        newPosition.setX( getPosition().getX() - 1);
        return newPosition;
    }
    public void jump() {
        if (!isJumping && isGrounded)
            isJumping = true;
    }

    public Position update() {
        Position newPosition = new Position(getPosition());

        if (isJumping) {
            isGrounded = false;
            jumpCounter++;

            newPosition.setY(getPosition().getY()-1);
            if (jumpRight) newPosition.setX(getPosition().getX()+1);
            else newPosition.setX(getPosition().getX()-1);

            if (jumpCounter >= 8) {
                isJumping = false;
                jumpCounter = 0;
            }
            return newPosition;

        }
        else if (!isGrounded) {
            newPosition.setY(getPosition().getY()+1);
            return newPosition;
        }

        return newPosition;
    }

    public Position doubleJump(){
        Position newPosition = new Position(getPosition());

        if (isJumping) {
            isGrounded = false;
            jumpCounter++;

            newPosition.setY(getPosition().getY()-1);
            if (jumpRight) newPosition.setX(getPosition().getX()+1);
            else newPosition.setX(getPosition().getX()-1);

            if (jumpCounter == 8) {
                newPosition.setY(getPosition().getY()+1);
                if (jumpRight) newPosition.setX(getPosition().getX()+1);
                else newPosition.setX(getPosition().getX()-1);
            }

            if (jumpCounter >= 16){
                isJumping = false;
                jumpCounter = 0;
            }
        }
        else if (!isGrounded) {
            newPosition.setY(getPosition().getY()+1);
            return newPosition;
        }
        return newPosition;
    }

    public void decreaseLives() {
        this.remainingLives--;
    }
    public void setWithKey(boolean key) { this.withKey = key; }
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

    public int getJumpCounter() {return jumpCounter;}

    public boolean getGrounded() {
        return isGrounded;
    }

    public void setWithPotion(boolean withPotion) {
        this.withPotion = withPotion;
    }

    public boolean getIsWithPotion() {return withPotion;}

    public boolean getIsJumping() {return isJumping;}

    public void setGrounded(boolean grounded) {
        isGrounded = grounded;
    }

    public void setRemainingJumps(int remainingJumps) {
        this.remainingJumps = remainingJumps;
    }

    public void resetJumps(){
        withPotion = false;
        remainingJumps = MAX_JUMPS;
    }
}