import java.util.Arrays;
import java.util.Scanner;
/**
 * RPS Refactoring
 *
 * 1. Stop criteria at the bottom of while
 * 2. Refactor User input + trim / toLower
 * 3. Error checking of hand
 *     1. recursive
 *     2. non-recursive/loop
 * 4. Extract Computer Throw
 * 5. Extract win/loss/draw score
 *     1. extract win/loss/draw
 * 6. Replace Constants
 *     1. RPS
 *     2. rps
 *     3. stop
 *     4. TITLE
 * 7. Generalize for RPSLX
 * 8. FINALLY - bad refactoring
 *     1. convert while to for()
 */
public class RPSfinal {
    public static final String TITLE = "RPSLX";
    public static final String GUESSES = "rpslx";
    public static String[] winners = {"rs", "sp", "pr", "px", "rl", "xs", "lx", "sl", "lp", "xr"};
    public static final String STOP = "stop";

    private static Scanner scanner = new Scanner(System.in); // let's talk about stdin, stdout, stderr

    public static void main(String[] args) {
        int win = 0;
        int lose = 0;
        int draw = 0;

        Arrays.sort(winners);

        for (String userHand = getUserThrow();
             !userHand.equals(STOP);
             userHand = getUserThrow())
        {
            if (userHand == null) continue;
            String myHand = getComputerThrow();
            String combined = userHand + myHand; // not entirely easy to understand
            if (compareThrows(userHand, myHand)) { // concise if statement
                win++;
                showScore(win, lose, draw, "\nYou Won!", "Score: wins:");
            } else if (myHand.equals(userHand)) {
                draw++;
                showScore(win, lose, draw, "\nIt's a tie!", "Score: wins:");
            } else {
                lose++;
                showScore(win, lose, draw, "\nI win!", "Score: wins:");
            }
        }
        showScore(win, lose, draw, "\nThanks for playing!", "FINAL SCORE: wins:");
    }

    private static boolean compareThrows(String userHand, String myHand) {
        String combined = userHand + myHand;
        int result = Arrays.binarySearch(winners, combined);
        return result >= 0;
    }

    private static void showScore(int win, int lose, int draw, String s, String s2) {
        System.out.println(s);
        System.out.println(s2 + win + " lose: " + lose + " draw: " + draw); // this looks familiar
    }

    private static String getComputerThrow() {
        int rnd = myRandom(0, GUESSES.length());
        String myHand = "rps".substring(rnd, rnd + 1); // notice scoping here - also, a bit obtuse
        System.out.println("I throw > " + myHand);
        return myHand;
    }

    private static int myRandom(int min, int max) {
        return (int) Math.floor(Math.random() * (max - min + 1)) + min;
    }

    private static String getUserThrow() {
        System.out.println("\nLet's play " + TITLE);
        System.out.print("Throw one of " + GUESSES + "> ");

        String userHand = getUserThrowNormalized();
        while (userHand == STOP && !GUESSES.contains(userHand)) { // good edit checks, concise albeit a bit obtuse
            System.out.println("\nPlease enter a valid throw");
            System.out.println("\nLet's play RPS"); // this looks familiar
            System.out.print("Throw one of 'rps' > ");// this looks familiar
            userHand = getUserThrowNormalized();
        }
        return userHand;
    }

    private static String getUserThrowNormalized() {
        return scanner.nextLine().trim().toLowerCase();
    }
}
