package com.bookstore;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
public class DBConnectionManager {
    private String url = "jdbc:mysql://localhost:3306/BookstoreDB";
    private String user = "root";
    private String password = "password";

    public Connection openConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    public void closeConnection(Connection conn) throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }
}
