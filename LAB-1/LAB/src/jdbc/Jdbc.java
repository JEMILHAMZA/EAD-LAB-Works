package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Jdbc {

    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/";
        String username = "root";  
        String password = "2020";    

        try {
            // Task 1: Connect to MySQL Database, Create Database, and Table
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            // Create database
            statement.execute("CREATE DATABASE IF NOT EXISTS StudentsDB");
            // Use the database
            statement.execute("USE StudentsDB");
            

            


            // Create table
            statement.execute("CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY, firstname VARCHAR(255), lastname VARCHAR(255), grade INT)");

            // Task 2: Insert Data
            insertSampleData(connection);

            // Task 3: Retrieve Data
            retrieveData(connection);

            // Task 4: Update Data
            updateStudentName(connection, 1, "UpdatedFirstName");

            // Task 5: Delete Data
            deleteStudent(connection, 2);

            // Task 6: Calculate Average Grade
            calculateAverageGrade(connection);

            // Close the resources
            statement.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void insertSampleData(Connection connection) {
        try {
            // Insert a single row
            PreparedStatement insertSingle = connection.prepareStatement("INSERT INTO students (id, firstname, lastname, grade) VALUES (?, ?, ?, ?)");
            insertSingle.setInt(1, 1);
            insertSingle.setString(2, "John");
            insertSingle.setString(3, "Doe");
            insertSingle.setInt(4, 90);
            insertSingle.executeUpdate();

            // Insert ten more rows
            for (int i = 2; i <= 11; i++) {
                PreparedStatement insertMore = connection.prepareStatement("INSERT INTO students (id, firstname, lastname, grade) VALUES (?, ?, ?, ?)");
                insertMore.setInt(1, i);
                insertMore.setString(2, "FirstName" + i);
                insertMore.setString(3, "LastName" + i);
                insertMore.setInt(4, 80 + (i % 10)); // Example grades
                insertMore.executeUpdate();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void retrieveData(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM students LIMIT 5");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstname = resultSet.getString("firstname");
                String lastname = resultSet.getString("lastname");
                int grade = resultSet.getInt("grade");
                System.out.println("ID: " + id + ", Name: " + firstname + " " + lastname + ", Grade: " + grade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void updateStudentName(Connection connection, int id, String newFirstName) {
        try {
            PreparedStatement updateStatement = connection.prepareStatement("UPDATE students SET firstname = ? WHERE id = ?");
            updateStatement.setString(1, newFirstName);
            updateStatement.setInt(2, id);
            updateStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void deleteStudent(Connection connection, int id) {
        try {
            PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM students WHERE id = ?");
            deleteStatement.setInt(1, id);
            deleteStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void calculateAverageGrade(Connection connection) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT AVG(grade) AS average_grade FROM students");
            while (resultSet.next()) {
                double averageGrade = resultSet.getDouble("average_grade");
                System.out.println("Average Grade: " + averageGrade);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
