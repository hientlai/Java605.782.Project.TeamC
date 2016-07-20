/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Hien
 */
public class EnrollmentDAOImpl implements EnrollmentDAO {
    private Connection conn;

    public EnrollmentDAOImpl(Connection conn) {

        this.conn = conn;
    }

    @Override
    public boolean insertEnrollment(String status, String student_id, String offering_id) {
        System.out.println("Insert enrollment's record to database with student id: " + student_id + " and offering_id =: " + offering_id);
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO ENROLLMENT (STATUS, STUDENT_ID, OFFERING_ID) VALUES (?, ?, ?)");
            pstmt.setString(1, status);
            pstmt.setString(2, student_id);
            pstmt.setString(3, offering_id);
            if (pstmt.executeUpdate() == 1)
                System.out.println("Insert the enrollment successfully");
            else
                System.out.println("Insert the enrollment fail");

        } catch (SQLException ex) {
            System.out.println("Insert the enrollment fail");
            Logger.getLogger(EnrollmentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(EnrollmentDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return true;
    }

}
