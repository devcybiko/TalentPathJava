package Company;

public class SoftwareEngineer extends TechnicalEmployee {
    private boolean codeAccess = false;

    public SoftwareEngineer(String name) {
        super(name);
    }

    public boolean getCodeAccess() {
        return codeAccess;
    }

    public void setCodeAccess(boolean codeAccess) {
        this.codeAccess = codeAccess;
    }

    public int getSuccessfulCheckIns(){
        return 0;
    }

    public boolean checkInCode() {
        return false;
    }
}

