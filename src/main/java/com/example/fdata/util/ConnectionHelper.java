package com.example.fdata.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionHelper {

    private static Connection connection;

    public static Connection getConnection() throws SQLException, ClassNotFoundException{
        Class.forName(config.DATABASE_DRIVER_CLASS);
        if(connection == null){
            connection =
                DriverManager.getConnection(
                        config.DATABASE_URL,
                        config.DATABASE_USERNAME,
                        config.DATABASE_PASSWORD);
            System.out.println("Connect success");
        }else {
            System.out.println("Use existing connection");
        }

        return connection;
    }
}
