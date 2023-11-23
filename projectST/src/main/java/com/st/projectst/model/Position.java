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

    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null) return false;
        if (getClass() != other.getClass()) return false;

        Position p = (Position) other;
        return x == p.getX() && y == p.getY();
    }
}
