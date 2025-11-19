import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    private static Course course;
    private static Scanner scanner = new Scanner(System.in);
    private static int nextStudentId = 101;

    public static void main(String[] args) {

        course = new Course("Advanced Java Programming", "CS401");
        int choice = 0;

        System.out.println("Welcome to the " + course.getCourseName() + " Grade Calculator!");

        do {
            displayMenu();
            try {
                System.out.print("Enter your choice: ");
                choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        addStudent();
                        break;
                    case 2:
                        addGradeToStudent();
                        break;
                    case 3:
                        viewStudentDetails();
                        break;
                    case 4:
                        viewCourseStatistics();
                        break;
                    case 5:
                        course.displayAllStudents();
                        break;
                    case 6:
                        System.out.println("Thank you for using the Grade Calculator. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid choice. Please select a number between 1 and 6.");
                }
            } catch (InputMismatchException e) {
                System.out.println(" Invalid input. Please enter a number for your choice.");
                scanner.nextLine();
                choice = 0;
            } catch (Exception e) {
                System.out.println("An unexpected error occurred: " + e.getMessage());
            }
            System.out.println("\n" + "=".repeat(40) + "\n");
        } while (choice != 6);

        scanner.close();
    }

    private static void displayMenu() {
        System.out.println("\n=== Student Grade Calculator Menu ===");
        System.out.println("1. Add New Student");
        System.out.println("2. Add Grade to Student");
        System.out.println("3. View Student Details and Grades");
        System.out.println("4. View Course Statistics (Average, Highest, Lowest)");
        System.out.println("5. List All Students");
        System.out.println("6. Exit");
    }

    private static void addStudent() {
        System.out.println("\n--- Add New Student ---");
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();

        Student newStudent = new Student(nextStudentId, name);
        course.addStudent(newStudent);

        System.out.println(" New Student successfully added! ID: " + nextStudentId);
        nextStudentId++;
    }

    private static void addGradeToStudent() {
        System.out.println("\n--- Add Grade ---");
        System.out.print("Enter Student ID: ");

        try {
            int studentId = scanner.nextInt();
            scanner.nextLine();

            Student student = course.getStudentById(studentId);

            if (student == null) {
                System.out.println("Student with ID " + studentId + " not found.");
                return;
            }

            System.out.print("Enter grade (0-100): ");
            double grade = scanner.nextDouble();
            scanner.nextLine();

            if (grade < 0 || grade > 100) {
                System.out.println("Invalid grade. Grade must be between 0 and 100.");
                return;
            }

            student.addGrade(grade);
            System.out.println("Grade " + grade + " added for " + student.getName() + ".");

        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter a number for the ID and grade.");
            scanner.nextLine();
        }
    }

    private static void viewStudentDetails() {
        System.out.println("\n--- View Student Details ---");
        System.out.print("Enter Student ID: ");

        try {
            int studentId = scanner.nextInt();
            scanner.nextLine();

            Student student = course.getStudentById(studentId);

            if (student == null) {
                System.out.println("Student with ID " + studentId + " not found.");
                return;
            }

            System.out.println("\n*** Student Details ***");
            System.out.println("Name:         " + student.getName());
            System.out.println("ID:           " + student.getStudentId());
            System.out.println("Grades:       " + student.getGrades());
            System.out.println("Average:      " + String.format("%.2f", student.calculateAverage()));
            System.out.println("Letter Grade: " + student.getLetterGrade() + " (" +
                    (student.getGrades().isEmpty() ? "N/A" : student.calculateAverage() + "%)"));

        } catch (InputMismatchException e) {
            System.out.println(" Invalid input. Please enter a number for the ID.");
            scanner.nextLine();
        }
    }

    private static void viewCourseStatistics() {
        System.out.println("\n--- Course Statistics: " + course.getCourseCode() + " ---");

        if (course.getStudents().isEmpty()) {
            System.out.println(" No students are currently enrolled to calculate statistics.");
            return;
        }

        double courseAverage = course.getCourseAverage();
        double highestGrade = course.findHighestGrade();
        double lowestGrade = course.findLowestGrade();

        System.out.println("Total Students:    " + course.getStudents().size());
        System.out.println("----------------------------------------");

        System.out.println("Overall Course Average: " + String.format("%.2f", courseAverage));
        System.out.println(
                "Highest Grade Recorded: " + (highestGrade == -1.0 ? "N/A" : String.format("%.2f", highestGrade)));
        System.out.println(
                "Lowest Grade Recorded:  " + (lowestGrade == -1.0 ? "N/A" : String.format("%.2f", lowestGrade)));
        System.out.println("Course Letter Grade:    " + GradeCalculator.getLetterGrade(courseAverage));
    }
}
