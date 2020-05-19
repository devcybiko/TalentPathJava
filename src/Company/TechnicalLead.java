package Company;

public class TechnicalLead extends TechnicalEmployee {
    private int checkins = 0;
    private BusinessLead myBusinessLead = null;

    public TechnicalLead(String name) {
        super(name);
        this.setIsManager(true);
        this.setBaseSalary(this.getBaseSalary() * 1.3);
        this.setHeadCount(4);
    }
    public boolean approveCheckIn(SoftwareEngineer e){
        return this.getDirectReports().contains(e) && e.getCodeAccess();
    }

    public boolean addReport(SoftwareEngineer e) {
        boolean result = false;
        if (this.hasHeadCount()) {
            this.getDirectReports().add(e);
            result = true;
        }
        return result;
    }

    public String getTeamStatus(){
        String status = "";
        if (this.getDirectReports().isEmpty()) {
            status += this.employeeStatus() + " and no direct reports yet.";
        } else {
            status += this.employeeStatus() + " and is managing:";
        }
        for(Employee e : this.getDirectReports()) {
            status += "\n\t" + e.employeeStatus();
        }
        return status;
    }

    public boolean hasHeadCount() {
        return this.getDirectReports().size() < this.getHeadCount();
    }

    public boolean requestBonus(Employee e, double bonus) {
        return this.myBusinessLead.approveBonus(e, bonus);
    }
}

