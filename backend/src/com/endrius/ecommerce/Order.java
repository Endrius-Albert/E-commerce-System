package com.endrius.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private int id;
    private User user;
    private List<OrderItem> items;
    private double total;

    public Order(int id, User user) {
        this.id = id;
        this.user = user;
        this.items = new ArrayList<>();
    }

    public Order(User user) {
        this.user = user;
        this.items = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void addItem(Product product, int quantity) {
        items.add(new OrderItem(product, quantity));
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
