package lesson2;

import javax.swing.*;
import java.util.Arrays;

public class Task5 {
    public static void main(String[] args) {
        final int SIZE = 8;
        int[] arr = new int[SIZE];
        Array.fillArrayRandom(arr, 100);
        Array.printArray(arr);
        System.out.println();

        if (SIZE > 0) {
            int max = findMax(arr);
            System.out.printf("Max element %d \n", max);

            int min = findMin(arr);
            System.out.printf("Min element %d \n", min);
        } else {
            System.out.println("Length must be > 0");
        }
    }

    static int findMax(int[] arr) {
        if (arr.length > 1) {
            Arrays.sort(arr);
        }
        return arr[0];
    }

    static int findMin(int[] arr) {
        if (arr.length > 1) {
            Arrays.sort(arr);
        }
        return arr[arr.length - 1];
    }
}
