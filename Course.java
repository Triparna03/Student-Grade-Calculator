import java.util.ArrayList;
import java.util.List;
import java.util.OptionalDouble;

public class Course {
    private String courseName;
    private String courseCode;
    private List<Student> students;

    public Course(String courseName, String courseCode) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.students = new ArrayList<>();
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        if (student != null) {
            students.add(student);
            System.out.println(" Student " + student.getName() + " added to " + courseName + " (" + courseCode + ").");
        }
    }

    public boolean removeStudent(int studentId) {
        return students.removeIf(s -> s.getStudentId() == studentId);
    }

    public Student getStudentById(int studentId) {
        return students.stream()
                .filter(s -> s.getStudentId() == studentId)
                .findFirst()
                .orElse(null);
    }

    public double getCourseAverage() {
        if (students.isEmpty()) {
            return 0.0;
        }

        OptionalDouble average = students.stream()
                .mapToDouble(Student::calculateAverage)
                .average();

        return average.orElse(0.0);
    }

    public double findHighestGrade() {
        if (students.isEmpty()) {
            return -1.0;
        }
        return students.stream()
                .flatMap(s -> s.getGrades().stream())
                .mapToDouble(Double::doubleValue)
                .max()
                .orElse(-1.0);
    }

    public double findLowestGrade() {
        if (students.isEmpty()) {
            return -1.0;
        }

        return students.stream()
                .flatMap(s -> s.getGrades().stream())
                .mapToDouble(Double::doubleValue)
                .min()
                .orElse(-1.0);
    }

    public void displayAllStudents() {
        System.out.println("\n*** Course Roster for: " + courseName + " (" + courseCode + ") ***");

        System.out.println(
                "+------------+----------------------+--------------------------------+----------+--------------+");
        System.out.println(
                "| ID         | Name                 | Grades                         | Average  | Letter Grade |");
        System.out.println(
                "+------------+----------------------+--------------------------------+----------+--------------+");

        if (students.isEmpty()) {
            System.out.println("| *** No students enrolled in this course. *** |");
        } else {
            for (Student student : students) {
                System.out.println(student.toString());
            }
        }
        System.out.println(
                "+------------+----------------------+--------------------------------+----------+--------------+");
    }
}
