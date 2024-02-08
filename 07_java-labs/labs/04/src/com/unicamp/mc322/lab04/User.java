package com.unicamp.mc322.lab04;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class User {
	private String name;
	private String cpf;
	private Date birthday;
	private Position address;

	public User(String name, String cpf, Date birthday, Position position) {
		this.name = name;
		this.cpf = cpf;
		this.birthday = birthday;
		this.address = position;
	}

	public String getName() {
		return name;
	}

	public String getCPF() {
		return cpf;
	}

	public Date getBirthday() {
		return birthday;
	}

	public Position getAddress() {
		return address;
	}

	public int getAge() {
		Date now = new Date();
		int diffInDays = (int) ((now.getTime() - birthday.getTime()) / (1000 * 60 * 60 * 24));
		return (diffInDays) / 365 + 1900;
	}

	@Override
	public String toString() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

		// BIRTHDAY: sub 1900 year and 1 month = 22801 months
		Calendar cBirthday = Calendar.getInstance();
		cBirthday.setTime(birthday);
		cBirthday.add(Calendar.MONTH, -22801);

		String out = "User: " + cpf + "\n";
		out += "Name: " + name + "\n";
		out += "Birthday: " + format.format(cBirthday.getTime()) + "\n";
		out += "Address: " + address + "\n";

		return out;
	}
}
