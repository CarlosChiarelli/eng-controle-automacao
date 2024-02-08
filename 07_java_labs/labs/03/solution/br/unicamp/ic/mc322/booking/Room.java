package br.unicamp.ic.mc322.booking;

public class Room {
	private boolean vip;
	private boolean occupied;
	private boolean smokersAccepted;
	private boolean airConditioning;
	private int cost;
	private int nights;
	private User guest;

	public Room(boolean vip, int cost) {
		this.vip = vip;
		if (vip) {
			this.smokersAccepted = true;
			this.airConditioning = true;
		} else {
			this.smokersAccepted = false;
			this.airConditioning = false;
		}

		this.occupied = false;
		this.cost = cost;
		this.nights = 0;
		this.guest = null;
	}

	public boolean isVIP() {
		return vip;
	}

	public boolean isOccupied() {
		return occupied;
	}

	protected void checkIn(User guest, int nights) {
		this.occupied = true;
		this.guest = guest;
		this.nights = nights;
	}

	protected void checkOut() {
		this.occupied = false;
		this.guest = null;
		this.nights = 0;
	}

	public boolean smokersAccepted() {
		return smokersAccepted;
	}

	public boolean hasAirConditioning() {
		return airConditioning;
	}

	public int getCost() {
		return cost;
	}

	public User getGuest() {
		return guest;
	}

	protected int getNights() {
		return nights;
	}
}
