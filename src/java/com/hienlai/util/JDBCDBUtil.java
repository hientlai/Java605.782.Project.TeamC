/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.util;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * this class for JDBC
 *
 * @author Hien
 */
public class JDBCDBUtil {

    private static Connection conn;

    public static Connection getConnection(){
        if (conn != null) {
            return conn;
        }

        InputStream inputStream = JDBCDBUtil.class.getClassLoader().getResourceAsStream("db.properties");
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
            String driver = properties.getProperty("driver");
            String url = properties.getProperty("url");
            String user = properties.getProperty("user");
            String pwd = properties.getProperty("password");
            
                Class.forName(driver);
                conn = (Connection) DriverManager.getConnection(url, user, pwd);
                System.out.println("Connected to the database");
        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        return conn;
    }

    /**
     * close connection
     *
     * @param toBeClosed
     */
    public static void closeConnection(Connection toBeClosed) {
        if (toBeClosed == null) {
            return;
        }
        try {
            toBeClosed.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
