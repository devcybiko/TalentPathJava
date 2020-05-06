import java.util.Scanner;

/**
 * Sample of GOTO in BASIC
 * http://cwarden.org/warden/personalPage/caell/7_3.html
 */
public class JavaLabels {
    public static void main(String[] args) {
        breakOut();
        //simpleBreakOut();
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static void simpleBreakOut() {
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < i; j++) {
                print(i + "," + j);
                // if (i == 55) goto label;
            }
        }
        label: return;
    }

    private static void breakOut() {
        int ii = 4;
        int jj = 3;
        int kk = 7;

        outerLoop: for (int i = 0; i < 10; i++) {
            innerLoop: for (int j = 0; j < 10; j++) {
                crazyLoop: for (int k = 0; k < 10; k++) {
                    System.out.println(i + ", " + j + ", " + k);
                    if (i == ii && j == jj && k == kk) {
                        System.out.println("....... BREAK OUT! .......");
                        break outerLoop;
                    }
                }
            }
        }
        System.out.println("WE MADE IT OUT SAFELY!");
    }
}
