package com.endrius.ecommerce;

public class OrderItem {
    private Product product;
    private int quantity;

    public OrderItem(Product product, int quantity) {
        if(quantity <= 0) throw new IllegalArgumentException("Quantity cannot be less than 1");
        this.product = product;
        this.quantity = quantity;
    }

    
    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if(quantity <= 0) throw new IllegalArgumentException("Quantity cannot be less than 1");
        this.quantity = quantity;
    }

    public double getSubtotal() {
        return product.getPrice() * quantity;
    }
}
