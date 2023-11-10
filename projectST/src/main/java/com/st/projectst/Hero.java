package com.st.projectst;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero extends GameObject{
    private static final double JUMP_FORCE = 2.3;
    private static final int GROUND_LEVEL = 15;
    private static final double GRAVITY = 0.2;
    private int groundLevel = 10;
    private double speedX;
    private double speedY;
    private int remainingLives;
    private boolean withKey;
    private boolean isJumping;

    public Hero(Position position) {
        super(position);
        speedX = 1; speedY = 0;
        remainingLives = 3;
        withKey = false;
        isJumping = false;
    }

    public void moveRight() {getPosition().setX((int) (getPosition().getX() + (1 * speedX)));}
    public void moveLeft() {getPosition().setX((int) (getPosition().getX() - (1 * speedX)));}
    /*
    public void accelerate(double accelerationX, double accelerationY) {
        speedX += accelerationX;
        speedY += accelerationY;
    }
    public void jump() {
        isJumping = true;
        accelerate(0, -JUMP_FORCE);
    }
    public void update() {
        double newX = getPosition().getX() + speedX;
        double newY = getPosition().getY() + speedY;
        getPosition().setX((int) newX);
        getPosition().setY(Math.max((int) newY, groundLevel));
    }
    */
    @Override
    public void draw(Screen screen) {
        screen.setCharacter(getPosition().getX(), getPosition().getY(), TextCharacter.fromCharacter('H')[0]);
    }
}
