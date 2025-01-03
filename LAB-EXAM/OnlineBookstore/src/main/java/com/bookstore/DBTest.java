package com.bookstore;



public class DBTest {
    public static void main(String[] args) {
        DBConnectionManager dbManager = new DBConnectionManager();
        dbManager.connect(); // Test connection
        dbManager.disconnect(); // Close connection
    }
}
