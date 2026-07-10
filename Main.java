import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);
    private static final CourseManager manager = new CourseManager();

    public static void main(String[] args) {
        System.out.println("====================================");
        System.out.println(" STUDENT COURSE MANAGEMENT SYSTEM");
        System.out.println("====================================");
        runMenu();
    }

    private static void runMenu() {
        System.out.println("\n1. Add Course");
        System.out.println("2. View All Courses");
        System.out.println("3. Search Course by Code");
        System.out.println("4. Compute Total Units");
        System.out.println("5. Save to File");
        System.out.println("6. Load from File");
        System.out.println("7. Exit Program");
        System.out.print("Enter your choice (1-7): ");

        int choice = readIntSafely();

        switch (choice) {
            case 1 -> handleAddCourse();
            case 2 -> manager.displayAllCourses();
            case 3 -> handleSearchCourse();
            case 4 -> System.out.println("Total units recorded: " + manager.computeTotalUnits());
            case 5 -> manager.saveToFile();
            case 6 -> manager.loadFromFile();
            case 7 -> {
                System.out.println("Exiting program. Goodbye!");
                return; // base case: stops the recursion
            }
            default -> System.out.println("Invalid choice. Please select a number between 1 and 7.");
        }

        runMenu(); // recursive call: show the menu again
    }

    private static void handleAddCourse() {
        try {
            System.out.print("Enter course code (e.g., COS201): ");
            String code = scanner.nextLine();

            System.out.print("Enter course title: ");
            String title = scanner.nextLine();

            System.out.print("Enter course units: ");
            int units = readIntSafely();

            manager.addCourse(code, title, units);
        } catch (InvalidCourseException e) {
            System.out.println("Error adding course: " + e.getMessage());
        }
    }

    private static void handleSearchCourse() {
        System.out.print("Enter course code to search: ");
        String code = scanner.nextLine();
        manager.searchCourse(code);
    }

    private static int readIntSafely() {
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number, please try again: ");
            }
        }
    }
}