package com.epam.wl.servlets;

import com.epam.wl.enums.UserRole;
import com.epam.wl.services.TestLoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "SignUpServlet", urlPatterns = "/sign_up")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.sendRedirect(TestLoginService.addNewUser(request.getParameter("name"), request.getParameter("lastname"),
                request.getParameter("email"), request.getParameter("password"),
                "user".equals(request.getParameter("role")) ? UserRole.USER : UserRole.LIBRARIAN));
    }
}
