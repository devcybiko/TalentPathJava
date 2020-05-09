package Maze;

import java.util.Scanner;

public class MazeRunner {
    public static Scanner scanner = new Scanner(System.in);

    /**
     * WARNING MESSAGES:
     * Notice that I've eliminated if-hydras by moving my warnings into arrays
     * This allows you to create alternative break-points just by updating the array
     * You could easily have a dozen instead of just 4
     * ALSO: Make sure the warningBreaks and warningQuips have the same number of elements
     */
    public static int[] warningBreaks = {25, 50, 75, 100};
    public static String[] warningQuips = {
            "You're about a quarter through your moves",
            "You're halfway through your moves",
            "You're running out of time - just " + (warningBreaks[warningBreaks.length - 1] - warningBreaks[warningBreaks.length - 2]) + " moves left",
            "I'm sorry - but you've run out of time!"
    };

    /**
     * When I used System.err
     * I ran into trouble with the app printing the error messages after
     * the user input request - or before the maze board
     * This is due to the dual threads and streams for System.out and System.err
     * So, I decided to accumulate all info and error messages
     * And just print them together using ANSI_RED to change the error color
     */
    public static String errorMessage = ""; // accumulated error messages
    public static String infoMessage = ""; // accumulated informational messages

    public static final String ANSI_RED = "\u001B[31m"; // print this to create red text
    public static final String ANSI_RESET = "\u001B[0m"; // print this to reset the text color

    /**
     * main program
     *
     * @param args
     */
    public static void main(String[] args) {
        // for loop - play the game forever as long as they say "YES"
        for (String playAgain = "Y";
             playAgain.equals("Y");
             playAgain = getInput("Would you like to play again?", "YN")) {
            playGame();
        }
    }

    /**
     * play the whole game
     */
    private static void playGame() {
        Maze maze = new Maze();
        int moves = 0;
        String dir = "";

        while (true) {
            dir = getUserMove();
            if (dir.equals("S")) break; // get out if we want to stop

            moves = makeAMove(maze, dir, moves);
            if (moves == -1) break; // ran out of moves
            if (maze.didIWin()) break; // i won

            printMaze(maze);
            printMessages(); // print accumulated messages and errors
        }
        printCongratulations(maze, moves, dir);
    }

    private static void printMaze(Maze maze) {
        //maze.printSolution();
        uncoverSurroundings(maze);
        maze.printMap(); // print the maze
    }

    /**
     * get the user move (up, down, left, right, or stop)
     *
     * @return
     */
    public static String getUserMove() {
        String dir = getInput("Which direction would you like to go (or Stop)?", "SUDLR");
        return dir;
    }

    /**
     * print our accumulated messages and errros
     */
    public static void printMessages() {
        if (errorMessage.length() != 0) error(errorMessage);
        if (infoMessage.length() != 0) print(infoMessage);
        // remember to reset the messages to empty strings
        infoMessage = "";
        errorMessage = "";
    }

    /**
     * this moves the user and jumps over pits automatically
     * this also makes it easier to call the maze.moveXXX() methods
     * without a switch or if-hydra
     *
     * @param maze
     * @param dir
     * @param moves
     * @return
     */
    public static int makeAMove(Maze maze, String dir, int moves) {
        if (!navigatePit(maze, dir) && canIMove(maze, dir)) {
            if (dir.equals("U")) maze.moveUp();
            if (dir.equals("D")) maze.moveDown();
            if (dir.equals("L")) maze.moveLeft();
            if (dir.equals("R")) maze.moveRight();
        }
        return incMoves(moves);
    }

    private static boolean navigatePit(Maze maze, String dir) {
        if (maze.isThereAPit(dir)) {
            if (getInput("Watch out! There's a pit ahead, jump it?", "YN").equals("Y")) {
                try {
                    addError("JUMPING!");
                    maze.jumpOverPit(dir);
                    addError("You jumped over the pit! Watch out next time");
                } catch (Exception ex) {
                    addError("Wow - you cannot jump that pit"); // error in Maze.java (:O)
                }
            }
            return true; // i've processed the 'dir'
        }
        return false; // i did not process the 'dir'
    }

