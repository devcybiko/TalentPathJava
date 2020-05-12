package BattleShips;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleShipsListsOfListsOfStrings {
    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int NSHIPS = 5;
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789012345678901234567890";
    private static final String XDIGITS = "          111111111122222222223";
    private static final String WATER = ".";
    private static final String BATTLESHIP = "O";
    private static final String USERSHIP = "@";
    private static final String COMPSHIP = "X";
    private static final String SPLASH = "-";
    private static final String CLOSE = "?";


    public static void main(String[] args) {
        playBattleShip();
    }

    public static Boolean playBattleShip() {
        Scanner scanner = new Scanner(System.in);
        List<List<String>> gameBoard = newBoard(ROWS, COLS);
        List<List<String>> userBoard = newBoard(ROWS, COLS);
        List<List<String>> compBoard = newBoard(ROWS, COLS);

        List<Integer[]> userShips = assignUserShips(userBoard, NSHIPS, scanner);
        if (userShips == null) return null; // user quit
        List<Integer[]> compShips = assignCompShips(compBoard, userBoard, NSHIPS);

        mergeBoards(gameBoard, userBoard);

        printBoard(userBoard);
        printBoard(compBoard);

        Boolean win = false;
        Boolean lose = false;
        int moves = 0;
        while (true) {
            moves++;
//            printBoard(userBoard);
//            printBoard(compBoard);
            printBoard(gameBoard);
            win = userMove(gameBoard, compBoard, userBoard, scanner); //null => user quit the game
            if (win == null || win) break;
            lose = compMove(gameBoard, compBoard, userBoard);
            if (lose) break;
        }
        printBoard(gameBoard);
        if (win != null && win) println("Congratulations, you won in just " + moves + " moves!");
        if (lose) println("Bummer, the computer beat you in just " + moves + " moves!");
        return win;
    }

    private static void mergeBoards(List<List<String>> gameBoard, List<List<String>> userBoard) {
        for (int irow = 0; irow < boardRows(gameBoard); irow++) {
            for (int icol = 0; icol < boardRows(gameBoard); icol++) {
                if (get(userBoard, irow, icol).equals(BATTLESHIP)) {
                    set(gameBoard, irow, icol, BATTLESHIP);
                }
            }
        }
    }

    private static Boolean userMove(List<List<String>> gameBoard, List<List<String>> compBoard, List<List<String>> userBoard, Scanner scanner) {
        if (countShips(compBoard) == 0) return true;
        Integer[] move = readCoords(scanner, "Enter your shot. ", gameBoard);
        if (move == null) return null; // user quit
        int row = move[0];
        int col = move[1];
        printCoords(row, col);

        String cell = get(gameBoard, row, col);
        if (cell.equals(WATER) || cell.equals(CLOSE) || cell.equals(BATTLESHIP)) { // it's a valid shot
            if (get(compBoard, row, col).equals(BATTLESHIP)) { // it's a hit!
                println("IT'S A HIT!");
                set(compBoard, row, col, WATER);
                set(gameBoard, row, col, COMPSHIP);
            } else if (get(userBoard, row, col).equals(BATTLESHIP)) { // whaaaattt?
                println("YOU HIT YOUR OWN SHIP!");
                set(gameBoard, row, col, USERSHIP);
                set(userBoard, row, col, WATER);
            } else { // its a miss :(
                println("aww... it's a miss");
                set(gameBoard, row, col, SPLASH);
                if (isClose(compBoard, row, col, BATTLESHIP)) {
                    println("but it's CLOSE!");
                    set(gameBoard, row, col, CLOSE);
                }
            }
        } else {
            println("Whaaaatttt???? That coordinate is already bombed!\nBetter luck next time.");
        }
        return countShips(compBoard) == 0;
    }

    private static Boolean isClose(List<List<String>> board, int row, int col, String s) {
        if (get(board, row - 1, col).equals(s)) return true;
        if (get(board, row + 1, col).equals(s)) return true;
        if (get(board, row, col - 1).equals(s)) return true;
        if (get(board, row, col + 1).equals(s)) return true;
        return false;
    }

    private static void printCoords(int row, int col) {
        println("..." + LETTERS.substring(row, row + 1) + "," + col);
    }
    private static Boolean compMove(List<List<String>> gameBoard, List<List<String>> compBoard, List<List<String>> userBoard) {
        println("\nComputer shoots...");
        if (countShips(userBoard) == 0) return true; // user lost on previous move
        while (true) {
            Integer[] move = randomCoords(compBoard);
            int row = move[0];
            int col = move[1];
//            printCoords(row, col);
            String cell = get(gameBoard, row, col);
            if (cell.equals(WATER) || cell.equals(CLOSE) || cell.equals(BATTLESHIP)) { // it's a valid shot
                if (get(userBoard, row, col).equals(BATTLESHIP)) { // it's a hit!
                    printCoords(row, col);
                    println("IT'S A HIT!");
                    set(userBoard, row, col, WATER);
                    set(gameBoard, row, col, USERSHIP);
                    break;
                } else if (get(compBoard, row, col).equals(BATTLESHIP)) { // whaaaattt?
//                    println("COMPUTER HIT THEIR OWN SHIP!");
//                    set(gameBoard, row, col, COMPSHIP);
//                    set(compBoard, row, col, WATER);
//                    break;
                    continue;
                } else { // its a miss :(
                    printCoords(row, col);
                    println("whew!... it's a miss");
                    set(gameBoard, row, col, SPLASH);
                    break;
                }
            }
        }
        return countShips(userBoard) == 0; // user lost
    }

    private static int countShips(List<List<String>> board) {
        int ships = 0;
        for (int irow = 0; irow < boardRows(board); irow++) {
            for (int icol = 0; icol < boardCols(board); icol++) {
                if (get(board, irow, icol).equals(BATTLESHIP)) {
                    ships++;
                }
            }
        }
        return ships;
    }

    private static List<List<String>> newBoard(int rows, int cols) {
        List<List<String>> board = new ArrayList<>();
        for (int row = 0; row < rows; row++) {
            List<String> rowList = new ArrayList<>();
            board.add(rowList);
            for (int col = 0; col < cols; col++) {
                board.get(row).add(WATER);
            }
        }
        return board;
    }

    private static String get(List<List<String>> board, int row, int col) {
        if (0 <= row && row < boardRows(board)) {
            if (0 <= col && col < boardCols(board)) {
                return board.get(row).get(col);
            }
        }
        return "";
    }

    private static void set(List<List<String>> board, int row, int col, String value) {
        board.get(row).set(col, value);
    }

    private static Integer[] readCoords(Scanner scanner, String prompt, List<List<String>> board) {
        final String ERRORMSG = "Please enter coordinates in (row,col) format";
        Integer[] result = null;
        int maxrows = boardRows(board);
        int maxcols = boardCols(board);
        while (true) {
            // printBoard(rows);
            print(prompt + "row, col (stop): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("stop")) {
                break;
            }
            if (input.equals("")) {
                return randomCoords(board);
            }
            try {
                String[] words = input.split(",");
                if (words.length < 2) throw new Exception(ERRORMSG);
                String row = words[0];
                String col = words[1];
                if (row.length() != 1) throw new Exception(ERRORMSG);
                if (!LETTERS.contains(row)) throw new Exception(ERRORMSG);
                int iRow = LETTERS.indexOf(row);
                int iCol = Integer.parseInt(col); // note - OBO plus, exception on bad value
                if (0 > iRow || iRow >= maxrows)
                    throw new Exception("Please make the row a letter from a-" + LETTERS.charAt(maxrows - 1));
                if (0 > iCol || iCol >= maxcols)
                    throw new Exception("Please make the col a number from 0-" + (maxcols - 1));
                result = new Integer[2];
                result[0] = iRow;
                result[1] = iCol;
                break;
            } catch (Exception ex) {
                println("Invalid response. Please try again.");
                println(ex.getMessage());
            }
        }
        return result;
    }

    private static List<Integer[]> assignUserShips(List<List<String>> board, int n, Scanner scanner) {
        final String[] numbers = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth"};
        List<Integer[]> ships = new ArrayList<Integer[]>();
        int i;
        for (i = 0; i < n; i++) {
            printBoard(board);
            String prompt = "Enter your " + numbers[i] + " ship coordinates.\n";
            Integer[] coords = readCoords(scanner, prompt, board);
            if (coords == null) {
                break; // user wants to quit
            }
            int row = coords[0]; // for clarity - self-documenting code
            int col = coords[1];
            if (!get(board, row, col).equals(WATER)) {
                print("I'm Sorry - you already have a ship there. Try Again.");
                i--; // tricky code - we're redoing this iteration
                continue;
            }
            set(board, row, col, BATTLESHIP);
            ships.add(coords);
        }
        return (i == n) ? ships : null; // note - this will be null if the user wants to quit
    }

    private static Integer[] randomCoords(List<List<String>> board) {
        Random random = new Random();
        int maxrows = boardRows(board);
        int maxcols = boardCols(board);

        Integer[] coords = new Integer[2];
        int row = random.nextInt(maxrows); // for clarity - self-documenting code
        int col = random.nextInt(maxcols);
        coords[0] = row;
        coords[1] = col;
        return coords;
    }

    private static List<Integer[]> assignCompShips(List<List<String>> compBoard, List<List<String>> userBoard, int n) {
        List<Integer[]> ships = new ArrayList<Integer[]>();
        for (int i = 0; i < n; i++) {
            Integer[] coords = randomCoords(compBoard);
            int row = coords[0];
            int col = coords[1];
            if (!get(compBoard, row, col).equals(WATER) || !get(userBoard, row, col).equals(WATER)) {
                i--; // tricky code - we're redoing this iteration
                continue;
            }
            set(compBoard, row, col, BATTLESHIP);
            ships.add(coords);
        }
        return ships;
    }

    private static void printBoard(List<List<String>> board) {
        println("    " + XDIGITS.substring(0, boardCols(board)));
        println("    " + DIGITS.substring(0, boardCols(board)));
        int rowcnt = 0;
        for (List<String> row : board) {
            print(" " + LETTERS.charAt(rowcnt) + ": ");
            for (String cell : row) {
                print(cell);
            }
            println(" :" + LETTERS.charAt(rowcnt));
            rowcnt++;
        }
        println("    " + DIGITS.substring(0, boardCols(board)));
        println("    " + XDIGITS.substring(0, boardCols(board)));

    }

    private static void print(Object o) {
        System.out.print(o);
    }

    private static void println(Object o) {
        System.out.println(o);
    }

    private static int boardRows(List<List<String>> board) {
        return board.size();
    }
    private static int boardCols(List<List<String>>  board) {
        return board.get(0).size();
    }

}
