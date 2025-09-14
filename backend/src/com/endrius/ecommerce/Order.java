package com.endrius.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private int userId;
    private List<Product> products;
    private double totalPrice;

    
    private static List<Order> orders = new ArrayList<>();

    public Order(int id, int userId, List<Product> products) {
        this.id = id;
        this.userId = userId;
        this.products = products;
        this.totalPrice = calculateTotal(products);
    }

  
    private double calculateTotal(List<Product> products) {
        double sum = 0;
        for (Product p : products) {
            sum += p.getPrice();
        }
        return sum;
    }

  
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public List<Product> getProducts() { return products; }
    public void setProducts(List<Product> products) {
        this.products = products;
        this.totalPrice = calculateTotal(products);
    }

    public double getTotalPrice() { return totalPrice; }

  
    public static void addOrder(Order o) {
        orders.add(o);
    }

    public static List<Order> getAllOrders() {
        return orders;
    }

    public static Order getOrderById(int id) {
        for (Order o : orders) {
            if (o.getId() == id) return o;
        }
        return null;
    }


    public static void updateOrder(int id, List<Product> newProducts) {
        Order o = getOrderById(id);
        if (o != null) {
            o.setProducts(newProducts);
        }
    }


    public static void removeOrder(int id) {
        orders.removeIf(o -> o.getId() == id);
    }
}
