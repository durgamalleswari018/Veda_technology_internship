import java.util.Scanner;
class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = Math.max(0, balance);
    }
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ Deposit successful!");
        } else {
            System.out.println("❌ Enter a valid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("❌ Enter a valid withdrawal amount.");
        } else if (amount > balance) {
            System.out.println("❌ Insufficient balance!");
        } else {
            balance -= amount;
            System.out.println("✅ Withdrawal successful!");
        }()
    }
    public void displayBalance() {
        System.out.printf("💰 Current Balance: ₹%.2f%n", balance);
    }

    public void displayAccountDetails() {
        System.out.println("\n===== ACCOUNT DETAILS =====");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Holder Name   : " + holderName);
        displayBalance();
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("🏦 WELCOME TO JAVA BANK 🏦");
        System.out.print("Enter account number: ");
        String accNo = sc.nextLine();

        System.out.print("Enter account holder name: ");
        String name = sc.nextLine();

        BankAccount account = new BankAccount(accNo, name, 1000);

        int choice;

        do {
            System.out.println("\n========== BANK MENU ==========");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Account Details");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");

            while (!sc.hasNextInt()) {
                System.out.println("❌ Please enter a number from 1 to 5.");
                sc.next();
                System.out.print("Choose an option: ");
            }

            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter deposit amount: ₹");
                    if (sc.hasNextDouble()) {
                        account.deposit(sc.nextDouble());
                    } else {
                        System.out.println("❌ Invalid amount.");
                        sc.next();
                    }
                    break;

                case 2:
                    System.out.print("Enter withdrawal amount: ₹");
                    if (sc.hasNextDouble()) {
                        account.withdraw(sc.nextDouble());
                    } else {
                        System.out.println("❌ Invalid amount.");
                        sc.next();
                    }
                    break;

                case 3:
                    account.displayBalance();
                    break;

                case 4:
                    account.displayAccountDetails();
                    break;

                case 5:
                    System.out.println("👋 Thank you for using Java Bank!");
                    break;

                default:
                    System.out.println("❌ Invalid choice. Select 1–5.");
            }
        } while (choice != 5);

        sc.close();
    }
}
