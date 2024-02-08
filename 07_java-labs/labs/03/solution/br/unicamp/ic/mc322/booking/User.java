package br.unicamp.ic.mc322.booking;

import java.util.Date;

public class User {
	private String name;
	private String cpf;
	private Date birthday;
	private UserGender gender;
	private double balance;
	private boolean smoker;
	
	public User(String name, String cpf, Date birthday, UserGender gender, double balance, boolean smoker) {
		this.name = name;
		this.cpf = cpf;
		this.birthday = birthday;
		this.gender = gender;
		this.balance = balance;
		this.smoker = smoker;
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
	
	public UserGender getGender() {
		return gender;
	}
	
	public double getBalance() {
		return balance;
    }
    
	protected boolean debit(double amount) {
		boolean success = false;
		
		if(balance >= amount) {
			balance -= amount;
			success = true;
		}else {
			System.err.println("Unable to credit " + amount + " to user " + cpf);
		}
		return success;
	}
	
	protected void credit(double amount) {
		balance += amount;
	}
	
	public boolean isSmoker() {
		return smoker;
	}
	
	private String genderToString() {
		String retVal;
		
		switch(gender) {
		case MALE:
			retVal = "Male";
			break;
		case FEMALE:
			retVal = "Female";
			break;
		default:
			retVal = "Unspecified";
		}
		
		return retVal;
	}
	
	@Override
	public String toString() {
		String out = "User: " + cpf + "\n";
		out += "Name: " + name + "\n";
		out += "Birthday: " + birthday + "\n";
		out += "Gender: " + genderToString() + "\n";
		out += "Balance: " + balance + "\n";
		out += "Smoker: " + smoker + "\n";

		return out;
	}
}
