package lesson2;

import java.util.Arrays;
import java.util.Random;

public class Task1 {
    public static void main(String[] args) {
        final int SIZE = 4;
        int[] arr = new int[SIZE];
        Array.fillArrayRandom(arr,2);

        System.out.println("Array");
        Array.printArray(arr);

        modifyArray(arr);

        System.out.println("\nModified Array");
        Array.printArray(arr);
    }

    public static void modifyArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = Math.abs(arr[i] - 1);
        }
    }
}
