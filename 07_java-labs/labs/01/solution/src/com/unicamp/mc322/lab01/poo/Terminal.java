package com.unicamp.mc322.lab01.poo;

import java.util.Scanner;

public class Terminal {

	private Scanner reader;
	
	public Terminal() {
		reader = new Scanner(System.in);
	}
	
	public void welcomeMessage() {
		printMessage("Welcome to Java Calculator!");
	}

	public void goodbyeMessage() {
		printMessage("Goodbye!");		
	}
	
	public void blankLine() {
		System.out.println();
	}
	
	public void printMessage(String message) {
		System.out.println(message);
	}
	
	public int readNumber() {
		return reader.nextInt();
	}
	
	public int[] readNumbers(int howMany) {
		int[] numbers = new int[howMany];
		for(int k=0; k<numbers.length; k++) {
			numbers[k] = readNumber();
		}
		return numbers;
	}
	
	public OperationKind askOperation() {
		System.out.println("1) Digite 1 para somar\n" + 
				   "2) Digite 2 para subtrair\n" + 
				   "3) Digite 3 para multiplicar\n" + 
				   "4) Digite 4 para dividir\n" + 
				   "5) Digite 5 para calcular fatorial\n" + 
				   "6) Digite 6 para verificar se um número é primo\n" + 
				   "99) Qualquer outro valor para sair do programa");

		int selected = readNumber();
		OperationKind operation;
		
		switch(selected) {
		case 1:
			operation = OperationKind.ADDITION;
			break;
		case 2:
			operation = OperationKind.SUBTRACTION;
			break;
		case 3:
			operation = OperationKind.MULTIPLICATION;
			break;
		case 4:
			operation = OperationKind.DIVISION;
			break;
		case 5:
			operation = OperationKind.FACTORIAL;
			break;
		case 6:
			operation = OperationKind.CHECK_PRIME;
			break;
		default:
			operation = OperationKind.EXIT;
			break;
		}
		
		return operation;
	}


	public boolean askRepeat() {
		System.out.println("Deseja executar outra função?\n" + 
						   "1) Sim\n" + 
						   "99) Não (qualquer outro valor)");
		return (readNumber() == 1);
	}
}
