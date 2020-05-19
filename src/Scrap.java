import CompanyManagerInterface.Company.SoftwareEngineer;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;

public class Scrap {
    public static void main(String[] args) {
        D var = new D();
        var.method4();
        var.method1();
        var.method3();
    }
    public static class A {
        public void method1() {
            System.out.println("a 1");
        }
    }
    public static class B extends A {
        public void method2() {
            System.out.println("b 2");
        }
    }
    public static class C extends B {
        public void method3() {
            System.out.println("c 3");
        }
    }
    public static class D extends C {
        public void method4() {
            System.out.println("d 4");
        }
    }
}