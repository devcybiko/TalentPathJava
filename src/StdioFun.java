import java.util.Scanner;

public class StdioFun {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        for(int i=0; i<3; i++) {
            String in = scanner.nextLine();
            System.out.println("...." + in);
            System.err.println("error...");
        }
    }
}
