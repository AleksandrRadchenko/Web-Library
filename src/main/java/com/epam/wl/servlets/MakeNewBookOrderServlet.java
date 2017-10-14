package com.epam.wl.servlets;

import com.epam.wl.enums.BookOption;
import com.epam.wl.enums.UserOrderStatus;
import com.epam.wl.services.BookOrderService;
import com.epam.wl.services.UserOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MakeNewBookOrderServlet", urlPatterns = "/makeBookOrder")
public class MakeNewBookOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int userOrderId = Integer.valueOf(request.getParameter("userOrderId"));
        int bookInstanceId = Integer.valueOf(request.getParameter("bookInstanceId"));
        BookOption bookOption = BookOption.valueOf(request.getParameter("bookOption"));

        BookOrderService bookOrderService = BookOrderService.getInstance();
        bookOrderService.create(bookInstanceId, userOrderId, bookOption);

        UserOrderService userOrderService = UserOrderService.getInstance();
        userOrderService.setUserOrderStatus(userOrderId, UserOrderStatus.IN_PROGRESS);

        request.getRequestDispatcher("/librarian").forward(request, response);
    }
}
