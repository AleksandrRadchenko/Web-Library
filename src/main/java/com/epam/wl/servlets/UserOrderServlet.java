package com.epam.wl.servlets;

import com.epam.wl.services.TestUserOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserOrderServlet", urlPatterns = "/uo")
public class UserOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("userorders", TestUserOrderService.getOrders());
        request.getRequestDispatcher("userorders.jsp").forward(request, response);
    }
}
