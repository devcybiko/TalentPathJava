package BattleShips;

import java.util.Random;
import java.util.Scanner;

public class BattleShipsRedux {

    public static final int ROWS = 10;
    public static final int COLS = 10;
    public static final int SHIPS = 5;
    public static final char WATER = '.';
    public static final char USERSHIP = 'O';
    public static final char COMPSHIP = 'X';
    public static final char SUNKSHIP = '!';
    public static final char MISS = '-';

    public BattleShipsRedux() {
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        playBattleShips(scanner);
    }

    private static void playBattleShips(Scanner scanner) {
        char[][] playOcean = newBoard();
        char[][] hiddenOcean = newBoard();

        compPlacement(hiddenOcean, USERSHIP);
        // userPlacement(hiddenOcean, playOcean, scanner);
        compPlacement(hiddenOcean, COMPSHIP);
        printBoard(playOcean);
        mainGameLoop(hiddenOcean, playOcean, scanner);
    }

    private static void mainGameLoop(char[][] hiddenOcean, char[][] playOcean, Scanner scanner) {
        boolean isGameActive = true;
        while (isGameActive) {
            getUserTurn(hiddenOcean, playOcean, scanner);
            printBoard(playOcean);
            getCompTurn(hiddenOcean, playOcean);
            printBoard(playOcean);
//            victoryCheck();
        }
    }

    public static void getUserTurn(char[][] hiddenOcean, char[][] playOcean, Scanner scanner) {
        boolean targetSelected = false;
        while(!targetSelected) {
            println("Enter X Coord for ship ");
            int row = Integer.parseInt(scanner.nextLine());
            println("Enter Y Coord for ship ");
            int col = Integer.parseInt(scanner.nextLine());
            switch (hiddenOcean[row][col]) {
                case USERSHIP: {
                    println("You sunk your own ship!");
                    hiddenOcean[row][col] = SUNKSHIP;
                    playOcean[row][col] = SUNKSHIP;
                    targetSelected = true;
                    break;
                }
                case COMPSHIP: {
                    println("ITS A HIT! You sunk a computer ship");
                    hiddenOcean[row][col] = SUNKSHIP;
                    playOcean[row][col] = SUNKSHIP;
                    targetSelected = true;
                    break;
                }
                case WATER: {
                    println("its a miss");
                    hiddenOcean[row][col] = MISS;
                    playOcean[row][col] = MISS;
                    targetSelected = true;
                    break;
                }
                case SUNKSHIP: {
                    println("hey- you already sunk this ship, try again!");
                    targetSelected = false;
                    break;
                }
                case MISS: {
                    println("hey- you already shot there and missed... try again!");
                    targetSelected = false;
                    break;
                }
                default: {
                    println("whaaattt?? I don't think this should ever happen");
                    targetSelected = false;
                    break;
                }
            }
        }
    }
    public static void getCompTurn(char[][] hiddenOcean, char[][] playOcean) {
        Random random = new Random();
        boolean targetSelected = false;
        while(!targetSelected) {
            int row = random.nextInt(hiddenOcean.length);
            int col = random.nextInt(hiddenOcean[0].length);
            switch (hiddenOcean[row][col]) {
                case USERSHIP: {
                    println("Computer sank our battleship at " + row +"," +col + "!");
                    hiddenOcean[row][col] = SUNKSHIP;
                    playOcean[row][col] = SUNKSHIP;
                    targetSelected = true;
                    break;
                }
                case COMPSHIP: {
                    println("FRIENDLY FIRE- the computer sank its own ship!");
                    hiddenOcean[row][col] = SUNKSHIP;
                    playOcean[row][col] = SUNKSHIP;
                    targetSelected = true;
                    break;
                }
                case WATER: {
                    println("its a miss");
                    hiddenOcean[row][col] = MISS;
                    playOcean[row][col] = MISS;
                    targetSelected = true;
                    break;
                }
                case SUNKSHIP: {
                    println("hey- computer sank a dead ship hahaha!");
                    targetSelected = false;
                    break;
                }
                case MISS: {
                    println("Silly computer, it hit a splash zone again!");
                    targetSelected = false;
                    break;
                }
                default: {
                    println("whaaattt?? I don't think this should ever happen");
                    targetSelected = false;
                    break;
                }
            }
        }
    }

    private static void printBoard(char[][] board) {

        for (int col = 0; col < COLS; col++) {
            print(col);
        }
        println("");
        for (int row = 0; row < ROWS; row++) {
            for (int col = 0; col < COLS; col++) {
                print(board[row][col]);
            }
            println("");
        }
    }

    private static void compPlacement(char[][] hiddenOcean, char ship) {
        Random random = new Random();
        for (int i = 0; i < SHIPS; i++) {
            while (true) {
                int row = random.nextInt(ROWS);
                int col = random.nextInt(COLS);
                if (hiddenOcean[row][col] == WATER) {
                    hiddenOcean[row][col] = ship;
                    break;
                }
            }
        }
    }

    private static void userPlacement(char[][] hiddenOcean, char[][] playOcean, Scanner scanner) {
        for (int i = 0; i < SHIPS; i++) {
            println("Enter X Coord for ship #" + i);
            int row = Integer.parseInt(scanner.nextLine());
            println("Enter Y Coord for ship #" + i);
            int col = Integer.parseInt(scanner.nextLine());
            if (inBounds(hiddenOcean, row) && inBounds(hiddenOcean, col)) {
                if (hiddenOcean[row][col] == WATER) {
                    hiddenOcean[row][col] = USERSHIP;
                }
            }
        }
    }

    private static boolean inBounds(char[][] hiddenOcean, int row) {
        return 0 <= row && row < hiddenOcean.length;
    }

    private static char[][] newBoard() {
        char[][] board = new char[ROWS][COLS];
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                board[row][col] = WATER;
            }
        }
        return board;
    }

    private static void print(Object o) {
        System.out.print(o);
    }

    private static void println(Object o) {
        System.out.println(o);
    }
}

