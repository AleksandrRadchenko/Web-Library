package com.epam.wl.servlets;

import com.epam.wl.entities.User;
import com.epam.wl.services.TestLoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@javax.servlet.annotation.WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final TestLoginService service = new TestLoginService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            Optional<User> userOptional = service.userDAO.getUserByEmailAndPassword(email, password);

            if (userOptional.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", userOptional.get());

                response.sendRedirect(service.getRolePage(email));
            } else {
                response.sendRedirect("login_error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
