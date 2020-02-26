package lesson2;

public class Task4 {
    public static void main(String[] args) {
        final int SIZE = 5;
        int[][] arr = new int[SIZE][SIZE];
        fillArray(arr);
        Array.printArray(arr);
    }

    static void fillArray(int[][] arr){
        for (int i = 0; i < arr.length; i++) {
            arr[i][i] = arr[i][arr.length - 1 - i] = 1;
        }
    }
}
