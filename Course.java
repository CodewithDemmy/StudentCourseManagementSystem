
public class Course {

    private String code;
    private String title;
    private int units;

    public Course(String code, String title, int units) {
        this.code = code;
        this.title = title;
        this.units = units;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public int getUnits() {
        return units;
    }

    
    @Override
    public String toString() {
        return String.format("%-10s %-35s %d unit(s)", code, title, units);
    }

    
    public String toFileString() {
        return code + "," + title + "," + units;
    }
}