package lesson1;

public class Main {

    public static void main(String[] args) {
        byte a = 100;
        short b = -1235;
        int c = 4;
        long d = 1231541L;
        float e = 1232.2321f;
        double f = 123.412;
        char g = 'g';
        boolean h = true;

        System.out.println(calc(2, 4, 10, 5));
        System.out.println(sum10and20(5,7));
        isPositive(-5);
        isNegative(5);
        printName("Alex");
        isLeap(2020);
    }

    public static float calc (float a, float b, float c, float d) {
        return a * (b + (c/d));
    }

    public static boolean sum10and20 (int a, int b) {
        return a + b >= 10 && a + b <= 20;
    }

    public static void isPositive (int a) {
        System.out.println(a>=0?"Положительное":"Отрицательное");
    }

    public static void isNegative (int a) {
        if (a < 0) {
            System.out.println(true);
        }
    }

    public static void printName (String name) {
        System.out.printf("Привет, %s \n", name);
    }

    public static void isLeap (int year) {
        System.out.println(year%400 == 0 || (year%4 == 0 && year%100 != 0) ? "Високосный" : "Не високосный" );
    }

}
