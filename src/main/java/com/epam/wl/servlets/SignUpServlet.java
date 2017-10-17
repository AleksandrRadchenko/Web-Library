package com.epam.wl.servlets;

import com.epam.wl.enums.UserRole;
import com.epam.wl.services.TestLoginService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@javax.servlet.annotation.WebServlet(name = "SignUpServlet", urlPatterns = "/sign_up")
public class SignUpServlet extends HttpServlet {
    private final TestLoginService service = TestLoginService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        try {
            response.sendRedirect(service.addNewUser(request.getParameter("name"),
                    request.getParameter("last_name"), request.getParameter("email"),
                    request.getParameter("password"), "user".equals(request.getParameter("role"))
                            ? UserRole.USER : UserRole.LIBRARIAN, request.getParameter("password_repeat"),
                    request.getParameter("captcha")));
        } catch (SQLException | IllegalArgumentException e) {
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }
}