public class OverLoading {
    public static void main(String[] args) {
        int first = power(2, 3);        // power_int_int(2,3)
        int second = power(2, 3, 5); // power_int_int_int(2,3,5)
        print(first);
        print(second);
        print(power(2, 3));
    }

    public static void print(Object o) {
        System.out.println(o);
    }

    public static int power(int base, int exp) {
        int result = 1;
        for (int i = 1; i <= exp; i++) {
            result *= base;
        }
        return result;
    }

//    public static double power(int base, int exp) {
//        int result = 1;
//        for (int i = 1; i <= exp; i++) {
//            result *= base;
//        }
//        return result;
//    }

    public static int power(int base, int exp, int mult) {
        int result = 1;
        for (int i = 1; i <= exp; i++) {
            result *= base;
        }
        return result * mult;
    }

}
