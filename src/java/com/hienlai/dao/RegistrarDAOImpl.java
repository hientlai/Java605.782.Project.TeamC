/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.dao;

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
public class RegistrarDAOImpl implements RegistrarDAO {

    private Connection conn;

    public RegistrarDAOImpl(Connection conn) {

        this.conn = conn;
    }

    @Override
    public int retrieveStudentsRegistered(int courseId) {
        System.out.println("Retrieve the number of student registered on the specific course");
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int numStudents = 0;
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("SELECT NUMBER_STUDENTS_REGISTRED FROM REGISTRAR WHERE courseId = ?");
            pstmt.setInt(1, courseId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                numStudents = resultSet.getInt("NUMBER_STUDENTS_REGISTRED");
            }
            return numStudents;

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
        return 0;
    }

    @Override
    public boolean updateNumberStudentsRegistered(int courseId, int numberStudentsRegistered) {
        System.out.println("Update number of student registered " + courseId + " " + numberStudentsRegistered);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int numStudents = 0;
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("UPDATE REGISTRAR SET NUMBER_STUDENTS_REGISTRED  = ? WHERE COURSEID = ?");
            pstmt.setInt(1, numberStudentsRegistered);
            pstmt.setInt(2, courseId);
            pstmt.executeUpdate();
            return true;

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
    public boolean insertStudentRegistered(int courseId, int numberStudentsRegistered) {
        System.out.println("Insert number of student registered " + courseId + " " + numberStudentsRegistered);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("INSERT INTO REGISTRAR(COURSEID, NUMBER_STUDENTS_REGISTRED) VALUES(?,?)");
            pstmt.setInt(1, courseId);
            pstmt.setInt(2, numberStudentsRegistered);
            pstmt.executeUpdate();
            return true;

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

}
