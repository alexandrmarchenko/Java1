package lesson2;

import java.util.Arrays;

public class Task3 {
    public static void main(String[] args) {
        int[] arr = {1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1};
        Array.printArray(arr);
        System.out.println();
        modifyArray(arr);
        System.out.println("Modified array");
        Array.printArray(arr);
    }

    public static void modifyArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < 6) {
                arr[i] *= 2;
            }
        }
    }
}
