package lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

    public static int SIZE;
    public static int DOTS_TO_WIN;
    public static final char DOT_EMPTY = '.';
    public static final String EMPTY = "  ";
    public static final char DOT_USER = 'X';
    public static final char DOT_AI = 'O';

    public static Scanner scanner = new Scanner(System.in);
    public static Random rand = new Random();

    public static char[][] field;
    ;

    public static void main(String[] args) {

        initGame();

        printField();

        gameEngine();
    }

    private static void gameEngine() {
        do {
            userTurn();
            printField();
            if (isEnd(DOT_USER, "Вы победили")) {
                System.exit(0);
            }

            aiTurn();
            printField();
            if (isEnd(DOT_AI, "Компьютер победил")) {
                System.exit(0);
            }

        } while (true);
    }

    private static void aiTurn() {
        if (!checkAITurn()) {
            int colNumber, rowNumber;
            do {
                rowNumber = rand.nextInt(SIZE) + 1;
                colNumber = rand.nextInt(SIZE) + 1;
            } while (!isCellValid(rowNumber, colNumber));

            field[rowNumber - 1][colNumber - 1] = DOT_AI;
        }
    }

    private static boolean checkAITurn() {
        //Если нельзя выиграть заполнив 1 ячейку, то проверям "уменьшаем" количество ячеек для победы и проверям опять
        //Таким образом компьютер будет строить линии и блокировать возможные линии игрока
        for (int i = DOTS_TO_WIN; i > 1; i--) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if (field[j][k] == DOT_EMPTY) {
                        //Проверяем выиграет ли ИИ, если заполнит ячейку
                        field[j][k] = DOT_AI;
                        if (checkWin(DOT_AI, DOTS_TO_WIN - (DOTS_TO_WIN - i))) {
                            return true;
                        }
                        field[j][k] = DOT_EMPTY;

                        //Проверям выиграет ли человек, если заполнит ячейку
                        //Проверяем только до DOTS_TO_WIN-1, раньше блокировать нет смысла
                        //т.е. если DOTS_TO_WIN=4, то будем блокировать строки где заполнены 2 ячейки, например *ХХ**
                        //но также уже будут блокироваться следующие ХХ**
                        if ((SIZE == DOTS_TO_WIN && SIZE == i) || (DOTS_TO_WIN < SIZE && DOTS_TO_WIN - i < 2)) {
                            field[j][k] = DOT_USER;
                            if (checkWin(DOT_USER, DOTS_TO_WIN - (DOTS_TO_WIN - i))) {
                                field[j][k] = DOT_AI;
                                return true;
                            }
                        }
                        field[j][k] = DOT_EMPTY;
                    }
                }
            }
        }
        return false;
    }

    private static boolean isEnd(char symbol, String message) {
        if (checkWin(symbol, DOTS_TO_WIN)) {
            System.out.println(message);
            return true;
        }

        if (isMapFull()) {
            System.out.println("Ничья!");
            return true;
        }

        return false;
    }

    private static boolean checkWin(char symbol, int dotsToWin) {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (field[i][j] == symbol) {
                    if (SIZE - j >= DOTS_TO_WIN) {
                        if (checkHorizontal(dotsToWin, symbol, i, j)) {
                            return true;
                        }
                    }
                    if (SIZE - i >= DOTS_TO_WIN) {
                        if (checkVertical(dotsToWin, symbol, i, j)) {
                            return true;
                        }
                    }
                    if (SIZE - j >= DOTS_TO_WIN && SIZE - i >= DOTS_TO_WIN) {
                        if (checkDiagonal(dotsToWin, symbol, i, j, 1)) {
                            return true;
                        }
                    }
                    if (j >= DOTS_TO_WIN - 1 && SIZE - i >= DOTS_TO_WIN) {
                        if (checkDiagonal(dotsToWin, symbol, i, j, -1)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private static boolean checkLine(int dotsToWin, char symbol, int rowNumber, int colNumber, int colMultiplier, int rowMultiplier) {
        //Пустая ячейка не повод для выхода
        //Допустим  DOTS_TO_WIN = 4
        //Проверяем для DotsToWin=3
        //Т.о. при текущей строке **ХХ*, проверится строка Х*ХХ*, которая тоже будет выиграшная в перспективе, т.к. можно ее можно будет завершить ХХХ*
        int curDots = 1;
        for (int i = 1; i < DOTS_TO_WIN; i++) {
            int row = rowNumber + i * rowMultiplier;
            int col = colNumber + i * colMultiplier;
            if (field[row][col] == DOT_EMPTY) {
                continue;
            } else if (field[row][col] == symbol) {
                curDots++;
            } else {
                return false;
            }

        }
        return curDots == dotsToWin;
    }

    private static boolean checkHorizontal(int dots_to_win, char symbol, int row, int col) {
        return checkLine(dots_to_win, symbol, row, col, 1, 0);
    }

    private static boolean checkVertical(int dots_to_win, char symbol, int row, int col) {
        return checkLine(dots_to_win, symbol, row, col, 0, 1);
    }

    private static boolean checkDiagonal(int dots_to_win, char symbol, int row, int col, int direction) {
        return checkLine(dots_to_win, symbol, row, col, direction, 1);
    }

    private static boolean isMapFull() {
        for (char[] row : field) {
            for (char col : row) {
                if (col == DOT_EMPTY)
                    return false;
            }
        }
        return true;
    }

    private static void userTurn() {
        int colNUmber = -1, rowNUmber = -1;
        do {
            rowNUmber = setInteger("Строка:");
            colNUmber = setInteger("Столбец:");
        }
        while (!isCellValid(rowNUmber, colNUmber));

        field[rowNUmber - 1][colNUmber - 1] = DOT_USER;
    }

    private static boolean isCellValid(int rowNumber, int colNumber) {
        boolean result = true;
        if (rowNumber < 1 || rowNumber > SIZE) {
            System.out.printf("Строка должна быть в диапазоне от 1 до %d\n", SIZE);
            result = false;
        }
        if (colNumber < 1 || colNumber > SIZE) {
            System.out.printf("Столбец должен быть в диапазоне от 1 до %d\n", SIZE);
            result = false;
        }

        if (result) {
            if (field[rowNumber - 1][colNumber - 1] != DOT_EMPTY) {
                System.out.println("Вы выбрали занятую ячейку");
                result = false;
            }
        }

        return result;
    }

    private static void printField() {
        System.out.print(EMPTY + "\t");
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + "\t");
        }
        System.out.println();
        for (int i = 0; i < SIZE; i++) {
            System.out.print(i + 1 + "\t");
            for (int j = 0; j < SIZE; j++) {
                System.out.print(field[i][j] + "\t");
            }
            System.out.println();
        }
        System.out.println();
    }

    private static void initField() {
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }

    private static void initGame() {
        setOptions();
        initField();
    }

    private static void setOptions() {
        do {
            SIZE = setInteger("Введите размер поля:");
        }
        while (!isValidSize(SIZE));
        do {
            DOTS_TO_WIN = setInteger("Количество точек для победы:");
        } while (!isValidDotsToWin(DOTS_TO_WIN));
    }

    private static boolean isValidSize(int size) {
        if (size < 3) {
            System.out.println("Размер поля должен быть больше 3");
            return false;
        }
        return true;
    }

    private static boolean isValidDotsToWin(int dotsToWin) {
        if (dotsToWin > SIZE || dotsToWin < 2) {
            System.out.printf("Количестов ячееек для победы должно быть от 3 до %d\n", SIZE);
            return false;
        }
        return true;
    }

    private static int setInteger(String message) {
        String num;
        do {
            System.out.print(message);
            num = scanner.nextLine();
        } while (!isInteger(num));
        return Integer.parseInt(num);
    }

    private static boolean isInteger(String num) {
        try {
            Integer.parseInt(num);
        } catch (Exception ex) {
            System.out.println("Вы должны ввести число!");
            return false;
        }
        return true;
    }
}
