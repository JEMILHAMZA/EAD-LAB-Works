package com.example.bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/display-books")
public class DisplayBooksServlet extends HttpServlet {
    private final DBConnectionManager dbManager = new DBConnectionManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter writer = resp.getWriter();

        try (Connection connection = dbManager.openConnection()) {
            String query = "SELECT * FROM Books";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            ResultSet resultSet = preparedStatement.executeQuery();

            writer.println("<html><body><h1>All Books</h1><table border='1'>");
            writer.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th></tr>");
            while (resultSet.next()) {
                writer.println("<tr>");
                writer.println("<td>" + resultSet.getInt("id") + "</td>");
                writer.println("<td>" + resultSet.getString("title") + "</td>");
                writer.println("<td>" + resultSet.getString("author") + "</td>");
                writer.println("<td>" + resultSet.getDate("price") + "</td>");
                writer.println("</tr>");
            }
            writer.println("</table></body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            resp.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving books.");
        }
    }
}
