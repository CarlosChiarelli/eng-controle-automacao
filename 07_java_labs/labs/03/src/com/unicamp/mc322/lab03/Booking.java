package com.unicamp.mc322.lab03;

import java.util.ArrayList;

public class Booking {
    private ArrayList<User> userList;
    private ArrayList<Hotel> hotelList;
    private ArrayList<Integer> numberRoomList;
    private ArrayList<Integer> daysList;

    public Booking() {
        userList = new ArrayList<User>();
        hotelList = new ArrayList<Hotel>();
        numberRoomList = new ArrayList<Integer>();
        daysList = new ArrayList<Integer>();
    }

    public static void main(String[] args) {
        // test User
        User user1 = new User("Roberci Silva", "784245698-21", "12/04/1996", "Masculino", 1000, true);
        User user2 = new User("Marcela Domingues", "269784061-45", "22/07/1998", "Feminino", 2000, false);
        user1.printInfo();
        user2.printInfo();
        System.out.printf("Current balance User1: %.2f\n\n", user1.getBalance());
        user1.changeBalance(50);
        System.out.printf("Current balance User1: %.2f\n\n", user1.getBalance());
        user1.changeBalance(-200);
        System.out.printf("Current balance User1: %.2f\n\n", user1.getBalance());

        // test Room
        Room room1 = new Room(false, false, false);
        Room room11 = new Room(false, false, false);
        Room room12 = new Room(false, false, false);
        Room room13 = new Room(false, false, false);
        Room room14 = new Room(false, false, false);
        Room room15 = new Room(false, false, false);
        Room room16 = new Room(false, false, false);
        Room room17 = new Room(false, false, false);
        Room room18 = new Room(false, false, false);
        Room room19 = new Room(false, false, false);
        Room room110 = new Room(false, false, false);

        Room room2 = new Room(true, true, true);
        Room room21 = new Room(true, true, true);
        Room room22 = new Room(true, true, true);
        Room room23 = new Room(true, true, true);
        Room room24 = new Room(true, true, true);
        Room room25 = new Room(true, true, true);
        Room room26 = new Room(true, true, true);
        Room room27 = new Room(true, true, true);
        Room room28 = new Room(true, true, true);
        Room room29 = new Room(true, true, true);
        Room room210 = new Room(true, true, true);

        Room room3 = new Room(false, false, false);
        System.out.printf("Occupied Room1: %b\n\n", room1.getOccupied());
        room1.setOccupied(true);
        System.out.printf("Occupied Room1: %b\n\n", room1.getOccupied());

        // test Hotel
        Hotel hotel1 = new Hotel("Praia Tropical", "Rua Tajubá, 201 - Florianópolis, SC", "3225-8997", 100, 900);
        Hotel hotel2 = new Hotel("Campo Florestal", " Rua Monteiro, 456 - Goiânia, GO", "3654-8974", 50, 2000);
        System.out.printf("Added: %b\n", hotel1.addRoom(room1));
        System.out.printf("Added: %b\n", hotel1.addRoom(room2));
        hotel1.printInfo();
        System.out.printf("Price Room nº 1 (vip): %.2f\n", hotel1.getPriceRoom(1));

        // for (int i = 0; i < 12; i++)
        // System.out.printf("Added: %b\n", hotel1.addRoom(room2));
        System.out.print("\n\n");
        System.out.printf("Added: %b\n", hotel1.addRoom(room2));
        System.out.printf("Added: %b\n", hotel1.addRoom(room21));
        System.out.printf("Added: %b\n", hotel1.addRoom(room22));
        System.out.printf("Added: %b\n", hotel1.addRoom(room23));
        System.out.printf("Added: %b\n", hotel1.addRoom(room24));
        System.out.printf("Added: %b\n", hotel1.addRoom(room25));
        System.out.printf("Added: %b\n", hotel1.addRoom(room26));
        System.out.printf("Added: %b\n", hotel1.addRoom(room27));
        System.out.printf("Added: %b\n", hotel1.addRoom(room28));
        System.out.printf("Added: %b\n", hotel1.addRoom(room29));
        System.out.printf("Added: %b\n", hotel1.addRoom(room210));

        // for (int i = 0; i < 11; i++)
        // System.out.printf("Added: %b\n", hotel1.addRoom(room1));
        System.out.print("\n\n");
        System.out.printf("Added: %b\n", hotel1.addRoom(room1));
        System.out.printf("Added: %b\n", hotel1.addRoom(room11));
        System.out.printf("Added: %b\n", hotel1.addRoom(room12));
        System.out.printf("Added: %b\n", hotel1.addRoom(room13));
        System.out.printf("Added: %b\n", hotel1.addRoom(room14));
        System.out.printf("Added: %b\n", hotel1.addRoom(room15));
        System.out.printf("Added: %b\n", hotel1.addRoom(room16));
        System.out.printf("Added: %b\n", hotel1.addRoom(room17));
        System.out.printf("Added: %b\n", hotel1.addRoom(room18));
        System.out.printf("Added: %b\n", hotel1.addRoom(room19));
        System.out.printf("Added: %b\n", hotel1.addRoom(room110));

        hotel1.addRoom(room3);

        System.out.printf("Price Room nº 10 (vip): %.2f\n", hotel1.getPriceRoom(10));
        System.out.printf("Price Room nº 11 (vip): %.2f\n", hotel1.getPriceRoom(11));
        System.out.printf("Price Room nº 12 (vip): %.2f\n", hotel1.getPriceRoom(12));

        hotel1.printInfo();
        System.out.printf("Available: %d\n", hotel1.getAvailableRooms().size());

        // test booking
        Booking booking = new Booking();
        boolean aux = booking.createHotelReserv(user2, hotel1, 3, 14);
        System.out.printf("Created: %b\n", aux);
        user2.printInfo();
        hotel1.printInfo();

        aux = booking.createHotelReserv(user1, hotel1, 22, 30);
        System.out.printf("Created: %b\n", aux);
        user1.printInfo();
        hotel1.printInfo();

        System.out.print("\nCancel: \n");
        user2.printInfo();
        System.out.printf("Available: %d\n", hotel1.getAvailableRooms().size());
        aux = booking.cancelHotelReserv(user2, hotel1, 3);
        System.out.printf("Canceled: %b\n", aux);
        System.out.printf("Available: %d\n", hotel1.getAvailableRooms().size());
        user2.printInfo();
    }

