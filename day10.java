
class Student {
    private String name;
    private int age;
    private String course;
    public Student() {
        this.name = "Unknown";
        this.age = 0;
        this.course = "Unassigned";
    }

    public Student(String name, int age) {
        this.name = name;
        this.age = age;
        this.course = "General Studies";
    }

 
    public Student(String name, int age, String course) {
        this.name = name;
        this.age = age;
        this.course = course;
    }

    
    public void displayDetails() {
        System.out.println("Name: " + name + " | Age: " + age + " | Course: " + course);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("Demonstrating Constructor Overloading:\n");

       
        Student student1 = new Student();
       
        Student student2 = new Student("Alice", 20);
        
        
        Student student3 = new Student("Bob", 22, "Java Programming");

        student1.displayDetails();
        student2.displayDetails();
        student3.displayDetails();
    }
}
