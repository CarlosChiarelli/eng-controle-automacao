package com.unicamp.mc322.lab03;

public class Room {
    private boolean vip;
    private boolean occupied;
    private boolean smokers;
    private boolean airCond;

    public Room(boolean vip, boolean smokers, boolean airCond) {
        this.vip = vip;
        this.occupied = false;
        this.smokers = smokers;
        this.airCond = airCond;
    }

    public boolean getOccupied() {
        return occupied;
    }

    public boolean getSmoker() {
        return smokers;
    }

    public boolean getVip() {
        return vip;
    }

    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    public void printInfo() {
        System.out.printf("VIP: %b\n", vip);
        System.out.printf("Occupied: %b\n", occupied);
        System.out.printf("Smokers: %b\n", smokers);
        System.out.printf("AirCond: %b\n\n", airCond);
    }

}
