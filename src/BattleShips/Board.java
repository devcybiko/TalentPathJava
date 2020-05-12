package BattleShips;

public class Board {
    private static final String LETTERS = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "0123456789012345678901234567890";
    private static final String XDIGITS = "          111111111122222222223";

    private String[][] board;
    private int rows;
    private int cols;

    private static void print(Object o) {
        System.out.print(o);
    }

    private static void println(Object o) {
        System.out.println(o);
    }

    public Board(int rows, int cols, String s) {
        this.rows = rows;
        this.cols = cols;
        this.board = new String[rows][cols];
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                this.board[row][col] = s;
            }
        }
    }

    public int getRows() {
        return this.rows;
    }

    public int getCols() {
        return this.cols;
    }

    public String get(Coordinate coord) {
        return this.get(coord.getRow(), coord.getCol());
    }

    public String get(int row, int col) {
        if (0 <= row && row < this.rows) {
            if (0 <= col && col < this.cols) {
                return this.board[row][col];
            }
        }
        return "";
    }

    public void set(Coordinate coord, String value) {
        this.set(coord.getRow(), coord.getCol(), value);
    }

    public void set(int row, int col, String value) {
        this.board[row][col] = value;
    }

    public void print() {
        println("    " + XDIGITS.substring(0, this.cols));
        println("    " + DIGITS.substring(0, this.cols));
        int rowcnt = 0;
        for (String row[] : board) {
            print(" " + LETTERS.charAt(rowcnt) + ": ");
            print(String.join("", row));
            println(" :" + LETTERS.charAt(rowcnt));
            rowcnt++;
        }
        println("    " + DIGITS.substring(0, this.cols));
        println("    " + XDIGITS.substring(0, this.cols));
    }
}