    public static void uncoverSurroundings(Maze maze) {
        try {
            maze.canIMoveUp();
            maze.canIMoveDown();
            maze.canIMoveLeft();
            maze.canIMoveRight();
        } catch (Exception ex) {
            addError("There's a bug in the Maze.java module :/");
        }
    }

    /**
     * handle incrementing moves and emitting 'quips'
     * at certain points. (25, 50, 75, 100 moves)
     *
     * @param moves
     * @return
     */
    public static int incMoves(int moves) {
        moves++;
        for (int i = 0; i < warningBreaks.length; i++) {
            // note that instead of a switch or if-hydra
            // I've used an array to determine the places where a warning is emitted
            if (moves == warningBreaks[i]) {
                addError(warningQuips[i]);
            }
        }
        if (moves == warningBreaks[warningBreaks.length - 1]) moves = -1; // maximum moves exceeded
        return moves;
    }

    /**
     * this makes calling the canIMoveXXX methods easier without an if-hydra
     *
     * @param maze
     * @param dir
     * @return
     */
    public static boolean canIMove(Maze maze, String dir) {
        boolean result = false;
        if (dir.equals("U")) result = maze.canIMoveUp();
        else if (dir.equals("D")) result = maze.canIMoveDown();
        else if (dir.equals("L")) result = maze.canIMoveLeft();
        else if (dir.equals("R")) result = maze.canIMoveRight();
        if (!result) {
            addError("You cannot move in that direction");
        }
        return result;
    }

    /**
     * Win, lose, or draw output
     *
     * @param maze
     * @param moves
     * @param dir
     */
    public static void printCongratulations(Maze maze, int moves, String dir) {
        printMaze(maze);
        if (maze.didIWin()) {
            addMessage("\n\nCongratulations! You won in only " + moves + " moves\n\n");
        } else if (moves == -1) {
            addError("\n\nI'm sorry - you lost because it took you too many moves\n\n");
        } else if (dir.equals("S")) { // stopped
            addMessage("\n\nGame aborted\n\n");
        } else {
            addError("\n\nWELL, HOW DID I GET HERE?\n\n"); // yeah, this should never happen
        }
        printMessages();
    }

    /**
     * generic get input method. Returns a single character and normalizes input
     *
     * @return
     */
    public static String getInput() {
        String s = scanner.nextLine().trim() + " "; // note - appending " " handles special case of no user input
        return s.toUpperCase().substring(0, 1); // normalize & single-character
    }

    /**
     * prints a message and waits for valid input
     * note how this is overloaded from getInput()
     *
     * @param message
     * @param valid
     * @return
     */
    public static String getInput(String message, String valid) { // overloaded function here
        String s;
        print(message);
        for (s = getInput();
             !valid.contains(s);
             s = getInput()) {
            error("Please enter one of " + valid);
        }
        return s;
    }

    /**
     * accumulate messages
     * Helps to print all messages after maze output but before user input
     *
     * @param message
     */
    public static void addMessage(String message) {
        infoMessage += "\n" + message;
    }

    /**
     * accumulate error messages
     * Helps to print all messages after maze output but before user input
     *
     * @param errMsg
     */
    public static void addError(String errMsg) {
        errorMessage += "\n" + errMsg;
    }

    /**
     * print output in BLACK
     *
     * @param o
     */
    public static void print(Object o) {
        System.out.print(ANSI_RESET);
        System.out.println(o);
    }

    /**
     * This prints out error messages in RED
     * NOTE: Using System.err causes problems with the order of the output.
     * NOTE: Often the System.err output comes before or after System.out
     * NOTE: Randomly. Using RED text gives us some control since we
     * NOTE: Always use System.out.
     *
     * @param o
     */
    public static void error(Object o) {
        System.out.print(ANSI_RESET);
        System.out.print(ANSI_RED);
        System.out.print(o);
        System.out.println(ANSI_RESET);
    }

}
