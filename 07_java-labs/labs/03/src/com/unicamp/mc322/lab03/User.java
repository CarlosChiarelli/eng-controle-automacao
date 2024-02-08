package com.unicamp.mc322.lab03;

public class User {
    private String name;
    private String cpf;
    private String birthDate;
    private String genre;
    private float currentBalance;
    private boolean smoker;

    public User(String name, String cpf, String birthDate, String genre, float currentBalance, boolean smoker) {
        this.name = name;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.genre = genre;
        this.currentBalance = currentBalance;
        this.smoker = smoker;
    }

    public void printInfo() {
        System.out.printf("\n\nName: %s\n", this.name);
        System.out.printf("Cpf: %s\n", this.cpf);
        System.out.printf("Birth date: %s\n", this.birthDate);
        System.out.printf("Genre: %s\n", this.genre);
        System.out.printf("Current balance: %.2f\n", this.currentBalance);
        System.out.printf("Smoker: %b\n\n", this.smoker);
    }

    public float getBalance() {
        return currentBalance;
    }

    public void changeBalance(double d) {
        currentBalance += d;
    }

    public boolean getSmoker() {
        return smoker;
    }

}
