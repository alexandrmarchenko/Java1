package lesson2;

public class Task7 {
    public static void main(String[] args) {
        final int SIZE = 8;
        int[] arr = new int[SIZE];
        Array.fillArrayRandom(arr, 11);
        Array.printArray(arr);
        System.out.println();

        offset(arr, 2);
        Array.printArray(arr);
    }

    static void offset(int[] arr, int n) {
        int length = arr.length;
        if (Math.abs(n) % length == 0)
            return;
        for (int i = 0; i < Math.abs(n) % length; i++) {
            int changedItemVal;
            int prevChangedItemVal = -1;
            for (int j = 0; j < length; j++) {
                int changedItemIndex = (length + j * (int) Math.signum(n) + (int) Math.signum(n)) % length;
                changedItemVal = arr[changedItemIndex];
                if (j == 0) {
                    arr[changedItemIndex] = arr[j];
                } else {
                    arr[changedItemIndex] = prevChangedItemVal;
                }
                prevChangedItemVal = changedItemVal;
            }
        }
    }
}
