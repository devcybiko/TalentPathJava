package CompanyManagerInterface.Company;

public interface Manager {
    public boolean addReport(Employee e);

    public String getTeamStatus();

    public boolean hasHeadCount();

    public boolean requestBonus(Employee e, double bonus);
}
