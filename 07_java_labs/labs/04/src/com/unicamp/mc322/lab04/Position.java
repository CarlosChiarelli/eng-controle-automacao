package com.unicamp.mc322.lab04;

public class Position {
    private double x;
    private double y;

    public Position(int i, int j) {
        x = i;
        y = j;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public boolean equals(Position p) {
        return x == p.getX() && y == p.getY();
    }

    public String toString() {
        return "(" + x + "," + y + ")";
    }

    public double getDistance(Position point) {
        double x2 = point.getX();
        double y2 = point.getY();
        return Math.sqrt((x2 - x) * (x2 - x) + (y2 - y) * (y2 - y));
    }
}
