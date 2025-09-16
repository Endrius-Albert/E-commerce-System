package com.endrius.ecommerce;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        System.out.println("=== E-commerce Backend Test ===");

        // -------------------------
        // 1. TEST PRODUCTS CRUD
        // -------------------------
        ProductDAO productDAO = new ProductDAO();

        System.out.println("All Products:");
        List<Product> products = productDAO.getAllProducts();
        for (Product p : products) {
            System.out.println(p.getId() + " - " + p.getName() + " - $" + p.getPrice() + " - Stock: " + p.getStock());
        }

        // Adding a new product
        Product newProduct = new Product("Monitor", 300.0, 10);
        productDAO.addProduct(newProduct);
        System.out.println("Added product: " + newProduct.getName());

        // Updating a product
        newProduct.setPrice(280.0);
        newProduct.setStock(15);
        productDAO.updateProduct(newProduct);
        System.out.println("Updated product: " + newProduct.getName());

        // -------------------------
        // 2. TEST USERS CRUD
        // -------------------------
        UserDAO userDAO = new UserDAO();

        System.out.println("All Users:");
        List<User> users = userDAO.getAllUsers();
        for (User u : users) {
            System.out.println(u.getId() + " - " + u.getName() + " - " + u.getEmail() + " - Role: " + u.getRole());
        }

        // Adding a new user
        User newUser = new User("John Doe", "john@example.com", "password123", "CUSTOMER");
        userDAO.addUser(newUser);
        System.out.println("Added user: " + newUser.getName());

        // Updating a user
        newUser.setEmail("john.doe@example.com");
        userDAO.updateUser(newUser);
        System.out.println("Updated user: " + newUser.getName());

        // -------------------------
        // 3. TEST ORDERS CRUD
        // -------------------------
        OrderDAO orderDAO = new OrderDAO();

       
        User orderUser = users.get(0);
        Order order = new Order(orderUser);

        order.addItem(products.get(0), 2); 
        order.addItem(products.get(1), 1); 

        orderDAO.addOrder(order);
        System.out.println("Order created with ID: " + order.getId());

        System.out.println("All Orders:");
        List<Order> allOrders = orderDAO.getAllOrders();
        for (Order o : allOrders) {
            System.out.println("Order ID: " + o.getId() + ", User ID: " + o.getUser().getId() + ", Total: $" + o.getTotal());
            for (OrderItem item : o.getItems()) {
                System.out.println("  - " + item.getProduct().getName() + " x" + item.getQuantity() + " @ $" + item.getProduct().getPrice());
            }
        }

        System.out.println("=== Backend Test Completed ===");
    }
}
