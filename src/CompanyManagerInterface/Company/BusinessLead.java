package CompanyManagerInterface.Company;

import java.util.ArrayList;
import java.util.List;

public class BusinessLead extends BusinessEmployee implements Manager {
    private int headCount = 0;
    private List<Accountant> directReports = new ArrayList<>();

    public BusinessLead(String name) {
        super(name);
        Accountant tmp = new Accountant("tmp");
        this.setBaseSalary(tmp.getBaseSalary() * 2);
        this.setHeadCount(10);
    }

    public boolean hasHeadCount() {
        return this.getDirectReports().size() < this.getHeadCount();
    }

    public boolean addReport(Accountant e, TechnicalLead supportTeam) {
        boolean result = false;
        if (this.hasHeadCount()) {
            this.getDirectReports().add(e);
            this.setBonusBudget(this.getBonusBudget() + e.getBaseSalary() * 1.1);
            e.setTeamSupported(supportTeam);
            result = true;
        }
        return result;
    }

    public boolean approveBonus(Employee e, double bonus) {
        boolean result = false;
        for(Employee employee : this.getDirectReports()) {
            Accountant accountant = (Accountant) employee;
            TechnicalLead techlead = (TechnicalLead) accountant.getTeamSupported();
            if (techlead != null && techlead.getDirectReports().contains(e)) {
                result = this.requestBonus(e, bonus);
                break;
            }
        }
        return result;
    }

    public boolean requestBonus(Employee e, double bonus) {
        boolean result = false;
        if (this.getBonusBudget() >= bonus) {
            e.setBaseSalary(e.getBaseSalary() + bonus);
            this.setBonusBudget(this.getBonusBudget() - bonus);
            result = true;
        }
        return result;
    }

    @Override
    public boolean addReport(Employee e) {
        return false;
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

    public int getHeadCount() {
        return headCount;
    }

    public void setHeadCount(int headCount) {
        this.headCount = headCount;
    }

    public List<Accountant> getDirectReports() {
        return directReports;
    }

    public void setDirectReports(List<Accountant> directReports) {
        this.directReports = directReports;
    }
}

