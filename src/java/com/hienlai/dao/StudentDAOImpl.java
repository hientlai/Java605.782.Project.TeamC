/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.dao;

import com.hienlai.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hien
 */
public class StudentDAOImpl implements StudentDAO {

    private Connection conn;

    public StudentDAOImpl(Connection conn) {

        this.conn = conn;
    }

    @Override
    public boolean isUserIdPasswordMatch(String userId, String password) {
        System.out.println("Check the student is exist by userid and password");
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("SELECT count(*) as nost FROM STUDENT WHERE USERID = ? AND PASSWORD = ?");
            pstmt.setString(1, userId);
            pstmt.setString(2, password);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                count = resultSet.getInt("nost");
            }
            if (count > 0) {
                return true;
            } else {
                return false;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean insert(String firstName, String lastName, String ssn, String email, String address, String userId, String password) {
        System.out.println("Insert student's record to database.");
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO STUDENT(FIRST_NAME, LAST_NAME, SSN, EMAIL, ADDRESS, USERID, PASSWORD) VALUES (?,?,?,?,?,?,?)");
            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, ssn);
            pstmt.setString(4, email);
            pstmt.setString(5, address);
            pstmt.setString(6, userId);
            pstmt.setString(7, password);

            int count = pstmt.executeUpdate();
            if(count == 1)
                return true;
            else 
                return false;
        } catch (SQLException ex) {
            Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return false;
    }

    @Override
    public User getUser(String userId) {
        System.out.println("Get Name of User");
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String firstName = null;
        String lastName = null;
        String id = null;
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("SELECT FIRST_NAME, LAST_NAME, STUDENT_ID FROM STUDENT WHERE USERID = ?");
            pstmt.setString(1, userId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                firstName = resultSet.getString("FIRST_NAME");
                lastName = resultSet.getString("LAST_NAME");
                id = resultSet.getString("STUDENT_ID");
                return new User(firstName,lastName,null,null,null,null,null,id);
            }
            return null;

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
            } catch (SQLException ex) {
                Logger.getLogger(StudentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
