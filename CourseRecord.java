public class CourseRecord {
    private String courseName;
    private String courseCode;
    private double creditHours;
    private double grade;

    public CourseRecord(String courseName, String courseCode, double creditHours, double grade) {
        this.courseName = courseName;
        this.courseCode = courseCode;
        this.creditHours = creditHours;
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public double getCreditHours() {
        return creditHours;
    }

    public double getGrade() {
        return grade;
    }

    public double getGradePoint() {
        return GradeCalculator.calculateGPAFromPercentage(grade);
    }

    public static class GradeCalculator {
        public static double calculateGPAFromPercentage(double percentage) {
            if (percentage >= 90.0) {
                return 4.0;
            }
            if (percentage >= 80.0) {
                return 3.0;
            }
            if (percentage >= 70.0) {
                return 2.0;
            }
            if (percentage >= 60.0) {
                return 1.0;
            }
            return 0.0;
        }
    }
}
