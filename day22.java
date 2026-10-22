
class InvalidAccountException extends Exception {
    public InvalidAccountException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class InvalidTransactionAmountException extends Exception {
    public InvalidTransactionAmountException(String message) {
        super(message);
    }
}

class BankAccount {
    private String accountNumber;
    private String accountHolder;
    private double balance;

    public BankAccount(String accountNumber, String accountHolder, double initialBalance) {
        this.accountNumber = accountNumber;
        this.accountHolder = accountHolder;
        this.balance = initialBalance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) throws InvalidTransactionAmountException {
        if (amount <= 0) {
            throw new InvalidTransactionAmountException(
                "Deposit failed: Amount must be greater than zero. Attempted: $" + amount
            );
        }

        balance += amount;
        System.out.println("Successfully deposited $" + amount +
                           ". New Balance: $" + balance);
    }

    public void withdraw(double amount)
            throws InvalidTransactionAmountException, InsufficientBalanceException {
        if (amount <= 0) {
            throw new InvalidTransactionAmountException(
                "Withdrawal failed: Amount must be greater than zero. Attempted: $" + amount
            );
        }

        if (amount > balance) {
            throw new InsufficientBalanceException(
                "Withdrawal failed: Insufficient funds. Available: $" +
                balance + ", Requested: $" + amount
            );
        }

        balance -= amount;
        System.out.println("Successfully withdrew $" + amount +
                           ". New Balance: $" + balance);
    }
}

public class BankingSystem {
    private static BankAccount[] accounts = {
        new BankAccount("ACC101", "Alice Smith", 500.00),
        new BankAccount("ACC102", "Bob Jones", 1000.00)
    };

    private static BankAccount findAccount(String accountNumber)
            throws InvalidAccountException {
        for (BankAccount acc : accounts) {
            if (acc.getAccountNumber().equalsIgnoreCase(accountNumber)) {
                return acc;
            }
        }

        throw new InvalidAccountException(
            "Search failed: Account number '" + accountNumber + "' does not exist."
        );
    }

    public static void main(String[] args) {
        System.out.println("=== STARTING BANKING SYSTEM TESTS ===");

        System.out.println("\n--- Test 1: Valid Operations ---");
        try {
            BankAccount account = findAccount("ACC101");
            account.deposit(200.00);
            account.withdraw(150.00);
        } catch (InvalidAccountException |
                 InvalidTransactionAmountException |
                 InsufficientBalanceException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("\n--- Test 2: Invalid Account Test ---");
        try {
            BankAccount account = findAccount("ACC999");
            account.deposit(100.00);
        } catch (InvalidAccountException e) {
            System.out.println("Caught Custom Exception: " + e.getMessage());
        } catch (InvalidTransactionAmountException e) {
            System.out.println("Caught Custom Exception: " + e.getMessage());
        }

        System.out.println("\n--- Test 3: Insufficient Balance Test ---");
        try {
            BankAccount account = findAccount("ACC101");
            account.withdraw(5000.00);
        } catch (InvalidAccountException |
                 InvalidTransactionAmountException |
                 InsufficientBalanceException e) {
            System.out.println("Caught Custom Exception: " + e.getMessage());
        }

        System.out.println("\n--- Test 4: Invalid Amount Test ---");
        try {
            BankAccount account = findAccount("ACC102");
            account.deposit(-50.00);
        } catch (InvalidAccountException |
                 InvalidTransactionAmountException e) {
            System.out.println("Caught Custom Exception: " + e.getMessage());
        }

        System.out.println("\n=== All tests executed smoothly ===");
    }
}
