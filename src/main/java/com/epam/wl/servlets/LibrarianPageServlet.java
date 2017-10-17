package com.epam.wl.servlets;

import com.epam.wl.entities.UserOrder;
import com.epam.wl.services.UserOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "LibrarianPageServlet", urlPatterns = "/librarian")
public class LibrarianPageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserOrderService service = UserOrderService.getInstance();

        try {
            List<UserOrder> userOrderList = service.getNewUserOrders();
            Map<UserOrder, List<Integer>> userOrderListMap = service.getUserOrderAndFreeBookInstanceMap(userOrderList);

            request.setAttribute("userOrderMap", userOrderListMap);
            request.getRequestDispatcher("librarian.jsp").forward(request, response);
        } catch (SQLException | IllegalArgumentException e){
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }
}
