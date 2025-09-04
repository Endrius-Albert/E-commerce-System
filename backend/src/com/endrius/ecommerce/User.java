package com.endrius.ecommerce;

import java.util.ArrayList;
import java.util.List;

public class User {
    private int id;
    private String name;
    private String email;

    // In-memory storage for all users
    private static List<User> users = new ArrayList<>();

    // Constructor
    public User(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    // === CRUD Operations ===

    // Create / Add a user
    public static void addUser(User u) {
        users.add(u);
    }

    // Read / Get all users
    public static List<User> getAllUsers() {
        return users;
    }

    // Read / Get user by ID
    public static User getUserById(int id) {
        for (User u : users) {
            if (u.getId() == id) return u;
        }
        return null;
    }

    // Update user 
    public static void updateUser(int id, String newName, String newEmail) {
        User u = getUserById(id);
        if (u != null) {
            u.setName(newName);
            u.setEmail(newEmail);
        }
    }

    // Delete user by ID
    public static void removeUser(int id) {
        users.removeIf(u -> u.getId() == id);
    }
}
