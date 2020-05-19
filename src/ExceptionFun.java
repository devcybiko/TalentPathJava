import java.security.spec.ECField;
import java.util.Scanner;

public class ExceptionFun {
    private static Scanner scanner = new Scanner(System.in);

    public ExceptionFun() {
    }

    public static void main(String[] args) throws Exception {
        Integer num = 1;
        Integer den = 0;
        Integer result = 0;

        try {
            // file = fopen("a.txt", "w");
            result = num / den;
            println(result);
            // file.println(result);
        } catch (Exception ex) {
            println("ERROR!");
        } finally {
            // file.close();
        }

        tryMyForFun(num);

    }

    private static void tryMyForFun(Integer num) throws Exception {
        int d = div(5, 2);
        int c = div(num, -1);
    }

    private static int div(int a, int b) throws Exception {
        if (a < 0 || b < 0) {
            throw new Exception("why are you using negative values, fubar?");
        }
        return a / b;
    }

    private static void print(Object o) {
        System.out.print(o);
    }

    private static void println(Object o) {
        System.out.println(o);
    }
}

