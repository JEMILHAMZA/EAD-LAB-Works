package com.bookstore;
// Name: Jemil Shikuri Hamza   ID:UGR/4296/14
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BookRegistrationServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        double price = Double.parseDouble(request.getParameter("price"));

        try {
            DBConnectionManager dbManager = new DBConnectionManager();
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
