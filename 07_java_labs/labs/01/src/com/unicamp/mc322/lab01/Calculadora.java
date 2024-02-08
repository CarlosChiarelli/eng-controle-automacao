package com.unicamp.mc322.lab01;

import java.util.Scanner;

public class Calculadora {
    public static void main(String[] args) {
        int op = 0;
        float x = 0;
        float y = 0;
        boolean out = false;
        boolean noExecAgain = false;

        // get operator
        Scanner input = new Scanner(System.in);
        System.out.print("Enter the operator: ");
        op = input.nextInt();
        if (op != 1 & op != 2 & op != 3 & op != 4 & op != 5 & op != 6) {
            out = true;
        }

        while (!out) {

            input = new Scanner(System.in);
            System.out.print("Enter number 1: ");
            x = input.nextInt();

            if (op != 5 & op != 6) {
                input = new Scanner(System.in);
                System.out.print("Enter number 2: ");
                y = input.nextInt();
            }

            switch (op) {
                case 1:
                    sum(x, y);
                    break;
                case 2:
                    sub(x, y);
                    break;
                case 3:
                    mul(x, y);
                    break;
                case 4:
                    div(x, y);
                    break;
                case 5:
                    fat(x);
                    break;
                case 6:
                    primo(x);
                    break;
                default:
                    out = true;
                    break;
            }

            // execute again
            op = executeAgain(op);
            noExecAgain = (op == 0) ? false : true;
            if (!noExecAgain | out)
                out = true;
        }

    }

    private static int executeAgain(int op) {
        Scanner input = new Scanner(System.in);
        System.out.print("\nExecute again? (yes type 1 / no type 0): \n");
        int r = input.nextInt();

        if (r == 1) {
            input = new Scanner(System.in);
            System.out.print("Enter the operator: ");
            op = input.nextInt();
            if (op == 1 | op == 2 | op == 3 | op == 4 | op == 5 | op == 6) {
                return op;
            }
        }
        return 0;
    }

    private static void displayResult(float r) {
        System.out.printf("Result: %.2f \n", r);
    }

    private static void displayPrimo(boolean b) {
        System.out.println("Primo: " + (b ? "yes" : "no"));
    }

    private static void sum(float x, float y) {
        float result = x + y;
        displayResult(result);
    }

    private static void sub(float x, float y) {
        float result = x - y;
        displayResult(result);
    }

    private static void mul(float x, float y) {
        float result = x * y;
        displayResult(result);
    }

    private static void div(float x, float y) {
        float result = x / y;
        displayResult(result);
    }

    private static void fat(float fat) {
        int aux = (int) fat;
        while (aux > 1) {
            fat = fat * (aux - 1);
            aux = aux - 1;
        }
        displayResult(fat);
    }

    private static void primo(float primo) {
        boolean b = true;
        for (int j = 2; j < primo; j++) {
            if (primo % j == 0)
                b = false;
        }
        displayPrimo(b);
    }
}
