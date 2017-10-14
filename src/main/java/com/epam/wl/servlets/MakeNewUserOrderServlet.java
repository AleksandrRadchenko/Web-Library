package com.epam.wl.servlets;

import com.epam.wl.services.UserOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MakeNewUserOrderServlet")
public class MakeNewUserOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userId = Integer.valueOf(request.getParameter("userId"));
        int bookId = Integer.valueOf(request.getParameter("bookId"));

        UserOrderService userOrderService = UserOrderService.getInstance();
        userOrderService.createNewUserOrder(bookId, userId);

        request.getRequestDispatcher("/catalog").forward(request, response);

    }
}
