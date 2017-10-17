package com.epam.wl.servlets;

import com.epam.wl.entities.BookOrder;
import com.epam.wl.services.BookOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "BookOrderServlet", urlPatterns = "/book_order")
public class BookOrderServlet extends HttpServlet {
    private final BookOrderService bookOrderService = BookOrderService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("userid") != null) {
                List<BookOrder> bookOrders = bookOrderService.getByUserId(Integer.parseInt(request.getParameter("userid")));
                request.setAttribute("username", bookOrders.get(0).getUserOrder().getUser().getName() + " " + bookOrders.get(0).getUserOrder().getUser().getLastname());
                request.setAttribute("bookorders", bookOrders);
                request.setAttribute("allusers", bookOrderService.getAllUsersWithOrders()); // TODO: 17.10.2017 chage for UserService.getAllUsers()
            } else {
                request.setAttribute("username", "all users");
                request.setAttribute("bookorders", bookOrderService.getAll());
                request.setAttribute("allusers", bookOrderService.getAllUsersWithOrders());
            }
            request.getRequestDispatcher("BookOrders.jsp").forward(request, response);
        } catch (SQLException e) {
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }
}
