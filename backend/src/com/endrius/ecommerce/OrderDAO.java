package com.endrius.ecommerce;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDAO {

    public void addOrder(Order order) {
        try (Connection conn = DBConnection.getConnection()) {
            conn.setAutoCommit(false);

           
            double total = order.getItems().stream()
                    .mapToDouble(OrderItem::getSubtotal)
                    .sum();
            order.setTotal(total);

            String sqlOrder = "INSERT INTO orders (user_id, total) VALUES (?, ?)";
            PreparedStatement stmtOrder = conn.prepareStatement(sqlOrder, Statement.RETURN_GENERATED_KEYS);
            stmtOrder.setInt(1, order.getUser().getId());
            stmtOrder.setDouble(2, total);
            stmtOrder.executeUpdate();

            ResultSet keys = stmtOrder.getGeneratedKeys();
            if (keys.next()) order.setId(keys.getInt(1));

            String sqlItem = "INSERT INTO order_items (order_id, product_id, quantity) VALUES (?, ?, ?)";
            PreparedStatement stmtItem = conn.prepareStatement(sqlItem);
            for (OrderItem item : order.getItems()) {
                stmtItem.setInt(1, order.getId());
                stmtItem.setInt(2, item.getProduct().getId());
                stmtItem.setInt(3, item.getQuantity());
                stmtItem.addBatch();
            }
            stmtItem.executeBatch();

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getAllOrders() {
        List<Order> orders = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection()) {
            String sql = "SELECT o.id as order_id, o.total, u.id as user_id, u.name, u.email, u.password, u.role " +
                         "FROM orders o JOIN users u ON o.user_id = u.id";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                User user = new User(
                        rs.getInt("user_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("role")
                );
                Order order = new Order(rs.getInt("order_id"), user);
                order.setTotal(rs.getDouble("total"));

                
                String sqlItems = "SELECT oi.quantity, p.id as product_id, p.name, p.price, p.stock " +
                                  "FROM order_items oi JOIN products p ON oi.product_id = p.id " +
                                  "WHERE oi.order_id=?";
                PreparedStatement stmtItems = conn.prepareStatement(sqlItems);
                stmtItems.setInt(1, order.getId());
                ResultSet rsItems = stmtItems.executeQuery();

                while(rsItems.next()) {
                    Product product = new Product(
                            rsItems.getInt("product_id"),
                            rsItems.getString("name"),
                            rsItems.getDouble("price"),
                            rsItems.getInt("stock")
                    );
                    order.addItem(product, rsItems.getInt("quantity"));
                }
                orders.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
}