    public boolean createHotelReserv(User user, Hotel hotel, int nbRoom, int days) {
        boolean success = false;
        boolean checkAvailableRoom = this.checkAvailableRoom(hotel, nbRoom);
        boolean checkBalanceUser = this.checkBalanceUser(hotel, nbRoom, user);
        boolean checkSmoker = this.checkSmoker(hotel, nbRoom, user);
        if (checkAvailableRoom && checkBalanceUser && checkSmoker) {
            hotel.setOccupiedByNumberRoom(nbRoom, true);
            user.changeBalance(-1 * hotel.getPriceRoom(nbRoom));
            userList.add(user);
            hotelList.add(hotel);
            numberRoomList.add(nbRoom);
            daysList.add(days);
            success = true;
        }
        return success;
    }

    private boolean checkAvailableRoom(Hotel hotel, int nbRoom) {
        return !hotel.getAvailableRoom(nbRoom);
    }

    private boolean checkBalanceUser(Hotel hotel, int nbRoom, User user) {
        if (hotel.getPriceRoom(nbRoom) > user.getBalance())
            return false;
        return true;
    }

    private boolean checkSmoker(Hotel hotel, int nbRoom, User user) {
        if (user.getSmoker() && !hotel.getSmokerRoom(nbRoom))
            return false;
        return true;
    }

    public boolean cancelHotelReserv(User user, Hotel hotel, int nbRoom) {
        boolean success = false;
        int idxReserv = this.checkReservationExist(user, hotel, nbRoom);
        if (idxReserv != -1) {
            float price = hotelList.get(idxReserv).getPriceRoom(nbRoom);
            userList.get(idxReserv).changeBalance(0.7 * price);
            hotelList.get(idxReserv).setOccupiedByNumberRoom(nbRoom, false);
            userList.remove(idxReserv);
            hotelList.remove(idxReserv);
            numberRoomList.remove(idxReserv);
            daysList.remove(idxReserv);
            success = true;
        }
        return success;
    }

    private int checkReservationExist(User user, Hotel hotel, int nbRoom) {
        int success = -1;
        for (int i = 0; i < userList.size(); i++)
            if (userList.get(i) == user && hotelList.get(i) == hotel && numberRoomList.get(i) == nbRoom)
                success = i;
        return success;
    }
}
