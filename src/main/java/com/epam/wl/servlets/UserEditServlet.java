package com.epam.wl.servlets;

import com.epam.wl.services.TestUserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserEditServlet", urlPatterns = "/useredit")
public class UserEditServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            response.setCharacterEncoding("UTF-8");
            request.setCharacterEncoding("UTF-8");
            String name = request.getParameter("name");
            String lastName = request.getParameter("lastname");
            String email = request.getParameter("email");
            String passwordHash = request.getParameter("passwordhash");

            TestUserService service = TestUserService.getInstance();
            service.editUser(name, lastName, email, passwordHash);
            response.sendRedirect("/userprofile");
        } catch (SQLException e) {
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }
}
