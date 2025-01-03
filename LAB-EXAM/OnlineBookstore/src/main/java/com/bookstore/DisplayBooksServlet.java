package com.bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DisplayBooksServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            DBConnectionManager dbManager = new DBConnectionManager();
            Connection conn = dbManager.openConnection();
            String query = "SELECT * FROM Books";
            PreparedStatement stmt = conn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();

            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<table><tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th></tr>");

            while (rs.next()) {
                out.println("<tr><td>" + rs.getInt("id") + "</td><td>" + rs.getString("title") + "</td><td>" + rs.getString("author") + "</td><td>" + rs.getDouble("price") + "</td></tr>");
            }

            out.println("</table>");
            dbManager.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
