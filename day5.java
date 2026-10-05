import java.util.Scanner;

public class MultiplicationTableGenerator {

    static void generateTable(int number) {
        System.out.println("\n📌 Multiplication Table of " + number);
        System.out.println("----------------------------");

        for (int i = 1; i <= 10; i++) {
            System.out.println(number + " × " + i + " = " + (number * i));
        }
    }

    static void generateRangeTables(int start, int end) {
        System.out.println("\n📚 Multiplication Tables from " + start + " to " + end);
        System.out.println("============================");

        for (int number = start; number <= end; number++) {
            System.out.println("\n📌 Table of " + number);
            System.out.println("----------------------------");

            for (int i = 1; i <= 10; i++) {
                System.out.println(number + " × " + i + " = " + (number * i));
            }
        }
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.println("=================================");
        System.out.println("   ✨ MULTIPLICATION GENERATOR ✨");
        System.out.println("=================================");

        System.out.println("\n1️⃣ Generate Single Table");
        System.out.println("2️⃣ Generate Tables for a Range");

        System.out.print("\n👉 Enter your choice: ");
        int choice = sc.nextInt();

        if (choice == 1) {

            System.out.print("🔢 Enter a number: ");
            int number = sc.nextInt();

            generateTable(number);

        } else if (choice == 2) {

            System.out.print("🔢 Enter starting number: ");
            int start = sc.nextInt();

            System.out.print("🔢 Enter ending number: ");
            int end = sc.nextInt();

            if (start <= end) {
                generateRangeTables(start, end);
            } else {
                System.out.println("❌ Starting number must be less than or equal to ending number.");
            }

        } else {
            System.out.println("❌ Invalid choice!");
        }

        System.out.println("\n✅ Program completed successfully!");
        sc.close();
    }
}