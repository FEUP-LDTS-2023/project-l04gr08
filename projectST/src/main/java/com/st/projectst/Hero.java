package com.st.projectst;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;

public class Hero {
    private Position heroPosition;
    private int speedX;
    private int speedY;
    private int gravity = 1;
    private int remainingLives;
    private boolean withKey;

    public Hero(Position position) {
        heroPosition = position;
        speedX = 1; speedY = 1;
        remainingLives = 3;
        withKey = false;
    }

    public void moveRight() {heroPosition.setX(heroPosition.getX() + (1 * speedX));}
    public void moveLeft() {heroPosition.setX(heroPosition.getX() - (1 * speedY));}
    public void moveUp() { //Jumping
    }

    public void draw(Screen screen) {
        screen.setCharacter(heroPosition.getX(), heroPosition.getY(), TextCharacter.fromCharacter('H')[0]);
    }
}
