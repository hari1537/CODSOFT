import java.util.*;
import java.io.*;

// Student Class
class Student implements Serializable {
    private String name;
    private int rollNo;
    private String grade;

    public Student(String name, int rollNo, String grade) {
        this.name = name;
        this.rollNo = rollNo;
        this.grade = grade;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void display() {
        System.out.println("Roll No: " + rollNo + ", Name: " + name + ", Grade: " + grade);
    }
}

// Management System Class
class StudentManagementSystem {
    private List<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.dat";

    // Add Student
    public void addStudent(Student s) {
        students.add(s);
        System.out.println(" Student added successfully!");
    }

    // Remove Student
    public void removeStudent(int rollNo) {
        boolean found = false;
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                students.remove(s);
                System.out.println(" Student removed successfully!");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("Student not found!");
        }
    }

    // Search Student
    public void searchStudent(int rollNo) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                System.out.println(" Student Found:");
                s.display();
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Display All Students
    public void displayAll() {
        if (students.isEmpty()) {
            System.out.println(" No students available.");
        } else {
            System.out.println("\n Student List:");
            for (Student s : students) {
                s.display();
            }
        }
    }

    // Edit Student
    public void editStudent(int rollNo, String newName, String newGrade) {
        for (Student s : students) {
            if (s.getRollNo() == rollNo) {
                s.setName(newName);
                s.setGrade(newGrade);
                System.out.println(" Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found!");
    }

    // Save to File
    public void saveToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(students);
            System.out.println(" Data saved successfully!");
        } catch (IOException e) {
            System.out.println(" Error saving data!");
        }
    }

    // Load from File
    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            students = (List<Student>) ois.readObject();
            System.out.println(" Data loaded successfully!");
        } catch (Exception e) {
            System.out.println(" No previous data found.");
        }
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();

        sms.loadFromFile();

        int choice;

        do {
            System.out.println("\n====== Student Management System ======");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Edit Student");
            System.out.println("6. Save Data");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    sc.nextLine(); // clear buffer
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter Roll No: ");
                    int roll = sc.nextInt();

                    sc.nextLine();
                    System.out.print("Enter Grade: ");
                    String grade = sc.nextLine();

                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println(" Fields cannot be empty!");
                    } else {
                        sms.addStudent(new Student(name, roll, grade));
                    }
                    break;

                case 2:
                    System.out.print("Enter Roll No to remove: ");
                    sms.removeStudent(sc.nextInt());
                    break;

                case 3:
                    System.out.print("Enter Roll No to search: ");
                    sms.searchStudent(sc.nextInt());
                    break;

                case 4:
                    sms.displayAll();
                    break;

                case 5:
                    System.out.print("Enter Roll No to edit: ");
                    int r = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter New Name: ");
                    String newName = sc.nextLine();

                    System.out.print("Enter New Grade: ");
                    String newGrade = sc.nextLine();

                    sms.editStudent(r, newName, newGrade);
                    break;

                case 6:
                    sms.saveToFile();
                    break;

                case 7:
                    sms.saveToFile();
                    System.out.println(" Exiting... Data saved!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 7);

        sc.close();
    }
}