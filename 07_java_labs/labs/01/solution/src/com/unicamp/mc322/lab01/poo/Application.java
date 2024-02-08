package com.unicamp.mc322.lab01.poo;

public class Application {

	private Calculadora calc = new Calculadora(3);
	private Terminal terminal = new Terminal();
		
	private void start() {
		terminal.welcomeMessage();
		terminal.printMessage(">> A calculadora está operando com " + calc.getPrecision() + " dígitos decimais");
		
		OperationKind op = terminal.askOperation();
		
		while(op != OperationKind.EXIT) {
			processOperation(op);
			
			if(terminal.askRepeat()) {
				op = terminal.askOperation();
			}else {
				op = OperationKind.EXIT;
			}
		}
		terminal.goodbyeMessage();
	}
	
	private void processOperation(OperationKind op) {
		switch (op) {
		case ADDITION:
			processAddition();
			break;
		case SUBTRACTION:
			processSubtraction();
			break;
		case MULTIPLICATION:
			processMultiplication();
			break;
		case DIVISION:
			processDivision();
			break;
		case FACTORIAL:
			processFactorial();
			break;
		case CHECK_PRIME:
			processCheckPrime();
			break;
		case EXIT:
			break;
		}
		
		terminal.blankLine();
	}
	
	private void processAddition() {
		selectedOperationMessage("SOMA");
		terminal.printMessage("Insira os dois numeros a ser somados.");
		int num[] = terminal.readNumbers(2);
		int resultado = calc.sum(num[0], num[1]);
		terminal.printMessage(num[0] + " + " + num[1] + " = " + resultado);
	}
	
	private void processSubtraction() {
		selectedOperationMessage("SUBTRAÇÃO");
		terminal.printMessage("Insira os dois operandos.");
		int num[] = terminal.readNumbers(2);
		int resultado = calc.subtract(num[0], num[1]);
		terminal.printMessage(num[0] + " - " + num[1] + " = " + resultado);
	}
	
	private void processMultiplication() {
		selectedOperationMessage("MULTIPLICAÇÃO");
		terminal.printMessage("Insira os dois numeros a ser multiplicados.");
		int num[] = terminal.readNumbers(2);
		long resultado = calc.multiply(num[0], num[1]);
		terminal.printMessage(num[0] + " * " + num[1] + " = " + resultado);
	}
	
	private void processDivision() {
		selectedOperationMessage("DIVISÃO");
		terminal.printMessage("Insira os dois operandos.");
		int num[] = terminal.readNumbers(2);
		double resultado = calc.divide(num[0], num[1]); 
		terminal.printMessage(num[0] + " / " + num[1]+ " = " + resultado);
	}
	
	private void processFactorial() {
		selectedOperationMessage("FACTORIAL");
		terminal.printMessage("Insira o número.");
		int a = terminal.readNumber();
		long fact = calc.factorial(a);
		terminal.printMessage(a + "! = " + fact);
	}
	
	private void processCheckPrime() {
		selectedOperationMessage("VERIFICA SE NÚMERO É PRIMO");
		terminal.printMessage("Insira o número.");
		int a = terminal.readNumber();
		boolean prime = calc.checkPrime(a);
		String word = "É";
		if(!prime) {
			word = "NÃO " + word;
		}
		terminal.printMessage("O número " + a + " " + word + " primo");
	}
	
	private void selectedOperationMessage(String opName) {
		terminal.printMessage(">> Foi selecionada a operação " + opName.toUpperCase() + ".");
	}			
			
	public static void main(String args[]) {
		
		Application app = new Application();
		app.start();
	}
}
