package CompanyManagerClass.Company;

public class Accountant extends BusinessEmployee {
    private TechnicalEmployee teamSupported;

    public Accountant(String name) {
        super(name);
    }

    public TechnicalEmployee getTeamSupported() {
        return teamSupported;
    }

    public void setTeamSupported(TechnicalLead lead) {
        this.teamSupported = lead;
        for(Employee e : lead.getDirectReports()) {
            this.setBonusBudget(this.getBonusBudget() + e.getBaseSalary() * 1.1);
        }
    }

    public boolean approveBonus(double bonus) {
        boolean approved = false;
        if (this.teamSupported != null) {
            if (this.getBonusBudget() >= bonus) {
                approved = true;
            }
        }
        // return (this.teamSupported != null) && (this.getBudget > bonus)
        return approved;
    }

    public String employeeStatus() {
        String status = super.employeeStatus() + " is not supporting a team at this time";
        if (this.getTeamSupported() != null) {
            status = super.employeeStatus() + " is supporting " + this.getTeamSupported().getName();
        }
        return status;
    }
}

