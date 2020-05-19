package CompanyManagerClass.Company;

public class BusinessEmployee extends Employee {
    private double bonusBudget = 0;

    public BusinessEmployee(String name) {
        super(name, 50000);
    }

    public double getBonusBudget() {
        return bonusBudget;
    }

    public void setBonusBudget(double bonusBudget) {
        this.bonusBudget = bonusBudget;
    }

    @Override
    public String employeeStatus() {
        return this.toString() + " with a budget of " + this.bonusBudget;
    }
}

