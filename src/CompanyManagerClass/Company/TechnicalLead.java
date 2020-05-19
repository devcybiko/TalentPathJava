package CompanyManagerClass.Company;

import java.util.ArrayList;
import java.util.List;

public class TechnicalLead extends TechnicalEmployee  {
    private int checkins = 0;
    private BusinessLead myBusinessLead = null;
    private int headCount = 0;
    private List<SoftwareEngineer> directReports = new ArrayList<>();

    public TechnicalLead(String name) {
        super(name);
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

    public boolean addReport(Employee e) {
        return this.addReport((SoftwareEngineer) e);
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

    public BusinessLead getMyBusinessLead() {
        return myBusinessLead;
    }

    public void setMyBusinessLead(BusinessLead myBusinessLead) {
        this.myBusinessLead = myBusinessLead;
    }

    public int getHeadCount() {
        return headCount;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    public List<SoftwareEngineer> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<SoftwareEngineer> directReports) {
        this.directReports = directReports;
    }
}

