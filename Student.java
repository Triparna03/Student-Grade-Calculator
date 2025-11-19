import java.util.ArrayList;
import java.util.List;

public class Student {
    private int studentId;
    private String name;
    private List<Double> grades;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
        this.grades = new ArrayList<>();
    }

    public int getStudentId() {
        return studentId;
    }

    public String getName() {
        return name;
    }

    public List<Double> getGrades() {
        return new ArrayList<>(grades);
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addGrade(double grade) {
        if (grade >= 0 && grade <= 100) {
            this.grades.add(grade);
        } else {
            System.err.println("Error: Grade must be between 0 and 100.");
        }
    }

    public double calculateAverage() {
        return GradeCalculator.calculateAverage(this.grades);
    }

    public String getLetterGrade() {
        double average = calculateAverage();
        return GradeCalculator.getLetterGrade(average);
    }

    @Override
    public String toString() {
        double avg = calculateAverage();
        String letterGrade = getLetterGrade();

        return String.format(
                "| %-10d | %-20s | %-30s | %-8.2f | %-12s |",
                studentId, name, grades.toString(), avg, letterGrade);
    }
}
