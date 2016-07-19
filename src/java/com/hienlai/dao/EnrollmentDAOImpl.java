/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.dao;

import com.hienlai.model.CoursesSupportBean;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
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
            if (pstmt.executeUpdate() == 1) {
                System.out.println("Insert the enrollment successfully");
            } else {
                System.out.println("Insert the enrollment fail");
            }

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

    @Override
    public boolean isEnrolled(int studentId, int offeringId) {
        System.out.println("Check student enrolls in the class student id: " + studentId + " and offering id =: " + offeringId);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int noen = 0;
        try {
            pstmt = conn.prepareStatement("SELECT COUNT(*) AS NOEN FROM ENROLLMENT WHERE STUDENT_ID =? AND OFFERING_ID = ?");
            pstmt.setInt(1, studentId);
            pstmt.setInt(2, offeringId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                noen = resultSet.getInt("NOEN");
                continue;
            }
            if (noen > 0) {
                return true;
            } else {
                return false;
            }
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
        return false;
    }

    @Override
    public List<CoursesSupportBean> getGrades(int studentId) {
        System.out.println("Get list of courses where studentId =: " + studentId);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<CoursesSupportBean> gradeList = new ArrayList<CoursesSupportBean>(5);
        try {
            pstmt = conn.prepareStatement("SELECT C.CREDITS, E.GRADE, C.COURSE_ID, C.COURSE_NAME FROM OFFERING AS O JOIN COURSES AS C  on C.COURSE_ID = O.COURSE_ID  JOIN ENROLLMENT AS E ON E.OFFERING_ID=O.OFFERING_ID WHERE E.STUDENT_ID = ?");
            pstmt.setInt(1, studentId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                CoursesSupportBean bean = new CoursesSupportBean();
                bean.setCourseId(resultSet.getInt("COURSE_ID"));
                bean.setCourseName(resultSet.getString("COURSE_NAME"));
                bean.setGrade(resultSet.getDouble("GRADE"));
                bean.setCredits(resultSet.getInt("CREDITS"));

                gradeList.add(bean);
            }

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

        return gradeList;
    }
}
