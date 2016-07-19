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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Hien
 */
public class OfferingDAOImpl implements OfferingDAO {

    private Connection conn;

    public OfferingDAOImpl(Connection conn) {

        this.conn = conn;
    }

    @Override
    public List<CoursesSupportBean> retrieveOfferingByTermYear(String term, String year) {
        System.out.println("Retrieve courses lists from database with term: " + term + " and year: " + year);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int course_id;
        String course_name;
        int offering_id;
        List<CoursesSupportBean> courses = new ArrayList<CoursesSupportBean>();
        try {
            pstmt = conn.prepareStatement("SELECT C.COURSE_ID, C.COURSE_NAME, O.OFFERING_ID FROM COURSES AS C JOIN OFFERING AS O on C.COURSE_ID = O.COURSE_ID where O.TERM=? and O.YEAR=?");
            pstmt.setString(1, term);
            pstmt.setString(2, year);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                course_id = resultSet.getInt("course_id");
                course_name = resultSet.getString("course_name");
                offering_id =  resultSet.getInt("offering_id");
                CoursesSupportBean bean = new CoursesSupportBean(course_id, course_name,offering_id);
                courses.add(bean);
            }

            return courses;
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

        @Override
    public int retrieveStudentsRegistered(int offeringId) {
        System.out.println("Retrieve the number of student registered on the specific course");
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int numStudents = 0;
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("SELECT NUMBER_OF_STUDENTS_ENROLLMENT FROM OFFERING WHERE OFFERING_ID = ?");
            pstmt.setInt(1, offeringId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                numStudents = resultSet.getInt("NUMBER_OF_STUDENTS_ENROLLMENT");
            }
            System.out.println("numStudents " + numStudents);
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
    public boolean updateNumberStudentsRegistered(int offeringId, int numberStudentsRegistered) {
        System.out.println("Update number of student registered " + offeringId + " " + numberStudentsRegistered);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int numStudents = 0;
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("UPDATE OFFERING SET NUMBER_OF_STUDENTS_ENROLLMENT  = ? WHERE OFFERING_ID = ?");
            pstmt.setInt(1, numberStudentsRegistered);
            pstmt.setInt(2, offeringId);
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
    public CoursesSupportBean retrieveCourse(int offeringId) {
       System.out.println("Retrieve courses lists from database with offeringId: " + offeringId);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int courseId;
        String courseName;
        int courseCapacity;
        try {
            pstmt = conn.prepareStatement("SELECT C.COURSE_ID, C.COURSE_NAME, O.OFFERING_ID,O.COURSE_CAPACITY FROM COURSES AS C JOIN OFFERING AS O on C.COURSE_ID = O.COURSE_ID WHERE O.OFFERING_ID = ?");
            pstmt.setInt(1, offeringId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                courseId = resultSet.getInt("COURSE_ID");
                courseName = resultSet.getString("COURSE_NAME");
                courseCapacity = resultSet.getInt("COURSE_CAPACITY");
                CoursesSupportBean bean = new CoursesSupportBean(courseId, courseName, offeringId,courseCapacity);
                return bean;
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
        return null;
    }
}
