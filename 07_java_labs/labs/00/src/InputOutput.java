import java.util.Scanner;

public class InputOutput {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Digite um numero: ");
        int num = input.nextInt();
        System.out.println("Numero: " + num);
        System.out.printf("Numero: %d \n", num);

        switch (num) {
            case 1:
                System.out.print("num = 1");
                break;
            case 2:
                System.out.print("num = 2");
                break;
            default:
                System.out.print("num > 2");
                break;
        }
    }
}
