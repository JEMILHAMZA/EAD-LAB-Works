package com.bookstore;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.XmlWebApplicationContext;

public class BookRegistrationServlet extends HttpServlet {
    @Autowired
    private DBConnectionManager dbManager;

    @Override
    public void init() throws ServletException {
        // Load Spring context and initialize bean
        XmlWebApplicationContext context = new XmlWebApplicationContext();
        context.setConfigLocation("classpath:applicationContext.xml");
        context.refresh();
        context.getAutowireCapableBeanFactory().autowireBean(this);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));

        try {
            Connection conn = dbManager.openConnection();
            String query = "INSERT INTO Books (title, author, price) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setDouble(3, price);
            stmt.executeUpdate();
            dbManager.closeConnection(conn);
            response.getWriter().println("Book Registered Successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error in book registration.");
        }
    }
}
