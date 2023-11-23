package com.st.projectst.model;

public class Vector extends Position{
    private double x;
    private double y;

    public Vector(double x, double y) {
        super( x, y);
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Vector rotatePoint(double angle) {
        double cosTheta = Math.cos(angle);
        double sinTheta = Math.sin(angle);

        double newX = x * cosTheta - y * sinTheta;
        double newY = x * sinTheta + y * cosTheta;

        return new Vector(newX, newY);
    }
}
