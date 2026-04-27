import java.util.*;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("====== Student Grade Calculator ======");

        // Input: number of subjects
        System.out.print("Enter number of subjects: ");
        int subjects = sc.nextInt();

        if (subjects <= 0) {
            System.out.println("Invalid number of subjects!");
            return;
        }

        double total = 0;

        // Input marks
        for (int i = 1; i <= subjects; i++) {
            System.out.print("Enter marks for subject " + i + " (out of 100): ");
            double marks = sc.nextDouble();

            if (marks < 0 || marks > 100) {
                System.out.println(" Invalid marks! Enter between 0 and 100.");
                i--; // retry same subject
                continue;
            }

            total += marks;
        }

        // Calculate average
        double average = total / subjects;

        // Grade calculation
        char grade;

        if (average >= 90) {
            grade = 'A';
        } else if (average >= 75) {
            grade = 'B';
        } else if (average >= 60) {
            grade = 'C';
        } else if (average >= 50) {
            grade = 'D';
        } else {
            grade = 'F';
        }

        // Display results
        System.out.println("\n====== Result ======");
        System.out.printf("Total Marks: %.2f\n", total);
        System.out.printf("Average Percentage: %.2f%%\n", average);
        System.out.println("Grade: " + grade);

        // Extra message based on grade
        switch (grade) {
            case 'A':
                System.out.println(" Excellent performance!");
                break;
            case 'B':
                System.out.println(" Good job!");
                break;
            case 'C':
                System.out.println(" Average performance.");
                break;
            case 'D':
                System.out.println(" Need improvement.");
                break;
            case 'F':
                System.out.println(" Failed. Work harder!");
                break;
        }

        sc.close();
    }
}