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
public class CourseDAOImpl implements CourseDAO {

    private Connection conn;

    public CourseDAOImpl(Connection conn) {

        this.conn = conn;
    }

    @Override
    public List<CoursesSupportBean> retrieveCourses() {
        System.out.println("Retrieve courses lists from database");
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int courseId;
        String course_name;
        List<CoursesSupportBean> courses = new ArrayList<CoursesSupportBean>();
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("SELECT courseId, course_name FROM COURSES");
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                courseId = resultSet.getInt("courseId");
                course_name = resultSet.getString("course_name");
                CoursesSupportBean bean = new CoursesSupportBean(courseId, course_name,0);
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
                Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public CoursesSupportBean retrieveCourse(int courseId) {
        System.out.println("Retrieve course from database " + courseId);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        String course_name;
        CoursesSupportBean bean = null;
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("SELECT course_id, course_name FROM COURSES WHERE course_id = ?");
            pstmt.setInt(1, courseId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                courseId = resultSet.getInt("course_id");
                course_name = resultSet.getString("course_name");
                bean = new CoursesSupportBean(courseId, course_name,0);
            }

            return bean;
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
                Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public boolean isCourseExist(int courseId) {
        System.out.println("Check the course is exist by courseId");
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int count = 0;
        try {
            Statement statement = conn.createStatement();
            pstmt = conn.prepareStatement("SELECT count(*) as nost FROM COURSES WHERE COURSEID = ?");
            pstmt.setInt(1, courseId);
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
    public boolean insertCourse(int courseId, String courseName) {
        System.out.println("Insert course's record to database.");
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO COURSES (COURSEID, COURSE_NAME) VALUES (?, ?)");
            pstmt.setInt(1, courseId);
            pstmt.setString(2, courseName);
            
            if(pstmt.executeUpdate() == 1)
                System.out.println("Insert the course id: " + courseId + " successfully");
            else 
                System.out.println("Insert the course id: " + courseId + " fail");
            
        } catch (SQLException ex) {
             System.out.println("Insert the course id: " + courseId + " fail");
            Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            if (pstmt != null) {
                try {
                    pstmt.close();
                } catch (SQLException ex) {
                    Logger.getLogger(CourseDAOImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

        return true;
    }

}
