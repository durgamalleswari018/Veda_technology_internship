class Employee {
    String name;
    int id;
    double salary;

    Employee(String name, int id, double salary) {
        this.name = name;
        this.id = id;
        this.salary = salary;
    }

    void displayDetails() {
        System.out.println("Employee ID : " + id);
        System.out.println("Name        : " + name);
        System.out.println("Salary      : Rs. " + salary);
    }

    void work() {
        System.out.println(name + " is working.");
    }
}

class Developer extends Employee {
    String programmingLanguage;

    Developer(String name, int id, double salary,
              String programmingLanguage) {
        super(name, id, salary);
        this.programmingLanguage = programmingLanguage;
    }

    @Override
    void work() {
        System.out.println(name + " is developing software using "
                + programmingLanguage + ".");
    }
}

class Manager extends Employee {
    int teamSize;

    Manager(String name, int id, double salary, int teamSize) {
        super(name, id, salary);
        this.teamSize = teamSize;
    }

    @Override
    void work() {
        System.out.println(name + " is managing a team of "
                + teamSize + " employees.");
    }
}

class Intern extends Employee {
    String department;

    Intern(String name, int id, double salary, String department) {
        super(name, id, salary);
        this.department = department;
    }

    @Override
    void work() {
        System.out.println(name + " is learning in the "
                + department + " department.");
    }
}

public class Main {
    public static void main(String[] args) {
        Employee[] employees = {
            new Developer("Arjun", 101, 45000, "Java"),
            new Manager("Priya", 102, 65000, 5),
            new Intern("Rahul", 103, 12000, "Data Analytics")
        };

        for (Employee employee : employees) {
            System.out.println("\n===== EMPLOYEE DETAILS =====");
            employee.displayDetails();
            employee.work();
        }
    }
}