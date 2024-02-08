package com.unicamp.mc322.lab01.poo;

public class Calculadora {
	
	/* exemplo de como usar o construtor e atributos internos
	 * um possível atributo da calculadora é o número de digitos a ser
	 * mostrados para números decimais */
	private int digits;
	
	public Calculadora() {
		this(2);
	}
	
	public Calculadora(int precision) {
		digits = precision;
	}
	
	public int getPrecision() {
		return digits;
	}
	
	/* Exemplo de como os atributos de um objeto podem influenciar o
	 * seu comportamento. O método arredonda qualquer resultado para ter
	 * no máximo <digits> números decimais depois da vírgula.
	 * OBS: O método é private, só a própria calculadora pode chamá-lo 
	 */
	private double enforcePrecision(double number) {
		return Math.round(number * Math.pow(10,digits)) / Math.pow(10,digits);
	}
	
	public int sum(int a, int b) {
		return a + b;
	}
	
	public int subtract(int a, int b) {
		return a - b;
	}
	
	public int multiply(int a, int b) {
		return a * b;
	}
	
	public double divide(int a, int b) {
		double result = a / (double)b;
		return enforcePrecision(result);
	}
	
	public long factorial(int a) {
		long fact = a;
		for(int k=a-1; k>1; k--) {
			fact = fact * k;
		}
		return fact;
	}
	
	public boolean checkPrime(int a) {
		boolean divisible = false;
		// é suficiente testar os divisores menores de n/2
		// se for divisível por n/2, seria também divisível por 2
		for(int k=2; k<a/2 && !divisible; k++) {
			if(a % k == 0) {
				divisible = true;
			}
		}
		return !divisible;
	}
}
