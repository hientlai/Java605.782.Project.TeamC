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
public class StaffDAOImpl implements StaffDAO {

    private Connection conn;

    public StaffDAOImpl(Connection conn) {

        this.conn = conn;
    }

    /**
     * Check if user is administrator or not
     * @param userId
     * @return boolean
     */
    public boolean isAdministrator(String userId) {
        System.out.println("Check the user exists by userID");
        ResultSet resultSet;
        int count = 0;

        try {
            Statement statement = conn.createStatement();
            String sqlStatement = "SELECT count(*) AS adminUser FROM staff WHERE userid = ";

            resultSet = statement.executeQuery(sqlStatement + userId + ";");

            while (resultSet.next()) {
                count = resultSet.getFetchSize();
            }

            if (count == 1)
                return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    /**
     * Remove current user from staff/admin table
     * @param userId
     * @return boolean
     */
    public boolean removeAdministrator(String userId) {
        ResultSet resultSet;
        int count = 0;

        try {
            Statement statement = conn.createStatement();
            String sqlStatement = "SELECT * FROM staff WHERE userid = ";

            resultSet = statement.executeQuery(sqlStatement + userId + ";");

            while (resultSet.next()) {
                count = resultSet.getFetchSize();
            }

            if (count == 1) {
                String srmAdminStatement = "INSERT INTO staff (ssn, first_name, last_name" +
                        ", email, address, userid, password) VALUES ( ?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement = conn.prepareStatement(srmAdminStatement);
                preparedStatement.setString(1, userId);

                // TODO: Create more setStrings for all column names

                preparedStatement.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }


    /**
     * Add current user as admin
     * @param userId
     * @return
     */
    public boolean addAdministrator(String userId) {

        return false;
    }

    @Override
    public boolean isUserIdPasswordMatch(String userId, String password) {
        System.out.println("Check the staff is exist by userid and password");
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("SELECT count(*) as nost FROM STAFF WHERE USERID = ? AND PASSWORD = ?");
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
                Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return false;
    }

    @Override
    public boolean insert(String firstName, String lastName, String ssn, String email, String address, String userId, String password) {
        System.out.println("Insert staff's record to database.");
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO STAFF(FIRST_NAME, LAST_NAME, SSN, EMAIL, ADDRESS, USERID, PASSWORD) VALUES (?,?,?,?,?,?,?)");
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
            Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
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
            pstmt = conn.prepareStatement("SELECT FIRST_NAME, LAST_NAME FROM STAFF WHERE USERID = ?");
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
                Logger.getLogger(StaffDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }
}
