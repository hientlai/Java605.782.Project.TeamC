/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.com.hienlai.dao;

import java.com.hienlai.model.User;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hien
 */
public class FacultyDAOImpl implements FacultyDAO {

    private Connection conn;

    public FacultyDAOImpl(Connection conn) {

        this.conn = conn;
    }

    @Override
    public boolean isUserIdPasswordMatch(String userId, String password) {
        System.out.println("Check the faculty is exist by userid and password");
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("SELECT count(*) as nost FROM FACULTY WHERE USERID = ? AND PASSWORD = ?");
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
                Logger.getLogger(FacultyDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean insert(String firstName, String lastName, String ssn, String email, String address, String userId, String password) {
        System.out.println("Insert faculty's record to database.");
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO FACULTY(FIRST_NAME, LAST_NAME, SSN, EMAIL, ADDRESS, USERID, PASSWORD) VALUES (?,?,?,?,?,?,?)");
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
            Logger.getLogger(FacultyDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(FacultyDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
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
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("SELECT FIRST_NAME, LAST_NAME FROM FACULTY WHERE USERID = ?");
            pstmt.setString(1, userId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                firstName = resultSet.getString("FIRST_NAME");
                lastName = resultSet.getString("LAST_NAME");
                return new User(firstName,lastName,null,null,null,null,null,null);
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
                Logger.getLogger(FacultyDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
