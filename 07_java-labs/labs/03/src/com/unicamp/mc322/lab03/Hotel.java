package com.unicamp.mc322.lab03;

import java.util.ArrayList;

public class Hotel {
    private String name;
    private String address;
    private String telephone;
    private ArrayList<Room> roomList;
    private float priceVip;
    private float priceNoVip;

    public Hotel(String name, String address, String telephone, float priceNoVip, float priceVip) {
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.priceVip = priceVip;
        this.priceNoVip = priceNoVip;
        this.roomList = new ArrayList<Room>(100);
    }

    public boolean addRoom(Room roomAdd) {
        boolean success = false;
        int maxRooms = 100;
        int maxVip = 10;
        int sizeRooms;

        sizeRooms = roomList == null ? 0 : roomList.size();

        // check max rooms
        if (sizeRooms < maxRooms) {
            // check: 10 first rooms are vip, after 90 aren't vip
            if ((roomAdd.getVip() && sizeRooms >= maxVip) || (!roomAdd.getVip() && sizeRooms < maxVip))
                return false;
            roomList.add(roomAdd);
            success = true;
        }
        return success;
    }

    public void printInfo() {
        System.out.printf("\nAvailable rooms: %d\n", this.countAvailableRoom());
        ArrayList<Room> aux = this.getAvailableRooms();
    }

    private int countAvailableRoom() {
        int count = 0;
        for (Room room : roomList)
            if (!room.getOccupied())
                count++;
        return count;
    }

    public ArrayList<Room> getAvailableRooms() {
        ArrayList<Room> availableRoomList = new ArrayList<Room>(100);
        for (int i = 0; i < roomList.size(); i++)
            if (!roomList.get(i).getOccupied()) {
                availableRoomList.add(roomList.get(i));
                System.out.printf("\nAvailable room nº: %d\n", i + 1);
                System.out.printf("Price: %.2f\n", this.getPriceRoom(i + 1));
                roomList.get(i).printInfo();
            }
        return availableRoomList;
    }

    public float getPriceRoom(int numberRoom) {
        if (numberRoom > roomList.size())
            return -1;
        return roomList.get(numberRoom - 1).getVip() ? priceVip : priceNoVip;
    }

    public boolean getAvailableRoom(int numberRoom) {
        if (numberRoom > roomList.size())
            return false;
        return roomList.get(numberRoom - 1).getOccupied();
    }

    public boolean getSmokerRoom(int numberRoom) {
        if (numberRoom > roomList.size())
            return false;
        return roomList.get(numberRoom - 1).getSmoker();
    }

    public Room getRoomByNumber(int nbRoom) {
        return roomList.get(nbRoom - 1);
    }

    public void setOccupiedByNumberRoom(int nbRoom, boolean occupied) {
        Room room = this.getRoomByNumber(nbRoom);
        room.setOccupied(occupied);
        roomList.set(nbRoom - 1, room);
    }
}
