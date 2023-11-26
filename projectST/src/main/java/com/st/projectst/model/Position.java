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

    @SuppressWarnings("EqualsHashCode")
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (!(other instanceof Position))return false;

        Position position = (Position) other;

        if (Double.compare(position.x, x) != 0) return false;
        return Double.compare(position.y, y) == 0;
    }
}
