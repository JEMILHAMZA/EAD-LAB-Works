package com.bookstore;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DeleteBookServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try {
            DBConnectionManager dbManager = new DBConnectionManager();
            Connection conn = dbManager.openConnection();
            String query = "DELETE FROM Books WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            int rowsAffected = stmt.executeUpdate();

            PrintWriter out = response.getWriter();
            if (rowsAffected > 0) {
                out.println("Book deleted successfully!");
            } else {
                out.println("No book found with the given ID.");
            }

            dbManager.closeConnection(conn);
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("Error in deleting book.");
        }
    }
}
