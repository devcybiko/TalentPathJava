public class Template {
    private static String name;

    public Template(String vars) {
        this.name = vars;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
