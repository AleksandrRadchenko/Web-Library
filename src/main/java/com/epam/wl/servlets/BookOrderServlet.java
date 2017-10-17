package com.epam.wl.servlets;

import com.epam.wl.dao.UserDAO;
import com.epam.wl.entities.BookOrder;
import com.epam.wl.entities.User;
import com.epam.wl.services.BookOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

@WebServlet(name = "BookOrderServlet", urlPatterns = "/book_order")
public class BookOrderServlet extends HttpServlet {
    private final BookOrderService bookOrderService = BookOrderService.getInstance();
    private final UserDAO userDAO = UserDAO.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getParameter("userid") != null) {
                int userId = Integer.parseInt(request.getParameter("userid"));
                List<BookOrder> bookOrders = bookOrderService.getByUserId(userId);
                List<User> allUsers = userDAO.getAllUsers();
                allUsers.sort(Comparator.comparing(User::getId));
                request.setAttribute("allusers", allUsers);
                request.setAttribute("username", allUsers.get(userId - 1).getName() + " " + allUsers.get(userId - 1).getLastname());
                request.setAttribute("bookorders", bookOrders);
            } else {
                request.setAttribute("username", "all users");
                request.setAttribute("bookorders", bookOrderService.getAll());
                request.setAttribute("allusers", userDAO.getAllUsers());
            }
            request.getRequestDispatcher("BookOrders.jsp").forward(request, response);
        } catch (SQLException | IllegalArgumentException e) {
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }
}
