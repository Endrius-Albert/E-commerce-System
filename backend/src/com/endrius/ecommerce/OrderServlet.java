package com.endrius.ecommerce;

import com.google.gson.Gson;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.WebServlet;
import java.io.IOException;
import java.util.List;


public class OrderServlet extends HttpServlet {
    private OrderDAO orderDAO = new OrderDAO();
    private Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        List<Order> orders = orderDAO.getAllOrders();
        resp.getWriter().write(gson.toJson(orders));
    }

   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("application/json;charset=UTF-8");
        Order order = gson.fromJson(req.getReader(), Order.class);
        orderDAO.addOrder(order);
        resp.setStatus(HttpServletResponse.SC_CREATED);
        resp.getWriter().write(gson.toJson(order));
    }
}
