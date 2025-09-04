package com.endrius.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        // === Users ===
        User.addUser(new User(1, "Alice", "alice@email.com"));
        User.addUser(new User(2, "Bob", "bob@email.com"));
        User.addUser(new User(3, "Charlie", "charlie@email.com"));

        // === Products ===
        Product.addProduct(new Product(1, "Laptop", 1200.0));
        Product.addProduct(new Product(2, "Mouse", 25.0));
        Product.addProduct(new Product(3, "Keyboard", 45.0));
        Product.addProduct(new Product(4, "Monitor", 250.0));

        // ===  Orders ===
        for (User user : User.getAllUsers()) {

            // Select products dynamically 
            List<Product> productsInOrder = new ArrayList<>(Product.getAllProducts());

            // Generate order ID dynamically
            int nextOrderId = Order.getAllOrders().size() + 1;

            // Create the order
            Order order = new Order(nextOrderId, user.getId(), productsInOrder);

            // Add the order to the system
            Order.addOrder(order);
        }

        // === Print Users ===
        System.out.println("All Users:");
        for (User u : User.getAllUsers()) {
            System.out.println(u.getId() + " - " + u.getName() + " - " + u.getEmail());
        }

        // === Print Products ===
        System.out.println("All Products:");
        for (Product p : Product.getAllProducts()) {
            System.out.println(p.getId() + " - " + p.getName() + " - $" + p.getPrice());
        }

        // === Print Orders ===
        System.out.println("All Orders:");
        for (Order o : Order.getAllOrders()) {
            System.out.println("Order ID: " + o.getId() + ", User ID: " + o.getUserId() + ", Total: $" + o.getTotalPrice());
        }

        // ===  Update  ===
        // Update first user
        if (!User.getAllUsers().isEmpty()) {
            User firstUser = User.getAllUsers().get(0);
            User.updateUser(firstUser.getId(), "Alice Smith", "alice.smith@email.com");
        }

        // Update first product
        if (!Product.getAllProducts().isEmpty()) {
            Product firstProduct = Product.getAllProducts().get(0);
            Product.updateProduct(firstProduct.getId(), "Gaming Laptop", 1500.0);
        }

        // Update first order 
        if (!Order.getAllOrders().isEmpty()) {
            Order firstOrder = Order.getAllOrders().get(0);
            List<Product> newProducts = new ArrayList<>();
            if (!Product.getAllProducts().isEmpty()) newProducts.add(Product.getAllProducts().get(0));
            Order.updateOrder(firstOrder.getId(), newProducts);
        }

        // ===  Delete  ===
        if (!User.getAllUsers().isEmpty()) {
            User.removeUser(User.getAllUsers().get(0).getId());
        }
        if (!Product.getAllProducts().isEmpty()) {
            Product.removeProduct(Product.getAllProducts().get(0).getId());
        }
        if (!Order.getAllOrders().isEmpty()) {
            Order.removeOrder(Order.getAllOrders().get(0).getId());
        }

        // === Print After Deletions ===
        System.out.println("After Deletions:");
        System.out.println("Users left: " + User.getAllUsers().size());
        System.out.println("Products left: " + Product.getAllProducts().size());
        System.out.println("Orders left: " + Order.getAllOrders().size());
    }
}
