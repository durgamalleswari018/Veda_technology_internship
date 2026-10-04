import java.util.Scanner;

public class StudentManagementSystem {

    static Scanner sc = new Scanner(System.in);

    static int[] studentIds = new int[100];
    static String[] studentNames = new String[100];
    static int[] studentAges = new int[100];

    static int count = 0;

    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("     STUDENT MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Add Student");
            System.out.println("2. Search Student");
            System.out.println("3. Update Student");
            System.out.println("4. Display Students");
            System.out.println("5. Exit");
            System.out.println("================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;

                case 2:
                    searchStudent();
                    break;

                case 3:
                    updateStudent();
                    break;

                case 4:
                    displayStudents();
                    break;

                case 5:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 5);

        sc.close();
    }

    // Add Student
    static void addStudent() {

        if (count >= studentIds.length) {
            System.out.println("Student storage is full.");
            return;
        }

        System.out.print("Enter Student ID: ");
        int id = sc.nextInt();

        if (findStudent(id) != -1) {
            System.out.println("Student ID already exists.");
            return;
        }

        sc.nextLine();

        System.out.print("Enter Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Student Age: ");
        int age = sc.nextInt();

        studentIds[count] = id;
        studentNames[count] = name;
        studentAges[count] = age;

        count++;

        System.out.println("Student added successfully!");
    }

    // Search Student
    static void searchStudent() {

        System.out.print("Enter Student ID to search: ");
        int id = sc.nextInt();

        int index = findStudent(id);

        if (index == -1) {
            System.out.println("Student ID not found.");
        } else {
            System.out.println("\nStudent Found!");
            System.out.println("Student ID   : " + studentIds[index]);
            System.out.println("Student Name : " + studentNames[index]);
            System.out.println("Student Age  : " + studentAges[index]);
        }
    }

    // Update Student
    static void updateStudent() {

        System.out.print("Enter Student ID to update: ");
        int id = sc.nextInt();

        int index = findStudent(id);

        if (index == -1) {
            System.out.println("Student ID not found.");
            return;
        }

        sc.nextLine();

        System.out.print("Enter New Student Name: ");
        String name = sc.nextLine();

        System.out.print("Enter New Student Age: ");
        int age = sc.nextInt();

        studentNames[index] = name;
        studentAges[index] = age;

        System.out.println("Student updated successfully!");
    }

    // Display Students
    static void displayStudents() {

        if (count == 0) {
            System.out.println("No student records available.");
            return;
        }

        System.out.println("\n==============================================");
        System.out.println("              STUDENT RECORDS");
        System.out.println("==============================================");

        for (int i = 0; i < count; i++) {
            System.out.println("Student ID   : " + studentIds[i]);
            System.out.println("Student Name : " + studentNames[i]);
            System.out.println("Student Age  : " + studentAges[i]);
            System.out.println("----------------------------------------------");
        }
    }

    // Find Student
    static int findStudent(int id) {

        for (int i = 0; i < count; i++) {
            if (studentIds[i] == id) {
                return i;
            }
        }

        return -1;
    }
}