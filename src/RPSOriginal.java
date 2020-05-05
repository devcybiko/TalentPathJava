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
public class RPSOriginal {
    private static Scanner scanner = new Scanner(System.in); // let's talk about stdin, stdout, stderr

    public static void main(String[] args) {
        int win = 0;
        int lose = 0;
        int draw = 0;

        System.out.println("\nLet's play RPS");
        System.out.print("Throw one of 'rps' > ");
        String userHand = scanner.nextLine(); // what if they enter stop?

        do {
            userHand = userHand.trim(); // nice!
            userHand = userHand.toLowerCase(); // but why all this transformation? do it on one line
            if (!"rps".contains(userHand)) { // good edit checks, concise albeit a bit obtuse
                System.out.println("\nPlease enter a valid throw");
                System.out.println("\nLet's play RPS"); // this looks familiar
                System.out.print("Throw one of 'rps' > ");// this looks familiar
                userHand = scanner.nextLine();// this looks familiar
                continue;
            }
            int rnd = (int) Math.floor(Math.random() * 3.0); // not explicit what its doing but OK
            String myHand = "rps".substring(rnd, rnd + 1); // notice scoping here - also, a bit obtuse
            System.out.println("I throw > " + myHand);
            String combined = userHand + myHand; // not entirely easy to understand
            if (combined.equals("rs") || combined.equals("sp") || combined.equals("pr")) { // concise if statement
                win++;
                System.out.println("\nYou Won!");
                System.out.println("Score: wins:" + win + " lose: " + lose + " draw: " + draw); // this looks familiar
            } else if (myHand.equals(userHand)) {
                draw++;
                System.out.println("\nIt's a tie!");
                System.out.println("Score: wins:" + win + " lose: " + lose + " draw: " + draw); // this looks familiar
            } else {
                lose++;
                System.out.println("\nI win!");
                System.out.println("Score: wins:" + win + " lose: " + lose + " draw: " + draw); // this looks familiar
            }
            System.out.println("\nLet's play RPS"); // this looks familiar
            System.out.print("Throw one of 'rps' > "); // this looks familiar
            userHand = scanner.nextLine(); // this looks familiar
        } while (!userHand.equals("stop")); // the termination criteria is WAY DOWN HERE!
        System.out.println("\nThanks for playing!");
        System.out.println("FINAL SCORE: wins:" + win + " lose: " + lose + " draw: " + draw);
    }
}
