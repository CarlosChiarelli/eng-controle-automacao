package br.unicamp.ic.mc322.booking;

public class Hotel {
	private String name;
	private String address;
	private String phone;
	private Room[] rooms;
	
	public Hotel(String name, String address, String phone, int normalValue, int vipValue) {
		this.name = name;
		this.address = address;
		this.phone = phone;
		rooms = new Room[100];
		for(int i = 0; i < 10; i++) {
			rooms[i] = new Room(true, vipValue);
		}
		for(int i = 10; i < 100; i++) {
			rooms[i] = new Room(false, normalValue);
		}
	}
	
	public boolean roomAvailable(int numero) {
		return rooms[numero].isOccupied();
	}
	
	public User roomGuest(int numero) {
		return rooms[numero].getGuest();
	}
	
	public int roomCost(int numero) {
		return rooms[numero].getCost();
	}
	
	public int roomNights(int numero) {
		if(roomAvailable(numero)) {
			return 0;
		}else {
			return rooms[numero].getNights();
		}
	}
	
	public boolean roomSmokersAccepted(int numero) {
		return rooms[numero].smokersAccepted();
	}
	
	public void checkIn(int numero, User user, int nights) {
		rooms[numero].checkIn(user, nights);
	}
	
	public void checkOut(int numero) {
		rooms[numero].checkOut();
	}
	
	public String getName() {
		return name;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		int roomsAvailable = 0;
		String out = "Hotel: " + name + "\n";
		out += "Address: " + address + "\n";
		out += "Phone: " + phone + "\n";
		for(int i = 0; i < 100; i++) {
			out += "Quarto numero: " + i + "\n";
			out += "Diária: " + rooms[i].getCost() + "\n";
			out += "Fumante: " + rooms[i].smokersAccepted() + "\n";	
			out += "Ar Condicionado: " + rooms[i].hasAirConditioning() + "\n";	
			out += "VIP: " + rooms[i].isVIP() + "\n";	
			out += "Ocupado: " + rooms[i].isOccupied() + "\n";	
			if(rooms[i].isOccupied() == false)
                roomsAvailable++;
		}
		out += "rooms available: " + roomsAvailable + "\n";	
		
		return out;
	}
}
