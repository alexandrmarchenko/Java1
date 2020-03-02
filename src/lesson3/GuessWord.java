package lesson3;

import java.util.Random;
import java.util.Scanner;

public class GuessWord {

    static String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot",
            "cherry", "garlic", "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive",
            "pea", "peanut", "pear", "pepper", "pineapple", "pumpkin", "potato"};
    static String guessWord;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        String userWord;
        guessWord = newGame();
        boolean win = false;

        greeting();
        menu(in);
        while (true) {
            userWord = userTurn(in);
            win = checkWord(userWord, guessWord);
            if (win) {
                repeat(in);
                newGame();
                continue;
            } else {
                System.out.println(showWord(userWord, guessWord));
            }
        }
    }

    public static String userTurn(Scanner in) {
        System.out.print("Введите слово: ");
        String word = in.next();
        return word;
    }

    static boolean checkWord(String userWord, String guessWord) {
        if (guessWord.equals(userWord.toLowerCase())) {
            win();
            return true;
        }
        return false;
    }

    static String showWord(String userWord, String guessWord){
        int minLen = userWord.length() >= guessWord.length() ? guessWord.length() : userWord.length();
        StringBuilder sB = new StringBuilder();
        for (int i = 0; i < minLen; i++) {
            if (userWord.charAt(i) == guessWord.charAt(i)) {
                sB.append(userWord.charAt(i));
            } else {
                sB.append('*');
            }
        }
        for (int i = sB.length(); i < 16; i++) {
            sB.append('*');
        }
        return sB.toString();
    }

    static void greeting() {
        System.out.println("Игра \"Угадай слово\"");
        System.out.println("Цель игры - угадать слово");
    }

    static void repeat(Scanner in) {
        System.out.println("Повторить игру еще раз??? y/n");
        String choice = in.next();
        if (choice.equals("y")) {
            return;
        }
        if (choice.equals("n")) {
            System.exit(0);
        }
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

    static String newGame() {
        Random rand = new Random();
        return words[rand.nextInt(words.length)];
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
        System.out.println("Победа! Вы угадали слово!");
    }

}
