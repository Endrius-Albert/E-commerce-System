package com.endrius.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Product {
    private int id; 
    private String name;
    private double price;

  
    private static List<Product> products = new ArrayList<>();

    static {
      
    products.add(new Product(1, "Laptop", 1200.00));
    products.add(new Product(2, "Smartphone", 800.00));
    products.add(new Product(3, "Headphones", 150.00));
    products.add(new Product(4, "Keyboard", 70.00));
    products.add(new Product(5, "Mouse", 50.00));
    }

  
    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    
    public int getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    public static List<Product> getAllProducts() {
        return products;
    }
}
