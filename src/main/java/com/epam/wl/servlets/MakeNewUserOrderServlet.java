package com.epam.wl.servlets;

import com.epam.wl.entities.User;
import com.epam.wl.services.UserOrderService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MakeNewUserOrderServlet", urlPatterns = "/userorderfromcatalog")
public class MakeNewUserOrderServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int bookId = Integer.valueOf(request.getParameter("bookId"));
        HttpSession session = request.getSession(false);
        int userId = ((User) session.getAttribute("currentSessionUser")).getId();

        session.setAttribute("bookID", bookId);
        UserOrderService userOrderService = UserOrderService.getInstance();
        userOrderService.createNewUserOrder(bookId, userId);//userId

        request.getRequestDispatcher("/userprofile").forward(request, response);

    }
}
