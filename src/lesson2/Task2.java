package lesson2;

import java.util.Arrays;

public class Task2 {
    public static void main(String[] args) {
        final int SIZE = 8;
        int[] arr = new int[SIZE];
        fillArray(arr);
        Array.printArray(arr);
    }

    public static void fillArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            arr[i] = i * 3;
        }
    }
}
