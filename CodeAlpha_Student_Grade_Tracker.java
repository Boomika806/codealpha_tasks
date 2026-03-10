import java.util.ArrayList;
import java.util.Scanner;

class Student {
    String name;
    double grade;

    Student(String name, double grade) {
        this.name = name;
        this.grade = grade;
    }
}

public class CodeAlpha_Student_Grade_Tracker { 

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(System.in)) {
            ArrayList<Student> students = new ArrayList<>();

            System.out.println("--- Student Grade Management System ---");

            while (true) {
                System.out.print("Enter student name (or type 'exit' to finish): ");
                String name = sc.nextLine();

                if (name.equalsIgnoreCase("exit")) {
                    break;
                }

                System.out.print("Enter grade for " + name + ": ");

                try {
                    double grade = Double.parseDouble(sc.nextLine());
                    students.add(new Student(name, grade));
                } catch (NumberFormatException e) {
                    System.out.println("Invalid input! Please enter a numeric grade.");
                }
            }

            if (students.isEmpty()) {
                System.out.println("No data entered.");
            } else {
                displaySummary(students);
            }
        } 
    }

    public static void displaySummary(ArrayList<Student> students) {
        double total = 0;
        double highest = students.get(0).grade;
        double lowest = students.get(0).grade;
        String topStudent = students.get(0).name;
        String bottomStudent = students.get(0).name;

        System.out.println("\n--- Student Report Card ---");
        System.out.printf("%-15s | %-10s\n", "Name", "Grade");
        System.out.println("----------------------------");

        for (Student s : students) {
            System.out.printf("%-15s | %-10.2f\n", s.name, s.grade);
            total += s.grade;

            if (s.grade > highest) {
                highest = s.grade;
                topStudent = s.name;
            }
            if (s.grade < lowest) {
                lowest = s.grade;
                bottomStudent = s.name;
            }
        }

        double average = total / students.size();

        System.out.println("----------------------------");
        System.out.printf("Average Score: %.2f\n", average);
        System.out.printf("Highest Score: %.2f (%s)\n", highest, topStudent);
        System.out.printf("Lowest Score:  %.2f (%s)\n", lowest, bottomStudent);
        System.out.println("-----------------------------");
    }
}

