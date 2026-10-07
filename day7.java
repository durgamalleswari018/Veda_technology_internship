import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PrimeAnalysis {

    /**
     * Checks if a single number is prime.
     * Optimization: Tests divisors up to the square root of the number.
     * Handles numbers less than 2.
     */
    public static boolean isPrime(int number) {
        // Numbers less than 2 are not prime
        if (number < 2) {
            return false;
        }
        
        // Check for divisors up to the square root of the number
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false; // Found a divisor, not prime
            }
        }
        
        return true; // No divisors found, it is prime
    }

    /**
     * Generates a list of all prime numbers within a specified range [start, end].
     */
    public static List<Integer> generatePrimesInRange(int start, int end) {
        List<Integer> primeList = new ArrayList<>();
        
        for (int i = start; i <= end; i++) {
            if (isPrime(i)) {
                primeList.add(i);
            }
        }
        
        return primeList;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Prime Number Analysis Program ===");
        
        // 1. Prime Checker Demo
        System.out.print("\nEnter a number to check if it is prime: ");
        int checkNum = scanner.nextInt();
        if (isPrime(checkNum)) {
            System.out.println(checkNum + " is a PRIME number.");
        } else {
            System.out.println(checkNum + " is NOT a prime number.");
        }
        
        // 2 & 3. Prime Generator & Range Analysis Demo
        System.out.println("\n--- Range-Based Prime Generator ---");
        System.out.print("Enter the start of the range: ");
        int start = scanner.nextInt();
        System.out.print("Enter the end of the range: ");
        int end = scanner.nextInt();
        
        if (start > end) {
            System.out.println("Invalid range! Start value cannot be greater than end value.");
        } else {
            List<Integer> primes = generatePrimesInRange(start, end);
            
            System.out.println("\nPrime numbers between " + start + " and " + end + " are:");
            System.out.println(primes);
            System.out.println("Total prime numbers found: " + primes.size());
        }
        
        scanner.close();
    }
}
