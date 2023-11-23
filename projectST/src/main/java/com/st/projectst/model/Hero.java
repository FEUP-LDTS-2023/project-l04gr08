package com.st.projectst.model;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

public class Hero extends GameObject {
    private static final int screenHeight = 24;
    private double speedX;
    private double speedY;
    private int remainingLives;
    private boolean withKey;
    private boolean isJumping;
    private int jumpCounter;
    private static final String[] playerDraw = new String[]{
            "        C        ",
            "       CWC       ",
            "       CWC       ",
            "      CCWCC      ",
            "      CWCWC      ",
            "     WCWbWCW     ",
            "    WWCWbWCWW    ",
            "   WWWCWbWCWWW   ",
            "  WWWWCWbWCWWWW  ",
            " WWWRWCWbWCWRWWW ",
            "WWWWWWCWbWCWWWWWW",
            "WWCWCWCWbWCWCWCWW",
            " CCCC CWbWC CCCC ",
            "      CWbWC      ",
            "       CCC       "
    };

    public Hero(Position position) {
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
    @Override
    public void draw(TextGraphics graphics) {
        int x = (int) getPosition().getX();
        int y = (int) getPosition().getY();
        double angle = Math.atan2(getPosition().getY(), getPosition().getX()) + Math.PI / 2.0;

        drawShadow(graphics, angle, x, y);

        String[] draw = playerDraw;
        drawPlayer(graphics, draw, angle, x, y);
    }

    private void drawShadow(TextGraphics graphics, double angle, int x, int y) {
        graphics.setBackgroundColor(TextColor.ANSI.WHITE);

        double midX = playerDraw[0].length() / 2.0;
        double midY = playerDraw.length / 2.0;

        Vector point1 = new Vector(9 - midX, 3 - midY).rotatePoint(angle);
        Vector point2 = new Vector(1 - midX, 11 - midY).rotatePoint(angle);
        Vector point3 = new Vector(15 - midX, 11 - midY).rotatePoint(angle);

        graphics.fillTriangle(
                new TerminalPosition((int) (point1.getX() * 2+ x), (int) (point1.getY() * 2 + y)),
                new TerminalPosition((int) (point2.getX() * 2 + x), (int) (point2.getY() * 2 + y)),
                new TerminalPosition((int) (point3.getX() * 2 + x), (int) (point3.getY() * 2 + y)),
                ' '
        );
    }

    private void drawPlayer(TextGraphics graphics, String[] draw, double angle, int playerX, int playerY) {
        int midX = draw[0].length() / 2;
        int midY = draw.length / 2;

        for (int y = 0; y < draw.length; y++) {
            for (int x = 0; x < draw[0].length(); x++) {
                char c = draw[y].charAt(x);
                if (c != ' ') {
                    Vector point = new Vector(x - midX, y - midY);
                    Vector newPoint = point.rotatePoint(angle);

                    setColor(graphics, c);
                    graphics.fillRectangle(
                            new TerminalPosition((int) (playerX + newPoint.getX() * 2),
                                    (int) (playerY + newPoint.getY() * 2)),
                            new TerminalSize(2, 2),
                            ' '
                    );
                }
            }
        }
    }

    private void setColor(TextGraphics graphics, char colorChar) {
        TextColor foregroundColor = TextColor.ANSI.WHITE;
        TextColor backgroundColor = TextColor.ANSI.BLACK;
        switch (colorChar) {
            case 'C':
                foregroundColor = TextColor.ANSI.CYAN;
                break;
            case 'W':
                foregroundColor = TextColor.ANSI.WHITE;
                break;
            default:
                break;
        }
        graphics.setForegroundColor(foregroundColor);
        //graphics.setBackgroundColor(backgroundColor);
    }

}