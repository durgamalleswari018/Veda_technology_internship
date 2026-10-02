import java.util.Scanner;

public class StudentGradeCalculator {

    static int calculateTotal(int[] marks) {
        int total = 0;

        for (int mark : marks) {
            total += mark;
        }

        return total;
    }

    static double calculatePercentage(int total, int subjects) {
        return (double) total / (subjects * 100) * 100;
    }

    static String calculateGrade(double percentage) {
        if (percentage >= 90) {
            return "A+";
        } else if (percentage >= 80) {
            return "A";
        } else if (percentage >= 70) {
            return "B";
        } else if (percentage >= 60) {
            return "C";
        } else if (percentage >= 50) {
            return "D";
        } else {
            return "F";
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int subjects = 5;
        int[] marks = new int[subjects];

        System.out.println("Student Grade Calculator");
        System.out.println("------------------------");

        for (int i = 0; i < subjects; i++) {

            do {
                System.out.print("Enter marks for Subject " + (i + 1) + " (0-100): ");
                marks[i] = sc.nextInt();

                if (marks[i] < 0 || marks[i] > 100) {
                    System.out.println("Invalid marks! Please enter 0-100.");
                }

            } while (marks[i] < 0 || marks[i] > 100);
        }

        int total = calculateTotal(marks);
        double percentage = calculatePercentage(total, subjects);
        String grade = calculateGrade(percentage);

        System.out.println("\n----- Student Result -----");

        for (int i = 0; i < subjects; i++) {
            System.out.println("Subject " + (i + 1) + " Marks: " + marks[i]);
        }

        System.out.println("Total Marks: " + total + "/" + (subjects * 100));
        System.out.printf("Percentage: %.2f%%\n", percentage);
        System.out.println("Grade: " + grade);

        sc.close();
    }
}