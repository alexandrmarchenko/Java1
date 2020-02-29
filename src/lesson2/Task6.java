package lesson2;

public class Task6 {
    public static void main(String[] args) {
        final int SIZE = 8;
        int[] arr = new int[SIZE];
        Array.fillArrayRandom(arr, 11);

        int balanceIndex = checkBalance(arr);

        Array.printArray(arr);
        System.out.println();
        if (balanceIndex == -1) {
            System.out.println("There is no index where the sums of the left and right sides are equal");
        } else {
            print(arr, balanceIndex);
        }

        arr = new int[]{2, 2, 2, 1, 2, 2, 10, 1};
        System.out.println();
        Array.printArray(arr);
        System.out.println();
        balanceIndex = checkBalance(arr);
        if (balanceIndex == -1) {
            System.out.println("There is no index where the sums of the left and right sides are equal");
        } else {
            print(arr, balanceIndex);
        }

        arr = new int[]{1, 1, 1, 2, 1};
        System.out.println();
        Array.printArray(arr);
        System.out.println();
        balanceIndex = checkBalance(arr);
        if (balanceIndex == -1) {
            System.out.println("There is no index where the sums of the left and right sides are equal");
        } else {
            print(arr, balanceIndex);
        }

    }

    static int checkBalance(int[] arr) {
        if (arr.length > 1) {
            int sumLeft = 0;
            int sumRight = 0;
            int leftIndex = 0;
            int rightIndex = arr.length - 1;
            while (leftIndex <= rightIndex) {
                if (sumLeft <= sumRight) {
                    sumLeft += arr[leftIndex++];
                } else {
                    sumRight += arr[rightIndex--];
                }
            }
            if (sumLeft == sumRight)
                return leftIndex;
        }
        return -1;
    }

    static void print(int[] arr, int balanceIndex) {
        for (int i = 0; i < arr.length; i++) {
            if (i == balanceIndex)
                System.out.print("|| \t");
            System.out.print(arr[i] + "\t");
        }
        System.out.println();
    }
}
