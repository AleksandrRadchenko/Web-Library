package com.epam.wl.servlets;

import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.services.UserOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookOrderCloseServlet", urlPatterns = "/close_book_order")
public class BookOrderCloseServlet extends HttpServlet {
    private final UserOrderService userOrderService = UserOrderService.getInstance();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!request.getParameter("book_orderid").isEmpty()) {
            userOrderService.setUserOrderStatus(Integer.parseInt(request.getParameter("book_orderid")), UserOrderStatus.CLOSED);
        }
        response.sendRedirect("/book_order");
    }
}
