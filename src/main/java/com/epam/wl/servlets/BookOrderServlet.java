package com.epam.wl.servlets;

import com.epam.wl.services.BookOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookOrderServlet", urlPatterns = "/book_order")
public class BookOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("userid").isEmpty()) {
            request.setAttribute("bookorders", BookOrderService.getByUserId(request.getParameter("userid")));
        } else {
            request.setAttribute("bookorders", BookOrderService.getAll());
        }
        request.getRequestDispatcher("bookOrders.jsp").forward(request, response);
    }
}
