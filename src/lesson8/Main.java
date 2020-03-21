package lesson8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;

public class Main {

    public static int SIZE;
    public static int DOTS_TO_WIN;
    public static final char DOT_EMPTY = '.';
    public static final char DOT_USER = 'X';
    public static final char DOT_AI = 'O';

    public static int DOT_FIELD_SIZE;

    public static Random rand = new Random();

    public static char[][] field;

    private static HashMap componentMap;

    static public class StartWindow extends JFrame {
        public StartWindow() {
            int winSize = 300;
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - getWidth() - winSize) / 2);
            int y = (int) ((dimension.getHeight() - getHeight() - winSize) / 2);
            setLocation(x, y);
            setSize(winSize,winSize);
            setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
            setResizable(false);

            JPanel panel = new JPanel();
            JButton addButton = new JButton("+");
            addButton.setFocusable(false);

            JLabel label = new JLabel("Размер поля (3-10):");
            label.setAlignmentX(LEFT_ALIGNMENT);
            JComboBox jCBSize = new JComboBox( new String[]{"3","4","5","6","7","8","9", "10"});

            panel.add(label);
            panel.add(jCBSize);
            add(panel, BorderLayout.NORTH);

            label = new JLabel("Количество точек для победы:");
            label.setAlignmentX(LEFT_ALIGNMENT);
            add(label);
            JComboBox jCBDotsToWin = new JComboBox( new String[]{"3","4","5","6","7","8","9", "10"});
            add(jCBDotsToWin);

            panel = new JPanel();
            panel.add(label);
            panel.add(jCBDotsToWin);
            add(panel, BorderLayout.CENTER);

            JButton button = new JButton("СТАРТ");
            button.setAlignmentX(CENTER_ALIGNMENT);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    int size =  Integer.parseInt(jCBSize.getSelectedItem().toString());
                    int win = Integer.parseInt(jCBDotsToWin.getSelectedItem().toString());
                    if (size < win) {
                        JOptionPane.showMessageDialog(button, "Количестов ячеек для победы не должно быть больше размера поля");
                    } else {
                        if(size<=6) {
                            DOT_FIELD_SIZE = 100;
                        } else {
                            DOT_FIELD_SIZE = 50;
                        }
                        SIZE = size;
                        DOTS_TO_WIN = win;
                        initField();
                        new GameWindow();
                        setVisible(false);
                        dispose();
                    }
                }
            });
            add(button, BorderLayout.LINE_END) ;

            setVisible(true);
        }
    }

    static public class GameWindow extends JFrame {
        public GameWindow() {
            int winSize = DOT_FIELD_SIZE * SIZE;
            Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
            int x = (int) ((dimension.getWidth() - getWidth() - winSize) / 2);
            int y = (int) ((dimension.getHeight() - getHeight() - winSize) / 2);
            setLocation(x, y);
            setSize(winSize,winSize);
            setResizable(false);

            setTitle("TicTacToe");

            setLayout(new GridLayout(SIZE, SIZE));
            for (int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                JButton button = new JButton();
                button.setName(i+":"+j);
                button.setSize(DOT_FIELD_SIZE,DOT_FIELD_SIZE);
                button.setFont(new Font("Arial", Font.PLAIN, 40));
                button.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                       userTurn((GameWindow) SwingUtilities.getRoot((Component) e.getSource()), (JButton)e.getSource());
                    }
                });
                    add(button);
                }
            }
            createComponentMap();

            setVisible(true);
        }

        private void createComponentMap() {
            componentMap = new HashMap<String,Component>();
            Component[] components = getContentPane().getComponents();
            for (int i=0; i < components.length; i++) {
                componentMap.put(components[i].getName(), components[i]);
            }
        }

        public Component getComponentByName(String name) {
            if (componentMap.containsKey(name)) {
                return (Component) componentMap.get(name);
            }
            else return null;
        }


    }

    public static void main(String[] args) {
        new StartWindow();
    }

    private static void aiTurn(GameWindow gW) {
        if (!checkAITurn(gW)) {
            int colNumber, rowNumber;
            do {
                rowNumber = rand.nextInt(SIZE) + 1;
                colNumber = rand.nextInt(SIZE) + 1;
            } while (!isCellValid(rowNumber, colNumber));

            field[rowNumber - 1][colNumber - 1] = DOT_AI;
            String btnName = (rowNumber - 1) + ":" + (colNumber - 1);
            setAIButton(gW, btnName);
        }
    }

    private static void setAIButton (GameWindow gW, String name) {
        JButton btn = (JButton) gW.getComponentByName(name);
        btn.setText( String.valueOf(DOT_AI));
        btn.setBackground(Color.GREEN);
        btn.setEnabled(false);
    }

    private static boolean checkAITurn(GameWindow gW) {
        //Если нельзя выиграть заполнив 1 ячейку, то проверям "уменьшаем" количество ячеек для победы и проверям опять
        //Таким образом компьютер будет строить линии и блокировать возможные линии игрока
        for (int i = DOTS_TO_WIN; i > 1; i--) {
            for (int j = 0; j < SIZE; j++) {
                for (int k = 0; k < SIZE; k++) {
                    if (field[j][k] == DOT_EMPTY) {
                        //Проверяем выиграет ли ИИ, если заполнит ячейку
                        field[j][k] = DOT_AI;
                        if (checkWin(DOT_AI, DOTS_TO_WIN - (DOTS_TO_WIN - i))) {
                            String btnName = (j) + ":" + (k);
                            setAIButton(gW, btnName);
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
                                String btnName = (j) + ":" + (k);
                                setAIButton(gW, btnName);
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

    private static boolean isEnd(char symbol, String message, JButton button) {
        if (checkWin(symbol, DOTS_TO_WIN)) {
            JOptionPane.showMessageDialog(button, message);
            return true;
        }

        if (isMapFull()) {
            JOptionPane.showMessageDialog(button, "Ничья");
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

    private static void userTurn(GameWindow gW, JButton button) {

        String[] position = button.getName().split(":");
        int row = Integer.parseInt(position[0]);
        int col = Integer.parseInt(position[1]);
            field[row][col] = DOT_USER;
            button.setText( String.valueOf(DOT_USER));
            button.setBackground(Color.RED);
            button.setEnabled(false);
        if (isEnd(DOT_USER, "Вы победили", button)) {
            gW.setVisible(false);
            gW.dispose();
            new StartWindow();
            return;
        }

        aiTurn(gW);
        if (isEnd(DOT_AI, "Компьютер победил", button)) {
            gW.setVisible(false);
            gW.dispose();
            new StartWindow();
            return;
        }
    }

    private static boolean isCellValid(int rowNumber, int colNumber) {
        if (field[rowNumber - 1][colNumber - 1] != DOT_EMPTY) {
            return false;
        }
        return true;
    }


    private static void initField() {
        field = new char[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                field[i][j] = DOT_EMPTY;
            }
        }
    }

}
