package Company;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

abstract public class Employee {
    public static Scanner scanner = new Scanner(System.in);
    private static int employeeCount = 0;

    private String name;
    private Double baseSalary;
    private int id;
    private Employee manager;
    private boolean isaManager;
    private int headCount = 0;
    private List<Employee> directReports = new ArrayList<Employee>();

    public Employee() {
        this.id = ++employeeCount;
    }

    public Employee(String name, double baseSalary) {
        this();
        this.name = name;
        this.baseSalary = baseSalary;
    }

    public int getEmployeeID() {
        return id;
    }

    public Employee getManager() {
        return manager;
    }

    public void setManager(Employee manager) {
        this.manager = manager;
    }

    public boolean isManager() {
        return isaManager;
    }

    public void setIsManager(boolean isaManager) {
        this.isaManager = isaManager;
    }

    public boolean equals(Employee other) {
        return this.id == other.id;
    }

    public String toString() {
        return "" + this.id + ": " + this.name;
    }

    public List<Employee> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Employee> directReports) {
        this.directReports = directReports;
    }

    public int getHeadCount() {
        return headCount;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    public Double getBaseSalary() {
        return baseSalary;
    }

    public void setBaseSalary(Double baseSalary) {
        this.baseSalary = baseSalary;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    abstract public String employeeStatus();
}