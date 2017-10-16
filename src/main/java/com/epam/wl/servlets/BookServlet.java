package com.epam.wl.servlets;

import com.epam.wl.services.TestBookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "BookServlet", urlPatterns = "/catalog")
public class BookServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        TestBookService service = TestBookService.getInstance();

        HttpSession session = request.getSession(false);
        List<String> list = new ArrayList<>();
        list.add(session.getAttribute("userID").toString());

        request.setAttribute("identification", list);
        request.setAttribute("books", service.getBooks());
        request.getRequestDispatcher("catalog.jsp").forward(request, response);
    }
}