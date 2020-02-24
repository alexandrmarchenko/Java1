package lesson2;

import java.util.Random;

public class Array {
    static void  fillArrayRandom(int[] arr, int bound){
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            arr[i] = rand.nextInt(bound);
        }
    }

    static void  fillArrayRandom(int[][] arr, int bound){
        Random rand = new Random();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                arr[i][j] = rand.nextInt(bound);
            }
        }
    }

    static void printArray(int[] arr){
        for (int el: arr
             ) {
            System.out.print(el + "\t");
        }
    }

    static void printArray(int[][] arr){
        for (int[] row: arr
        ) {
            for (int col: row
                 ) {
                System.out.print(col + "\t");
            }
            System.out.println();
        }
    }
}
