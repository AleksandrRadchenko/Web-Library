package com.epam.wl.servlets;

import com.epam.wl.enums.UserRole;
import com.epam.wl.services.TestUserService;
import com.epam.wl.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "UserEditServlet", urlPatterns = "/useredit")
public class UserEditServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("UTF-8");
        request.setCharacterEncoding("UTF-8");
        String name = request.getParameter("name");
        String lastName = request.getParameter("lastname");
        String email = request.getParameter("email");
        String passwordHash = request.getParameter("passwordhash");
        //UserRole role = UserRole.valueOf(request.getParameter("userrole"));

        TestUserService.editUser(name, lastName, email, passwordHash);//, role
        response.sendRedirect("/userprofile");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //todo what should i add here???
    }
}
