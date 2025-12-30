import java.io.*;
import java.util.*;

// ====================== STUDENT CLASS ======================
class Student {
    // Student attributes
    private int rollNumber;
    private String name;
    private String grade;
    private int age;

    // Constructor
    public Student(int rollNumber, String name, String grade, int age) {
        this.rollNumber = rollNumber;
        this.name = name;
        this.grade = grade;
        this.age = age;
    }

    // Getter
    public int getRollNumber() {
        return rollNumber;
    }

    // Setters (used while editing student details)
    public void setName(String name) {
        this.name = name;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // Convert student data into string for file storage
    public String toFileString() {
        return rollNumber + "," + name + "," + grade + "," + age;
    }

    // Convert file string back to Student object
    public static Student fromFileString(String line) {
        String[] data = line.split(",");
        return new Student(
                Integer.parseInt(data[0]),
                data[1],
                data[2],
                Integer.parseInt(data[3])
        );
    }

    // Display student details
    public void display() {
        System.out.println("Roll Number : " + rollNumber);
        System.out.println("Name        : " + name);
        System.out.println("Grade       : " + grade);
        System.out.println("Age         : " + age);
        System.out.println("--------------------------------");
    }
}

// ================= STUDENT MANAGEMENT SYSTEM =================
class StudentManagementSystem {
    private ArrayList<Student> students = new ArrayList<>();
    private final String FILE_NAME = "students.txt";

    // Constructor – loads data from file
    public StudentManagementSystem() {
        loadFromFile();
    }

    // Add student
    public void addStudent(Student s) {
        students.add(s);
        saveToFile();
        System.out.println("Student added successfully!");
    }

    // Remove student by roll number
    public void removeStudent(int roll) {
        boolean removed = students.removeIf(s -> s.getRollNumber() == roll);
        if (removed) {
            saveToFile();
            System.out.println("Student removed successfully!");
        } else {
            System.out.println("Student not found!");
        }
    }

    // Search student
    public Student searchStudent(int roll) {
        for (Student s : students) {
            if (s.getRollNumber() == roll)
                return s;
        }
        return null;
    }

    // Display all students
    public void displayAllStudents() {
        if (students.isEmpty()) {
            System.out.println("No student records available.");
            return;
        }
        for (Student s : students) {
            s.display();
        }
    }

    // Save data to file
    public void saveToFile() {
        try (PrintWriter pw = new PrintWriter(new FileWriter(FILE_NAME))) {
            for (Student s : students) {
                pw.println(s.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving data.");
        }
    }

    // Load data from file
    private void loadFromFile() {
        File file = new File(FILE_NAME);
        if (!file.exists()) return;

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                students.add(Student.fromFileString(line));
            }
        } catch (IOException e) {
            System.out.println("Error loading data.");
        }
    }
}

// ====================== MAIN CLASS ======================
public class StudentManagementApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentManagementSystem sms = new StudentManagementSystem();
        int choice;

        do {
            System.out.println("\n===== STUDENT MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Student");
            System.out.println("2. Edit Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Remove Student");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            while (!sc.hasNextInt()) {
                System.out.print("Invalid input. Enter a number: ");
                sc.next();
            }
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Roll Number: ");
                    int roll = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Name: ");
                    String name = sc.nextLine();

                    System.out.print("Grade: ");
                    String grade = sc.nextLine();

                    System.out.print("Age: ");
                    int age = sc.nextInt();

                    if (name.isEmpty() || grade.isEmpty()) {
                        System.out.println("Fields cannot be empty!");
                        break;
                    }

                    sms.addStudent(new Student(roll, name, grade, age));
                    break;

                case 2:
                    System.out.print("Enter Roll Number to Edit: ");
                    roll = sc.nextInt();
                    sc.nextLine();

                    Student s = sms.searchStudent(roll);
                    if (s == null) {
                        System.out.println("Student not found!");
                        break;
                    }

                    System.out.print("New Name: ");
                    s.setName(sc.nextLine());

                    System.out.print("New Grade: ");
                    s.setGrade(sc.nextLine());

                    System.out.print("New Age: ");
                    s.setAge(sc.nextInt());

                    sms.saveToFile();
                    System.out.println("Student updated successfully!");
                    break;

                case 3:
                    System.out.print("Enter Roll Number: ");
                    roll = sc.nextInt();

                    s = sms.searchStudent(roll);
                    if (s != null)
                        s.display();
                    else
                        System.out.println("Student not found!");
                    break;

                case 4:
                    sms.displayAllStudents();
                    break;

                case 5:
                    System.out.print("Enter Roll Number to Remove: ");
                    roll = sc.nextInt();
                    sms.removeStudent(roll);
                    break;

                case 6:
                    System.out.println("Exiting application...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        } while (choice != 6);

        sc.close();
    }
}

