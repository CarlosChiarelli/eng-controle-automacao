package br.unicamp.ic.mc322.booking;

import java.util.Date;

public class Booking {

	public void createBooking(User user, Hotel hotel, int roomNumber, int nights) {
		if (!hotel.roomAvailable(roomNumber)) {
			System.err.println("Quarto ocupado");
			return;
		}
		if (user.isSmoker() && !hotel.roomSmokersAccepted(roomNumber)) {
			System.err.println("O quarto nao aceita fumantes");
			return;
		}

		boolean debitSuccess = user.debit(hotel.roomCost(roomNumber) * nights);
		if (debitSuccess) {
			hotel.checkIn(roomNumber, user, nights);
			System.out.println("Reserva feita");
		} else {
			System.err.println("Impossível finalizar a reserva");
		}
	}

	public void cancelBooking(User user, Hotel hotel, int roomNumber) {
		if (!hotel.roomAvailable(roomNumber)) {
			System.err.println("O quarto nao esta ocupado");
			return;
		} else {
			if (hotel.roomGuest(roomNumber).getCPF() != user.getCPF()) {
				System.err.println("O quarto esta ocupado por outro usuario");
				return;
			}
		}

		user.credit(hotel.roomCost(roomNumber) * 0.7 * hotel.roomNights(roomNumber));
		hotel.checkOut(roomNumber);
		System.out.println("Cancelamento feito");
	}

	public static void main(String[] args) {

		Booking agency = new Booking();

		Hotel hotel1 = new Hotel("Praia Tropical", "Rua Itajuba, 201 - Florianopolis, SC", "3225-8997", 100, 900);
		Hotel hotel2 = new Hotel("Campos Florestal", "Rua Monteiro, 456 - Goiania, GO", "3654-8974", 50, 2000);

		System.out.println(hotel1);

		User user1 = new User("Roberci Silva", "784245698-21", new Date(12 / 04 / 1996), UserGender.MALE, 1000, true);
		User user2 = new User("Marcela Domingues", "269784061-45", new Date(22 / 07 / 1998), UserGender.FEMALE, 2000,
				false);

		agency.createBooking(user1, hotel1, 2, 1);
		System.out.println(hotel1);
		agency.createBooking(user2, hotel2, 13, 4);

		agency.createBooking(user1, hotel1, 87, 1);
		agency.cancelBooking(user2, hotel1, 22);

		agency.createBooking(user1, hotel2, 99, 1);
		agency.cancelBooking(user1, hotel2, 99);

		agency.createBooking(user2, hotel2, 87, 1);

	}
}
