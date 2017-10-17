package com.epam.wl.servlets;

import com.epam.wl.services.TestBookService;
import com.epam.wl.services.TestLoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@javax.servlet.annotation.WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    private final TestLoginService service = TestLoginService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.sendRedirect(service.confirmUser(request));
        } catch (SQLException e) {
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }
}
