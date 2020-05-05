import java.util.Scanner;

/**
 * Sample of GOTO in BASIC
 * http://cwarden.org/warden/personalPage/caell/7_3.html
 */
public class JavaLabels {
    public static void main(String[] args) {
        int ii = 4;
        int jj = 3;
        int kk = 7;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                for (int k = 0; k < 10; k++) {
                    System.out.println(i +", " + j + ", " + k);
                    if (i == ii && j == jj && k == kk) {
                        System.out.println("....... BREAK OUT! .......");
                    }
                }
            }
        }
        System.out.println("WE MADE IT OUT SAFELY!");
    }
}
