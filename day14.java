// Abstract class representing a generic shape
abstract class Shape {
    // Abstract methods that must be implemented by all concrete subclasses
    public abstract double calculateArea();
    public abstract double calculatePerimeter();
}

// Concrete subclass for a Circle
class Circle extends Shape {
    private double radius;

    public Circle(double radius) {
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

// Concrete subclass for a Rectangle
class Rectangle extends Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}

// Concrete subclass for a Triangle
class Triangle extends Shape {
    private double sideA;
    private double sideB;
    private double sideC;

    // Constructor for a triangle when all three sides are known
    public Triangle(double sideA, double sideB, double sideC) {
        this.sideA = sideA;
        this.sideB = sideB;
        this.sideC = sideC;
    }

    @Override
    public double calculateArea() {
        // Using Heron's Formula to calculate area from 3 sides
        double s = calculatePerimeter() / 2;
        return Math.sqrt(s * (s - sideA) * (s - sideB) * (s - sideC));
    }

    @Override
    public double calculatePerimeter() {
        return sideA + sideB + sideC;
    }
}

// Main class to test the calculator functionality
public class Main {
    public static void main(String[] args) {
        // Instantiate different shapes
        Shape circle = new Circle(5.0);
        Shape rectangle = new Rectangle(4.0, 6.0);
        Shape triangle = new Triangle(3.0, 4.0, 5.0);

        // Display results for Circle
        System.out.println("--- Circle (Radius: 5.0) ---");
        System.out.printf("Area: %.2f%n", circle.calculateArea());
        System.out.printf("Perimeter: %.2f%n%n", circle.calculatePerimeter());

        // Display results for Rectangle
        System.out.println("--- Rectangle (4.0 x 6.0) ---");
        System.out.printf("Area: %.2f%n", rectangle.calculateArea());
        System.out.printf("Perimeter: %.2f%n%n", rectangle.calculatePerimeter());

        // Display results for Triangle
        System.out.println("--- Triangle (Sides: 3.0, 4.0, 5.0) ---");
        System.out.printf("Area: %.2f%n", triangle.calculateArea());
        System.out.printf("Perimeter: %.2f%n", triangle.calculatePerimeter());
    }
}
