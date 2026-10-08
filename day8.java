import java.util.Scanner;

// --- ACCOUNT LOGIC CLASS (Encapsulation) ---
class ATM {
    private double balance;
    private final String correctPin;
    private int pinAttempts;
    private static final int MAX_ATTEMPTS = 3;

    public ATM(double initialBalance, String pin) {
        this.balance = initialBalance;
        this.correctPin = pin;
        this.pinAttempts = 0;
    }

    public boolean verifyPin(String inputPin) {
        if (isLocked()) {
            return false;
        }
        if (this.correctPin.equals(inputPin)) {
            pinAttempts = 0; // Reset attempts on successful login
            return true;
        } else {
            pinAttempts++;
            return false;
        }
    }

    public boolean isLocked() {
        return pinAttempts >= MAX_ATTEMPTS;
    }

    public int getRemainingAttempts() {
        return MAX_ATTEMPTS - pinAttempts;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.printf("Successfully deposited $%.2f%n", amount);
        } else {
            System.out.println("Invalid deposit amount.");
        }
    }

    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount.");
            return false;
        }
        if (amount > balance) {
            System.out.println("Transaction Failed: Insufficient balance.");
            return false;
        }
        balance -= amount;
        System.out.printf("Successfully withdrew $%.2f%n", amount);
        return true;
    }
}

// --- USER INTERACTION CLASS (Main) ---
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        // Initialize ATM with $1000 balance and default PIN "1234"
        ATM atm = new ATM(1000.00, "1234"); 

        System.out.println("=== Welcome to the ATM Simulation ===");

        // PIN Authentication Loop
        boolean authenticated = false;
        while (!authenticated) {
            if (atm.isLocked()) {
                System.out.println("Your card has been blocked due to too many incorrect PIN attempts.");
                return;
            }

            System.out.print("Enter your 4-digit PIN: ");
            String inputPin = scanner.nextLine();

            if (atm.verifyPin(inputPin)) {
                authenticated = true;
                System.out.println("\nLogin Successful!");
            } else {
                System.out.printf("Incorrect PIN. Remaining attempts: %d%n%n", atm.getRemainingAttempts());
            }
        }

        // Main Menu Loop
        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Choose an option (1-4): ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    System.out.printf("Your current balance is: $%.2f%n", atm.getBalance());
                    break;

                case "2":
                    System.out.print("Enter deposit amount: $");
                    try {
                        double depAmount = Double.parseDouble(scanner.nextLine());
                        atm.deposit(depAmount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;

                case "3":
                    System.out.print("Enter withdrawal amount: $");
                    try {
                        double withAmount = Double.parseDouble(scanner.nextLine());
                        atm.withdraw(withAmount);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid input. Please enter a valid number.");
                    }
                    break;

                case "4":
                    System.out.println("Thank you for using the ATM. Goodbye!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please select an option between 1 and 4.");
                    break;
            }
        }
        scanner.close();
    }
}
