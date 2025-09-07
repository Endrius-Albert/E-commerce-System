package com.endrius.ecommerce;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        ProductDAO productDAO = new ProductDAO();

        
        productDAO.addProduct(new Product(0, "Laptop", 1200.0, 10));
        productDAO.addProduct(new Product(0, "Smartphone", 800.0, 15));
        productDAO.addProduct(new Product(0, "Headphones", 150.0, 25));
        productDAO.addProduct(new Product(0, "Keyboard", 70.0, 30));
        productDAO.addProduct(new Product(0, "Mouse", 50.0, 50));

        
        List<Product> products = productDAO.getAllProducts();
        System.out.println("All Products:");
        for (Product p : products) {
            System.out.println(p.getId() + " - " + p.getName() + " - $" + p.getPrice() + " - Stock: " + p.getStock());
        }

        
        if (!products.isEmpty()) {
            Product firstProduct = products.get(0);
            
            Product updatedProduct = new Product(
                    firstProduct.getId(),
                    "Gaming Laptop",
                    1500.0,
                    firstProduct.getStock()
            );
            
            productDAO.deleteProduct(firstProduct.getId());
            productDAO.addProduct(updatedProduct);
        }

        
        if (!products.isEmpty()) {
            productDAO.deleteProduct(products.get(0).getId());
        }

       
        List<Product> productsAfter = productDAO.getAllProducts();
        System.out.println("Products left: " + productsAfter.size());
        for (Product p : productsAfter) {
            System.out.println(p.getId() + " - " + p.getName() + " - $" + p.getPrice() + " - Stock: " + p.getStock());
        }
    }
}
