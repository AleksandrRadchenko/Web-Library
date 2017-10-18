package com.epam.wl.servlets;

import com.epam.wl.services.UserService;
import com.epam.wl.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

/**
 * This servlet represents user's profile in the library.
 */
@WebServlet(name = "UserServlet", urlPatterns = "/userprofile")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            UserService service = UserService.getInstance();
            HttpSession session = request.getSession(false);

            if (session != null) {
                User user = (User) session.getAttribute("currentSessionUser");
                request.setAttribute("users", service.getUser(user));
                request.setAttribute("userorders", service.getUserOrderBooks(user));

                request.getRequestDispatcher("users.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException | IllegalArgumentException e) {
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }
}
