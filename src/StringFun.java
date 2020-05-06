public class StringFun {
    public static void main(String[] args) {
        //substringFun();
        charAtFun();
    }

    private static void substringFun() {
        String input = "This is a test of the emergency broadcast system";
        int j = 0;
        for (int i = 0;
             i < input.length();
             i = j + 1) {
            j = input.indexOf(" ", i);
            if (j == -1) j = input.length();
            String token = input.substring(i, j);
            print("'" + token + "'");
        }
    }

    public static void charAtFun() {
        String input = "This is a test of the emergency broadcast system";
        char c = input.charAt(6);
        print(c);
        String cc = input.substring(6,7);
        print(cc);
        int x = c;
        print(x);
    }

    public static void print(Object o) {
        System.out.println(o);
    }
}
