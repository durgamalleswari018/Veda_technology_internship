import java.util.ArrayList;
import java.util.Scanner;

class Product {
    private int id;
    private String name;
    private int quantity;
    private double price;

    public Product(int id, String name, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    @Override
    public String toString() {
        return String.format("ID: %-5d | Name: %-15s | Qty: %-5d | Price: $%.2f", id, name, quantity, price);
    }
}

public class InventorySystem {
    private final ArrayList<Product> inventory = new ArrayList<>();

    private Product findProductById(int id) {
        for (Product product : inventory) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    public void addProduct(Product product) {
        if (findProductById(product.getId()) != null) {
            System.out.println("❌ Error: A product with ID " + product.getId() + " already exists.");
            return;
        }
        inventory.add(product);
        System.out.println("✅ Product added successfully!");
    }

    public void displayAllProducts() {
        if (inventory.isEmpty()) {
            System.out.println("⚠️ The inventory is currently empty.");
            return;
        }
        System.out.println("\n--- Current Inventory List ---");
        for (Product product : inventory) {
            System.out.println(product);
        }
    }

    public void searchProduct(int id) {
        Product found = findProductById(id);
        if (found != null) {
            System.out.println("🔍 Product Found: " + found);
        } else {
            System.out.println("❌ Product with ID " + id + " not found.");
        }
    }

    public void updateProduct(int id, String newName, int newQty, double newPrice) {
        Product product = findProductById(id);
        if (product != null) {
            product.setName(newName);
            product.setQuantity(newQty);
            product.setPrice(newPrice);
            System.out.println("✅ Product updated successfully!");
        } else {
            System.out.println("❌ Product with ID " + id + " not found.");
        }
    }

    public void removeProduct(int id) {
        Product product = findProductById(id);
        if (product != null) {
            inventory.remove(product);
            System.out.println("🗑️ Product removed successfully!");
        } else {
            System.out.println("❌ Product with ID " + id + " not found.");
        }
    }

    public static void main(String[] args) {
        InventorySystem manager = new InventorySystem();
        Scanner sc = new Scanner(System.in);
        int choice;

        manager.addProduct(new Product(101, "Laptop", 8, 899.99));
        manager.addProduct(new Product(102, "Mouse", 45, 24.50));

        do {
            System.out.println("\n--- Inventory Management Options ---");
            System.out.println("1. Add Product (Create)");
            System.out.println("2. Display All Products (Read)");
            System.out.println("3. Search Product by ID");
            System.out.println("4. Update Product Details");
            System.out.println("5. Remove Product (Delete)");
            System.out.println("6. Exit");
            System.out.print("Choose option (1-6): ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    System.out.print("Enter Price: ");
                    double price = sc.nextDouble();
                    manager.addProduct(new Product(id, name, qty, price));
                    break;

                case 2:
                    manager.displayAllProducts();
                    break;

                case 3:
                    System.out.print("Enter ID to search: ");
                    manager.searchProduct(sc.nextInt());
                    break;

                case 4:
                    System.out.print("Enter existing ID to update: ");
                    int uId = sc.nextInt();
                    sc.nextLine(); 
                    System.out.print("Enter New Name: ");
                    String uName = sc.nextLine();
                    System.out.print("Enter New Quantity: ");
                    int uQty = sc.nextInt();
                    System.out.print("Enter New Price: ");
                    double uPrice = sc.nextDouble();
                    manager.updateProduct(uId, uName, uQty, uPrice);
                    break;

                case 5:
                    System.out.print("Enter ID to remove: ");
                    manager.removeProduct(sc.nextInt());
                    break;

                case 6:
                    System.out.println("Exiting. Application Terminated.");
                    break;

                default:
                    System.out.println("Invalid option. Please input 1 through 6.");
            }
        } while (choice != 6);

        sc.close();
    }
}
