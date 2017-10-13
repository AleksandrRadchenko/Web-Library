package com.epam.wl.servlets;

import com.epam.wl.entities.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

import static com.epam.wl.services.TestLoginService.userDAO;
import static com.epam.wl.services.TestLoginService.signIn;

@javax.servlet.annotation.WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Optional<User> userOptional = userDAO.getUserByEmailAndPassword(email, password);

            if (userOptional.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", userOptional.get());

                response.sendRedirect(signIn(email, password));
            } else {
                response.sendRedirect("login_error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
