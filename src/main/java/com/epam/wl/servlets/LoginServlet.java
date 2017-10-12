package com.epam.wl.servlets;

import com.epam.wl.dao.UserDAO;
import com.epam.wl.entities.User;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@javax.servlet.annotation.WebServlet(name = "LoginServlet", urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        UserDAO userDAO = new UserDAO(getEmbeddedDatabase());

        try {
            Optional<User> userOptional = userDAO.getUserByEmailAndPassword(request.getParameter("email"),
                    request.getParameter("password"));

            if (userOptional.isPresent()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", userOptional.get());

                if ("on".equals(request.getParameter("is_librarian"))) {
                    response.sendRedirect("librarian_from_login.jsp");
                } else {
                    response.sendRedirect("user_from_login.jsp");
                }
            } else {
                response.sendRedirect("login_error.jsp");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static EmbeddedDatabase getEmbeddedDatabase() {
        final EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        final EmbeddedDatabase db = builder
                .setType(EmbeddedDatabaseType.H2)
                .addScript("H2DBinit.sql")
                .addScript("H2DBdata.sql")
                .build();
        return db;
    }
}
