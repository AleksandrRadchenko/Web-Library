package com.epam.wl.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NewBookOrderServlet", urlPatterns = "/makebookorder")
public class NewBookOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getRequestDispatcher("bookOption"));
        System.out.println(request.getRequestDispatcher("exemlarNum"));
//        request.setAttribute("userorders", TestUserOrderService.getOrders());
//        request.getRequestDispatcher("userorders.jsp").forward(request, response);
    }
}
