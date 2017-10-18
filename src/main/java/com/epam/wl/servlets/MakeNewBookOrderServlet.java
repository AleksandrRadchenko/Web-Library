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
import java.sql.SQLException;

@WebServlet(name = "MakeNewBookOrderServlet", urlPatterns = "/makeBookOrder")
public class MakeNewBookOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final int userOrderId = Integer.valueOf(request.getParameter("userOrderId"));
            final int bookInstanceId = Integer.valueOf(request.getParameter("bookInstanceId"));
            final BookOption bookOption = BookOption.valueOf(request.getParameter("bookOption"));
            request.removeAttribute("userOrderId");
            request.removeAttribute("bookInstanceId");
            request.removeAttribute("bookOption");

            final BookOrderService bookOrderService = BookOrderService.getInstance();
            bookOrderService.create(bookInstanceId, userOrderId, bookOption);

            final UserOrderService userOrderService = UserOrderService.getInstance();
            userOrderService.setUserOrderStatus(userOrderId, UserOrderStatus.IN_PROGRESS);

            response.sendRedirect("/librarian");
        } catch (SQLException | IllegalArgumentException e) {
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
