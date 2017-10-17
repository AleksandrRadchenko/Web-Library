package com.epam.wl.servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LogoutServlet", urlPatterns = "/log_out")
public class LogoutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("log out");

        HttpSession session = request.getSession(false);
        if (session != null) {
            session.invalidate();
            System.out.println("invalidate");
        }

        System.out.println("before redirect");
        response.sendRedirect("index.jsp");
    }
}
