package com.unicamp.mc322.lab01;

public class Algorithm {

    public static void main(String[] args) {

        int quantity = 10;
        int[] vector = new int[quantity];

        for (int i = 0; i < vector.length - 1; i++) {
            vector[i] = (int) (Math.random() * 100);
        }

        sort(vector);

        for (int i = 1; i < vector.length; i++) {
            System.out.println(vector[i]);
        }

    }

    private static void sort(int[] vector) {

        boolean switched = true;
        int aux;
        while (switched) {
            switched = false;
            for (int i = 0; i < vector.length - 1; i++) {
                if (vector[i] > vector[i + 1]) {
                    aux = vector[i];
                    vector[i] = vector[i + 1];
                    vector[i + 1] = aux;
                    switched = true;
                }
            }
        }
    }

}
