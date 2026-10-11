
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

class Employee {
    private final int employeeId;
    private String name;
    private String department;
    private double salary;

    public Employee(int employeeId, String name,
                    String department, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.department = department;
        this.salary = salary;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public double getSalary() {
        return salary;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void display() {
        System.out.println("\nEmployee ID : " + employeeId);
        System.out.println("Name        : " + name);
        System.out.println("Department  : " + department);
        System.out.printf("Salary      : %.2f%n", salary);
    }
}

public class Main {
    static Scanner sc = new Scanner(System.in);

    static Map<Integer, Employee> employees =
            new LinkedHashMap<>();

    static int nextId = 1001;

    public static void main(String[] args) {
        int choice;

        do {
            System.out.println("\n================================");
            System.out.println("   EMPLOYEE MANAGEMENT SYSTEM");
            System.out.println("================================");
            System.out.println("1. Create Employee");
            System.out.println("2. Search Employee");
            System.out.println("3. Update Employee");
            System.out.println("4. List All Employees");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = readInt();

            switch (choice) {
                case 1:
                    createEmployee();
                    break;
                case 2:
                    searchEmployee();
                    break;
                case 3:
                    updateEmployee();
                    break;
                case 4:
                    listEmployees();
                    break;
                case 5:
                    System.out.println(
                            "Thank you for using the system!");
                    break;
                default:
                    System.out.println(
                            "Invalid choice. Try again.");
            }
        } while (choice != 5);

        sc.close();
    }

    static void createEmployee() {
        System.out.println("\n--- Create Employee ---");

        System.out.print("Enter employee name: ");
        String name = sc.nextLine();

        System.out.print("Enter department: ");
        String department = sc.nextLine();

        System.out.print("Enter salary: ");
        double salary = readDouble();

        if (name.trim().isEmpty()
                || department.trim().isEmpty()
                || salary < 0) {
            System.out.println(
                    "Invalid details. Please try again.");
            return;
        }

        Employee emp = new Employee(
                nextId, name, department, salary);

        employees.put(nextId, emp);

        System.out.println("\nEmployee created successfully!");
        System.out.println("Employee ID: " + nextId);

        nextId++;
    }

    static void searchEmployee() {
        System.out.println("\n--- Search Employee ---");

        System.out.print("Enter employee ID: ");
        int id = readInt();

        Employee emp = employees.get(id);

        if (emp != null) {
            emp.display();
        } else {
            System.out.println("Employee not found!");
        }
    }

    static void updateEmployee() {
        System.out.println("\n--- Update Employee ---");

        System.out.print("Enter employee ID: ");
        int id = readInt();

        Employee emp = employees.get(id);

        if (emp == null) {
            System.out.println("Employee not found!");
            return;
        }

        emp.display();

        System.out.print("\nEnter new name: ");
        String name = sc.nextLine();

        System.out.print("Enter new department: ");
        String department = sc.nextLine();

        System.out.print("Enter new salary: ");
        double salary = readDouble();

        if (name.trim().isEmpty()
                || department.trim().isEmpty()
                || salary < 0) {
            System.out.println(
                    "Invalid details. Update cancelled.");
            return;
        }

        emp.setName(name);
        emp.setDepartment(department);
        emp.setSalary(salary);

        System.out.println(
                "Employee updated successfully!");
    }

    static void listEmployees() {
        System.out.println("\n--- Employee List ---");

        if (employees.isEmpty()) {
            System.out.println("No employees available.");
            return;
        }

        for (Employee emp : employees.values()) {
            emp.display();
            System.out.println("------------------------");
        }

        System.out.println(
                "Total Employees: " + employees.size());
    }

    static int readInt() {
        while (true) {
            try {
                int value = Integer.parseInt(sc.nextLine().trim());
                return value;
            } catch (NumberFormatException e) {
                System.out.print(
                        "Enter a valid whole number: ");
            }
        }
    }

    static double readDouble() {
        while (true) {
            try {
                double value = Double.parseDouble(
                        sc.nextLine().trim());

                if (Double.isFinite(value)) {
                    return value;
                }

                System.out.print(
                        "Enter a valid salary: ");
            } catch (NumberFormatException e) {
                System.out.print(
                        "Enter a valid salary: ");
            }
        }
    }
}
