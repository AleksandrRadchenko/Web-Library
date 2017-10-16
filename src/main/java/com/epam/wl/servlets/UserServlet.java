package com.epam.wl.servlets;

import com.epam.wl.services.TestUserService;
import com.epam.wl.entities.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "UserServlet", urlPatterns = "/userprofile")
public class UserServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestUserService service = TestUserService.getInstance();
        request.setAttribute("users", service.getUser());//request.setAttribute("users", TestUserService.getUser());
        request.setAttribute("books", service.getUserOrderBooks());

        HttpSession session = request.getSession(true);//false
        //session.setAttribute("userID", service.getUser().get(0).getId());
        request.getRequestDispatcher("users.jsp").forward(request, response);
    }
}
