import java.util.*;

class Student {
    int rollNo;
    String name;
    int[] marks = new int[3];
    int total;
    double average;

    Student(int rollNo, String name, int[] marks) {
        this.rollNo = rollNo;
        this.name = name;
        this.marks = marks;
        calculate();
    }

    void calculate() {
        total = 0;
        for (int m : marks) total += m;
        average = total / 3.0;
    }

    void updateMarks(int[] newMarks) {
        this.marks = newMarks;
        calculate();
    }

    void display() {
        System.out.printf("%-10d %-15s %-5d %-5d %-5d %-6d %-6.2f\n",
                rollNo, name, marks[0], marks[1], marks[2], total, average);
    }
}

public class StudentGradeManagement {
    static ArrayList<Student> students = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Student Grade Management System ---");
            System.out.println("1. Add Student");
            System.out.println("2. Update Marks");
            System.out.println("3. Remove Student");
            System.out.println("4. View All Students");
            System.out.println("5. Search Student");
            System.out.println("6. Highest Scorer");
            System.out.println("7. Class Average");
            System.out.println("8. Exit");
            System.out.print("Choose an option: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1 -> addStudent();
                case 2 -> updateMarks();
                case 3 -> removeStudent();
                case 4 -> viewAll();
                case 5 -> searchStudent();
                case 6 -> highestScorer();
                case 7 -> classAverage();
                case 8 -> exitSummary();
                default -> System.out.println("Invalid choice!");
            }
        } while (choice != 8);
    }

    static void addStudent() {
        if (students.size() >= 50) {
            System.out.println("Cannot add more than 50 students!");
            return;
        }
        System.out.print("Enter Roll No: ");
        int roll = sc.nextInt();
        if (findStudent(roll) != null) {
            System.out.println("Roll number already exists!");
            return;
        }
        sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        int[] marks = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter Marks in Subject " + (i + 1) + ": ");
            int m = sc.nextInt();
            if (m < 0 || m > 100) {
                System.out.println("Invalid marks!");
                return;
            }
            marks[i] = m;
        }
        students.add(new Student(roll, name, marks));
        System.out.println("Student added successfully!");
    }

    static void updateMarks() {
        System.out.print("Enter Roll No to update: ");
        int roll = sc.nextInt();
        Student s = findStudent(roll);
        if (s == null) {
            System.out.println("Roll number not found!");
            return;
        }
        int[] newMarks = new int[3];
        for (int i = 0; i < 3; i++) {
            System.out.print("Enter New Marks in Subject " + (i + 1) + ": ");
            int m = sc.nextInt();
            if (m < 0 || m > 100) {
                System.out.println("Invalid marks!");
                return;
            }
            newMarks[i] = m;
        }
        s.updateMarks(newMarks);
        System.out.println("Marks updated successfully!");
    }

    static void removeStudent() {
        System.out.print("Enter Roll No to remove: ");
        int roll = sc.nextInt();
        Student s = findStudent(roll);
        if (s != null) {
            students.remove(s);
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Roll number not found!");
        }
    }

    static void viewAll() {
        if (students.isEmpty()) {
            System.out.println("No students available!");
            return;
        }
        System.out.printf("%-10s %-15s %-5s %-5s %-5s %-6s %-6s\n",
                "RollNo", "Name", "M1", "M2", "M3", "Total", "Avg");
        for (Student s : students) {
            s.display();
        }
    }

    static void searchStudent() {
        System.out.print("Enter Roll No to search: ");
        int roll = sc.nextInt();
        Student s = findStudent(roll);
        if (s != null) {
            System.out.printf("%-10s %-15s %-5s %-5s %-5s %-6s %-6s\n",
                    "RollNo", "Name", "M1", "M2", "M3", "Total", "Avg");
            s.display();
        } else {
            System.out.println("Roll number not found!");
        }
    }

    static void highestScorer() {
        if (students.isEmpty()) {
            System.out.println("No students available!");
            return;
        }
        Student top = students.get(0);
        for (Student s : students) {
            if (s.total > top.total) top = s;
        }
        System.out.println("Highest Scorer:");
        top.display();
    }

    static void classAverage() {
        if (students.isEmpty()) {
            System.out.println("No students available!");
            return;
        }
        double sum = 0;
        for (Student s : students) sum += s.average;
        System.out.printf("Class Average: %.2f\n", (sum / students.size()));
    }

    static void exitSummary() {
        System.out.println("Exiting... Total Students: " + students.size());
        classAverage();
    }

    static Student findStudent(int roll) {
        for (Student s : students) if (s.rollNo == roll) return s;
        return null;
    }
}
