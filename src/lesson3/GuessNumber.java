package lesson3;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class GuessNumber {

    static final int stepEndGame = 3;
    static int guessNUmber;
    static int currStep = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int userNumber;
        boolean win = false;

        greeting();
        menu(in);
        while (true) {
            if (currStep > stepEndGame) {
                loose();
                repeat(in);
            }
            System.out.printf("Раунд %d\n", currStep);
            userNumber = userTurn(in);
            win = checkNumber(userNumber);
            if (win) {
                repeat(in);
                continue;
            }
            currStep++;
        }
    }

    public static int userTurn(Scanner in) {
        String num;
        String[] numbers = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        do {
            System.out.print("Введите число от 0 до 9: ");
            num = in.next();
        }
        while (Arrays.binarySearch(numbers, num) < 0);
        return Integer.parseInt(num);
    }

    static boolean checkNumber(int num) {
        if (num == guessNUmber) {
            win();
            return true;
        }
        if (num > guessNUmber) {
            System.out.printf("Загаданное число меншье %d\n", num);

        }
        if (num < guessNUmber) {
            System.out.printf("Загаданное число больше %d\n", num);
        }
        return false;
    }

    static void greeting() {
        System.out.println("Игра \"Угадай число\"");
        System.out.println("Цель игры - угадать число от 0 до 9");
    }

    static void menu(Scanner in) {
        String item;
        do {
            System.out.println("\nМЕНЮ:");
            System.out.println("1. Новая игра");
            System.out.println("2. Выйти");
            item = in.next();
        } while (!"1".equals(item)  && !"2".equals(item));

        switch (item) {
            case "1": {
                newGame();
                break;
            }
            case "2": {
                doExit(in);
                break;
            }
        }
    }

    static void repeat(Scanner in){
        System.out.println("Повторить игру еще раз??? y/n");
        String choice = in.next();
        if (choice.equals("y")) {
            newGame();
            return;
        }
        if (choice.equals("n")) {
            System.exit(0);
        }
        newGame();
    }

    static void newGame() {
        Random rand = new Random();
        guessNUmber = rand.nextInt(10);
        currStep = 1;
    }

    private static void doExit(Scanner in) {
        System.out.println("Вы уверены??? y/n");
        String choice = in.next();
        if (choice.equals("y")) {
            System.exit(0);
        }
        menu(in);
    }

    static void win() {
        System.out.println("Победа! Вы угадали число!");
    }

    static void loose() {
        System.out.println("ВЫ не угадали число с 3 попыток. Игра окончена.");
    }
}
