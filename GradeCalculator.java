import java.util.List;
import java.util.Collections;

public class GradeCalculator {

    private GradeCalculator() {
    }

    public static String getLetterGrade(double average) {
        if (average >= 90)
            return "A";
        if (average >= 80)
            return "B";
        if (average >= 70)
            return "C";
        if (average >= 60)
            return "D";
        return "F";
    }

    public static double calculateCGPAFromPercentage(double percentage) {
        if (percentage >= 90)
            return 4.0;
        if (percentage >= 85)
            return 3.7;
        if (percentage >= 80)
            return 3.3;
        if (percentage >= 75)
            return 3.0;
        if (percentage >= 70)
            return 2.7;
        if (percentage >= 65)
            return 2.3;
        if (percentage >= 60)
            return 2.0;
        return 0.0;
    }

    public static double calculateAverage(List<Double> grades) {
        if (grades == null || grades.isEmpty())
            return 0.0;
        double sum = 0;
        for (double g : grades)
            sum += g;
        return sum / grades.size();
    }

    public static double findHighestGrade(List<Double> grades) {
        if (grades == null || grades.isEmpty())
            return -1.0;
        return Collections.max(grades);
    }

    public static double findLowestGrade(List<Double> grades) {
        if (grades == null || grades.isEmpty())
            return -1.0;
        return Collections.min(grades);
    }

    public static double calculateGPAFromPercentage(double grade) {
        throw new UnsupportedOperationException("Unimplemented method 'calculateGPAFromPercentage'");
    }
}
