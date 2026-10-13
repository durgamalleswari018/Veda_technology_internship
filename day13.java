class Parent {
    void display() {
        System.out.println("This is the display() method from the Parent class.");
    }
}
class Child1 extends Parent {
    @Override
    void display() {
        System.out.println("This is the display() method from Child class 1.");
    }
}
class Child2 extends Parent {
    @Override
    void display() {
        System.out.println("This is the display() method from Child class 2.");
    }
}
public class PolymorphismDemo {
    public static void main(String[] args) {
        Parent ref1 = new Child1();
        Parent ref2 = new Child2();
        System.out.println("--- Executing Method via Parent Reference 1 ---");
        ref1.display(); 
        System.out.println("\n--- Executing Method via Parent Reference 2 ---");
        ref2.display(); 
    }
}
