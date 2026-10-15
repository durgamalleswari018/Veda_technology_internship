interface PaymentMethod {
    boolean validate();
    void processPayment(double amount);
}

class CardPayment implements PaymentMethod {
    private String cardNumber;
    private String expiryDate;

    public CardPayment(String cardNumber, String expiryDate) {
        this.cardNumber = cardNumber;
        this.expiryDate = expiryDate;
    }

    @Override
    public boolean validate() {
        return cardNumber != null && cardNumber.length() == 16;
    }

    @Override
    public void processPayment(double amount) {
        if (validate()) {
            System.out.println("💳 Card Payment Success: Processed ₹" + amount);
        } else {
            System.out.println("❌ Card Payment Failed: Invalid 16-digit card number.");
        }
    }
}

class UPIPayment implements PaymentMethod {
    private String upiId;

    public UPIPayment(String upiId) {
        this.upiId = upiId;
    }

    @Override
    public boolean validate() {
        return upiId != null && upiId.contains("@");
    }

    @Override
    public void processPayment(double amount) {
        if (validate()) {
            System.out.println("📱 UPI Payment Success: Processed ₹" + amount);
        } else {
            System.out.println("❌ UPI Payment Failed: Invalid UPI handle format.");
        }
    }
}

class WalletPayment implements PaymentMethod {
    private double balance;

    public WalletPayment(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean validate() {
        return balance >= 0;
    }

    @Override
    public void processPayment(double amount) {
        if (validate() && balance >= amount) {
            balance -= amount;
            System.out.println("👛 Wallet Payment Success: Processed ₹" + amount + " | Remaining Balance: ₹" + balance);
        } else {
            System.out.println("❌ Wallet Payment Failed: Insufficient funds or invalid setup.");
        }
    }
}

class PaymentProcessor {
    private PaymentMethod paymentMethod;

    public PaymentProcessor(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public void executeTransaction(double amount) {
        paymentMethod.processPayment(amount);
    }
}

public class Main {
    public static void main(String[] args) {
        System.out.println("=== Running Consolidated Payment System ===\n");

        PaymentMethod myCard = new CardPayment("1234567890123456", "11/29");
        PaymentProcessor cardProcessor = new PaymentProcessor(myCard);
        cardProcessor.executeTransaction(2500.00);

        PaymentMethod myUpi = new UPIPayment("alex@okaxis");
        PaymentProcessor upiProcessor = new PaymentProcessor(myUpi);
        upiProcessor.executeTransaction(450.50);

        PaymentMethod myWallet = new WalletPayment(100.00); 
        PaymentProcessor walletProcessor = new PaymentProcessor(myWallet);
        walletProcessor.executeTransaction(350.00);
    }
}
