package BattleShips;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class BattleShipsClass {
    private static final int ROWS = 10;
    private static final int COLS = 10;
    private static final int NSHIPS = 5;
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String WATER = ".";
    private static final String BATTLESHIP = "O";
    private static final String USERSHIP = "@";
    private static final String COMPSHIP = "X";
    private static final String SPLASH = "-";
    private static final String CLOSE = "?";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (playBattleShip(scanner)) {
            print("\nPlay again (y/[n])? ");
            String again = scanner.nextLine().trim().toLowerCase();
            if (!again.startsWith("y")) break;
        }
    }

    private static Boolean playBattleShip(Scanner scanner) {
        Board gameBoard = new Board(ROWS, COLS, WATER);
        Board userBoard = new Board(ROWS, COLS, WATER);
        Board compBoard = new Board(ROWS, COLS, WATER);

        boolean quit = assignUserShips(userBoard, NSHIPS, scanner);
        if (quit) return null; // user quit

        assignCompShips(compBoard, userBoard, NSHIPS);
        mergeBoards(gameBoard, userBoard);

        Boolean win = false;
        Boolean lose = false;
        int moves = 0;
        while (true) {
            moves++;
            gameBoard.print();
            win = userMove(gameBoard, compBoard, userBoard, scanner); //null => user quit the game
            if (win == null || win) break;
            lose = compMove(gameBoard, compBoard, userBoard);
            if (lose) break;
        }
        gameBoard.print();
        if (win == null) println("Sorry to see you go so soon!");
        if (win != null && win) println("Congratulations, you won in just " + moves + " moves!");
        if (lose) println("Bummer, the computer beat you in just " + moves + " moves!");
        return win != null; // true if the user did not enter 'stop'
    }

    private static void mergeBoards(Board gameBoard, Board userBoard) {
        for (int irow = 0; irow < gameBoard.getRows(); irow++) {
            for (int icol = 0; icol < gameBoard.getCols(); icol++) {
                if (userBoard.get(irow, icol).equals(BATTLESHIP)) {
                    gameBoard.set(irow, icol, BATTLESHIP);
                }
            }
        }
    }

    private static Boolean userMove(Board gameBoard, Board compBoard, Board userBoard, Scanner scanner) {
        if (countShips(compBoard) == 0) return true;
        Coordinate move = readCoords(scanner, "Enter your shot. ", gameBoard);
        if (move == null) return null; // user quit
        printCoords(move);

        String cell = gameBoard.get(move);
        if (cell.equals(WATER) || cell.equals(CLOSE) || cell.equals(BATTLESHIP)) { // it's a valid shot
            if (compBoard.get(move).equals(BATTLESHIP)) { // it's a hit!
                println("IT'S A HIT!");
                compBoard.set(move, WATER);
                gameBoard.set(move, COMPSHIP);
            } else if (userBoard.get(move).equals(BATTLESHIP)) { // whaaaattt?
                println("YOU HIT YOUR OWN SHIP!");
                gameBoard.set(move, USERSHIP);
                userBoard.set(move, WATER);
            } else { // its a miss :(
                println("aww... it's a miss");
                gameBoard.set(move, SPLASH);
                if (isClose(compBoard, move, BATTLESHIP)) {
                    println("but it's CLOSE!");
                    gameBoard.set(move, CLOSE);
                }
            }
        } else {
            println("Whaaaatttt???? That coordinate is already bombed!\nBetter luck next time.");
        }
        return countShips(compBoard) == 0;
    }

    private static Boolean isClose(Board board, Coordinate coord, String s) {
        if (board.get(coord.getRow() - 1, coord.getCol()).equals(s)) return true;
        if (board.get(coord.getRow() + 1, coord.getCol()).equals(s)) return true;
        if (board.get(coord.getRow(), coord.getCol() - 1).equals(s)) return true;
        if (board.get(coord.getRow(), coord.getCol() + 1).equals(s)) return true;
        return false;
    }

    private static Boolean compMove(Board gameBoard, Board compBoard, Board userBoard) {
        println("\nComputer shoots...");
        if (countShips(userBoard) == 0) return true; // user lost on previous move
        while (true) {
            Coordinate move = randomCoords(compBoard);
            // printCoords(move);
            String cell = gameBoard.get(move);
            if (cell.equals(WATER) || cell.equals(CLOSE) || cell.equals(BATTLESHIP)) { // it's a valid shot
                if (userBoard.get(move).equals(BATTLESHIP)) { // it's a hit!
                    printCoords(move);
                    println("IT'S A HIT!");
                    userBoard.set(move, WATER);
                    gameBoard.set(move, USERSHIP);
                    break;
                } else if (compBoard.get(move).equals(BATTLESHIP)) { // whaaaattt?
//                    println("COMPUTER HIT THEIR OWN SHIP!");
//                    gameBoard.set( move,  COMPSHIP);
//                    compBoard.set( move,  WATER);
//                    break;
                    continue;
                } else { // its a miss :(
                    printCoords(move);
                    println("whew!... it's a miss");
                    gameBoard.set(move, SPLASH);
                    break;
                }
            }
        }
        return countShips(userBoard) == 0; // user lost
    }

    private static int countShips(Board board) {
        int ships = 0;
        for (int irow = 0; irow < board.getRows(); irow++) {
            for (int icol = 0; icol < board.getCols(); icol++) {
                if (board.get(irow, icol).equals(BATTLESHIP)) {
                    ships++;
                }
            }
        }
        return ships;
    }

    private static Coordinate readCoords(Scanner scanner, String prompt, Board board) {
        final String ERRORMSG = "Please enter coordinates in row,col format";
        Coordinate result = null;

        while (true) {
            // board.print();
            print(prompt + " row, col (stop): ");
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("stop")) break;
            if (input.equals("")) return randomCoords(board);

            try {
                String[] words = input.split(",");
                if (words.length < 2) throw new Exception(ERRORMSG);
                String row = words[0];
                String col = words[1];
                if (row.length() != 1) throw new Exception(ERRORMSG);
                if (!LETTERS.contains(row)) throw new Exception(ERRORMSG);
                int iRow = LETTERS.indexOf(row);
                int iCol = Integer.parseInt(col); // note - OBO plus, exception on bad value
                if (!between(0, iRow, board.getRows() - 1))
                    throw new Exception("Please make the row a letter from a-" + LETTERS.charAt(board.getRows() - 1));
                if (!between(0, iCol, board.getCols() - 1))
                    throw new Exception("Please make the col a number from 0-" + (board.getCols() - 1));
                result = new Coordinate(iRow, iCol);
                break;
            } catch (Exception ex) {
                println("Invalid response. Please try again.");
                println(ex.getMessage());
            }
        }
        return result;
    }

    private static boolean assignUserShips(Board board, int n, Scanner scanner) {
        final String[] numbers = {"first", "second", "third", "fourth", "fifth", "sixth", "seventh", "eighth", "ninth", "tenth"};
        int i;
        for (i = 0; i < n; i++) {
            board.print();
            String prompt = "Enter your " + numbers[i] + " ship coordinates.\n";
            Coordinate coord = readCoords(scanner, prompt, board);
            if (coord == null) break; // user wants to quit
            if (!board.get(coord).equals(WATER)) {
                print("I'm Sorry - you already have a ship there. Try Again.");
                i--; // tricky code - we're redoing this iteration
                continue;
            }
            board.set(coord, BATTLESHIP);
        }
        return (i != n); // note - this will be true if the user wants to quit
    }

    private static Coordinate randomCoords(Board board) {
        Random random = new Random();
        Coordinate coords = new Coordinate(
                random.nextInt(board.getRows()),
                random.nextInt(board.getCols()));
        return coords;
    }

    private static void assignCompShips(Board compBoard, Board userBoard, int n) {
        for (int i = 0; i < n; i++) {
            Coordinate coord = randomCoords(compBoard);
            String compCell = compBoard.get(coord);
            String userCell = userBoard.get(coord);
            if (!(compCell.equals(WATER) && userCell.equals(WATER))) {
                // dont select a cell that either the user or computer has already populated
                i--; // tricky code - we're redoing this iteration
                continue;
            }
            compBoard.set(coord, BATTLESHIP);
        }
    }
    private static void print(Object o) {
        System.out.print(o);
    }

    private static void println(Object o) {
        System.out.println(o);
    }

    private static void printCoords(Coordinate move) {
        println("..." + LETTERS.substring(move.getRow(), move.getRow() + 1) + "," + move.getCol());
    }

    private static boolean between(int min, int i, int max) { // inclusive
        return (i >= min && i <= max);
    }

}
