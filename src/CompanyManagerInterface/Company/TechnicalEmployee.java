package CompanyManagerInterface.Company;

public class TechnicalEmployee extends Employee {
    private int checkins = 0;

    public TechnicalEmployee(String name) {
        super(name, 75000);

    }

    public int getCheckins() {
        return checkins;
    }

    public void setCheckins(int checkins) {
        this.checkins = checkins;
    }

    @Override
    public String employeeStatus() {
        return this.toString() + " has " + this.checkins + " successful check ins";
    }
}

