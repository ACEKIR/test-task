package com.haulmont.testtask;

import com.vaadin.shared.ui.Connect;

import java.sql.*;

public class ConnectJDBC {

    static Connection connection = null;

    public static Connection getConnect(){

        try {
            Class.forName("org.hsqldb.jdbcDriver");
        } catch (ClassNotFoundException e) {
            System.err.println("НЕ удалось загрузить драйвер БД.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            connection = DriverManager.getConnection(
                    "jdbc:hsqldb:file:testdb", "SA", "");
        } catch (SQLException e) {
            System.err.println("НЕ удалось оздать соединение.");
            e.printStackTrace();
            System.exit(1);
        }
        return connection;
    }

    public static void disconnect(Statement statement) throws SQLException {
        statement = connection.createStatement();
        statement.execute("SHUTDOWN");
        if(statement!=null) {
            statement.close();
        }
        System.out.println("Database connection disestablished");
    }

}
