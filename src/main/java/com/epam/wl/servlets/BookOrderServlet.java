package com.epam.wl.servlets;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.services.BookOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookOrderServlet", urlPatterns = "/book_order")
public class BookOrderServlet extends HttpServlet {
    private final BookOrderService bookOrderService = BookOrderService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!(request.getParameter("userid") == null)) {
            List<BookOrder> bookOrders = bookOrderService.getByUserId(Integer.parseInt(request.getParameter("userid")));
            request.setAttribute("username", bookOrders.get(0).getName() + " " + bookOrders.get(0).getLastName());
            request.setAttribute("bookorders", bookOrders);
            request.setAttribute("allusers", bookOrderService.getAllUsers());
        } else {
            request.setAttribute("username", "all users");
            request.setAttribute("bookorders", bookOrderService.getAll());
            request.setAttribute("allusers", bookOrderService.getAllUsers());
        }
        request.getRequestDispatcher("BookOrders.jsp").forward(request, response);
    }
}