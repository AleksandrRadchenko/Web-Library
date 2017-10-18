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

/**
 * Servlet for searching books by author.
 */
@WebServlet(name = "SearchByAuthorServlet", urlPatterns = "/authorsearch")
public class SearchByAuthorServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            final String author = request.getParameter("author");
            BookService service = BookService.getInstance();

            HttpSession session = request.getSession(false);
            if (session != null) {
                User user = (User) session.getAttribute("currentSessionUser");
                request.setAttribute("identification", user);

                List<Book> bookList = service.getBookByAuthor(author);
                Map<Book, List<Integer>> bookMap = service.getBookAndFreeBookInstanceMap(bookList);
                request.setAttribute("bookMap", bookMap);

                request.setAttribute("authorName", author);
                request.getRequestDispatcher("searchByAuthor.jsp").forward(request, response);
            } else {
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }
        } catch (SQLException | IllegalArgumentException e) {
            e.printStackTrace();
            request.getRequestDispatcher("errors/error500.html").forward(request, response);
        }
    }
}