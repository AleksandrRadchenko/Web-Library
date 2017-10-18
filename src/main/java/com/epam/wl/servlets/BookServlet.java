package com.epam.wl.servlets;

import com.epam.wl.entities.Book;
import com.epam.wl.entities.User;
import com.epam.wl.services.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@WebServlet(name = "BookServlet", urlPatterns = "/catalog")
public class BookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            BookService service = BookService.getInstance();
            HttpSession session = request.getSession(false);

            if (session.getAttribute("currentSessionUser") != null) {
                User user = (User) session.getAttribute("currentSessionUser");
                request.setAttribute("identification", user);

                List<Book> bookList = service.getBooks();
                Map<Book, List<Integer>> bookMap = service.getBookAndFreeBookInstanceMap(bookList);
                request.setAttribute("bookMap", bookMap);

                request.getRequestDispatcher("catalog.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException | IllegalArgumentException e) {
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }
}