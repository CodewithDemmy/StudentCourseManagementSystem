import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;


public class CourseManager {

    private List<Course> courses;
    private static final String FILE_NAME = "courses.txt";

    public CourseManager() {
        courses = new ArrayList<>();
    }

   
    public void addCourse(String code, String title, int units) throws InvalidCourseException {
        code = code.trim().toUpperCase();
        title = title.trim();

        if (code.isEmpty() || title.isEmpty()) {
            throw new InvalidCourseException("Course code and title cannot be empty.");
        }
        if (units <= 0 || units > 6) {
            throw new InvalidCourseException("Units must be between 1 and 6.");
        }
        if (findCourseRecursive(code, 0) != null) {
            throw new InvalidCourseException("A course with code '" + code + "' already exists.");
        }

        courses.add(new Course(code, title, units));
        System.out.println("Course added successfully: " + code);
    }

    
    public void displayAllCourses() {
        if (courses.isEmpty()) {
            System.out.println("No courses recorded yet.");
            return;
        }
        System.out.println("\n---- Recorded Courses ----");
        for (Course c : courses) {
            System.out.println(c);
        }
        System.out.println("---------------------------");
    }

   
    public Course findCourseRecursive(String code, int index) {
        if (index >= courses.size()) {
            return null;
        }
        if (courses.get(index).getCode().equalsIgnoreCase(code)) {
            return courses.get(index);
        }
        return findCourseRecursive(code, index + 1);
    }

    public void searchCourse(String code) {
        code = code.trim();
        Course found = findCourseRecursive(code, 0);
        if (found != null) {
            System.out.println("Course found:\n" + found);
        } else {
            System.out.println("No course found with code '" + code + "'.");
        }
    }

    
    public int computeTotalUnits() {
        int total = 0;
        for (Course c : courses) {
            total += c.getUnits();
        }
        return total;
    }

    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Course c : courses) {
                writer.println(c.toFileString());
            }
            System.out.println("Courses saved successfully to " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }

    public void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            System.out.println("No saved file found (" + FILE_NAME + ").");
            return;
        }

        courses.clear();
        int loadedCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.trim().isEmpty()) {
                    continue;
                }
                try {
                    String[] parts = line.split(",");
                    if (parts.length != 3) {
                        throw new InvalidCourseException("Malformed line skipped: " + line);
                    }
                    String code = parts[0].trim();
                    String title = parts[1].trim();
                    int units = Integer.parseInt(parts[2].trim());
                    courses.add(new Course(code, title, units));
                    loadedCount++;
                } catch (InvalidCourseException | NumberFormatException e) {
                    System.out.println("Skipping invalid record: " + e.getMessage());
                }
            }
            System.out.println("Loaded " + loadedCount + " course(s) from " + FILE_NAME);
        } catch (IOException e) {
            System.out.println("Error loading file: " + e.getMessage());
        }
    }
}