package main.java.com.endrius.ecommerce;
import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id; 
    private String name;
    private double price;
 
    // In-memory storage for all products
    private static List<Product> products = new ArrayList<>();

    // Constructor
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    // === CRUD Operations ===

    // Create / Add a product
    public static void addProduct(Product p) {
        products.add(p);
    }

    // Read / Get all products
    public static List<Product> getAllProducts() {
        return products;
    }

    // Read / Get product by ID
    public static Product getProductById(int id) {
        for (Product p : products) {
            if (p.getId() == id) return p;
        }
        return null; // return null if not found
    }

    // Update product 
    public static void updateProduct(int id, String newName, double newPrice) {
        Product p = getProductById(id);
        if (p != null) {
            p.setName(newName);
            p.setPrice(newPrice);
        }
    }

    // Delete product by ID 
    public static void removeProduct(int id) {
        products.removeIf(p -> p.getId() == id);
    }
}
