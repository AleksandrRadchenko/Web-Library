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
    private final BookOrderService bookOrderService = BookOrderService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!(request.getParameter("userid") == null)) {
            request.setAttribute("bookorders", bookOrderService.getByUserId(Integer.parseInt(request.getParameter("userid"))));
        } else {
            request.setAttribute("bookorders", bookOrderService.getAll());
        }
        request.getRequestDispatcher("BookOrders.jsp").forward(request, response);
    }
}
