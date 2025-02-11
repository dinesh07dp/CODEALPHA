import java.util.ArrayList;
import java.util.Scanner;

public class Task1_StudentGradeTracker {
    public static void main(String[] args) {
        // Create an ArrayList to store grades
        ArrayList<Double> grades = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter a student's grade (or type 'done' to finish): ");
            String input = scanner.nextLine();

            // Check if the user wants to finish entering grades
            if (input.equalsIgnoreCase("done")) {
                break;
            }

            try {
                // Convert the input to a double and add it to the grades list
                double grade = Double.parseDouble(input);
                if (grade >= 0 && grade <= 100) { // Check if the grade is within a valid range
                    grades.add(grade);
                } else {
                    System.out.println("Please enter a grade between 0 and 100.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a numeric grade.");
            }
        }

        // Check if any grades were entered
        if (!grades.isEmpty()) {
            // Calculate average, highest, and lowest scores
            double sum = 0;
            double highest = grades.get(0);
            double lowest = grades.get(0);

            for (double grade : grades) {
                sum += grade;
                if (grade > highest) {
                    highest = grade;
                }
                if (grade < lowest) {
                    lowest = grade;
                }
            }

            double average = sum / grades.size();

            // Display the results
            System.out.println("\nNumber of grades entered: " + grades.size());
            System.out.printf("Average grade: %.2f%n", average);
            System.out.printf("Highest grade: %.2f%n", highest);
            System.out.printf("Lowest grade: %.2f%n", lowest);
        } else {
            System.out.println("No grades were entered.");
        }

        scanner.close();
    }
}