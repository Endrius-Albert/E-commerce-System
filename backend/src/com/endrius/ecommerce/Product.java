package com.endrius.ecommerce;

public class Product {

    private int id;
    private String name;
    private double price;
    private int stock;

    // Constructor with all fields 
    public Product(int id, String name, double price, int stock) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative");

        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Constructor without ID 
    public Product(String name, double price, int stock) {
        this(0, name, price, stock);
    }

   
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (price < 0) throw new IllegalArgumentException("Price cannot be negative");
        this.price = price;
    }

    public int getStock() { return stock; }
    public void setStock(int stock) {
        if (stock < 0) throw new IllegalArgumentException("Stock cannot be negative");
        this.stock = stock;
    }
}
