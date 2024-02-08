public class repeat {
    public static void main(String[] args) {
        int limit = 5;
        int count = 0;

        for (int i = 0; i < limit; i++) {
            System.out.printf("%d \n", i);
        }

        System.out.print("while \n");

        while (count < limit) {
            System.out.printf("%d \n", count);
            count = count + 1;
        }

        if (limit < 6) {
            System.out.print("Limit < 6");
        } else {
            System.out.print("Limit >= 6");
        }

    }
}
