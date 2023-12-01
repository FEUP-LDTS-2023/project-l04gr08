package com.st.projectst.model;

public class Position {
    private double x;
    private double y;
    public Position(double x, double y){
        this.x = x;
        this.y = y;
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
    public Position getLeft() {
        return new Position(x - 1, y);
    }

    public Position getRight() {
        return new Position(x + 1, y);
    }

    public Position getUp() {
        return new Position(x, y - 1);
    }

    public Position getDown() {
        return new Position(x, y + 1);
    }

    @SuppressWarnings("EqualsHashCode")
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Position))return false;

        Position position = (Position) other;

        if (Double.compare(position.x, x) != 0) return false;
        return Double.compare(position.y, y) == 0;
    }

    public Position getRandomNeighbour() {
        int n = (int) (Math.random() * 4);
        switch (n) {
            case 0:
                return getUp();
            case 1:
                return getRight();
            case 2:
                return getDown();
            default:
                return getLeft();
        }
    }
}
