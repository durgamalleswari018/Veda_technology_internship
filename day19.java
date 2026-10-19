import java.util.HashSet;
import java.util.Set;

public class EmailRegistry {
    private final Set<String> emailSet;

    public EmailRegistry() {
        this.emailSet = new HashSet<>();
    }

    private String normalizeEmail(String email) {
        if (email == null) {
            return "";
        }
        return email.trim().toLowerCase();
    }

    public boolean registerEmail(String email) {
        String normalized = normalizeEmail(email);
        if (normalized.isEmpty()) {
            System.out.println("Invalid email address.");
            return false;
        }

        boolean isAdded = emailSet.add(normalized);
        if (isAdded) {
            System.out.println("Successfully registered: " + normalized);
        } else {
            System.out.println("Registration failed: " + normalized + " is already registered.");
        }
        return isAdded;
    }

    public boolean removeEmail(String email) {
        String normalized = normalizeEmail(email);
        boolean isRemoved = emailSet.remove(normalized);
        if (isRemoved) {
            System.out.println("Successfully removed: " + normalized);
        } else {
            System.out.println("Removal failed: " + normalized + " not found in registry.");
        }
        return isRemoved;
    }

    public void listEmails() {
        System.out.println("\n--- Registered Emails ---");
        if (emailSet.isEmpty()) {
            System.out.println("The registry is currently empty.");
        } else {
            for (String email : emailSet) {
                System.out.println("- " + email);
            }
        }
        System.out.println("-------------------------\n");
    }

    public static void main(String[] args) {
        EmailRegistry registry = new EmailRegistry();

        registry.registerEmail("alice@example.com");
        registry.registerEmail("bob@example.com");

        registry.registerEmail("ALICE@example.com "); 
        registry.registerEmail("bob@example.com");     

        registry.listEmails();

        registry.removeEmail("alice@example.com");
        registry.removeEmail("charlie@example.com"); 

        registry.listEmails();
    }
}
