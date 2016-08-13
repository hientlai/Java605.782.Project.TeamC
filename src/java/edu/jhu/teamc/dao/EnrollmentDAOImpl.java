/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jhu.teamc.dao;

import edu.jhu.teamc.model.CoursesSupportBean;
import edu.jhu.teamc.model.StudentGradeBean;

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
            pstmt = conn.prepareStatement("SELECT COUNT(*) AS NOEN FROM ENROLLMENT WHERE STUDENT_ID =? AND OFFERING_ID = ? AND STATUS='Active'");
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
            System.out.println("Check student enrolls in the class fail");
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
    public List<CoursesSupportBean> getEnrollments(int studentId) {
        System.out.println("Get list of courses where studentId =: " + studentId);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<CoursesSupportBean> gradeList = new ArrayList<CoursesSupportBean>(5);
        try {
            pstmt = conn.prepareStatement("SELECT E.ENROLLMENT_ID, E.REGISTRATION_DATE, C.CREDITS, E.GRADE, C.COURSE_ID, C.COURSE_NAME FROM OFFERING AS O JOIN COURSES AS C  on C.COURSE_ID = O.COURSE_ID  JOIN ENROLLMENT AS E ON E.OFFERING_ID=O.OFFERING_ID WHERE E.STATUS='Active' AND E.STUDENT_ID = ?");
            pstmt.setInt(1, studentId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                CoursesSupportBean bean = new CoursesSupportBean();
                bean.setCourseId(resultSet.getInt("COURSE_ID"));
                bean.setCourseName(resultSet.getString("COURSE_NAME"));
                bean.setGrade(resultSet.getDouble("GRADE"));
                bean.setCredits(resultSet.getInt("CREDITS"));
                bean.setEnrollmentDate(resultSet.getDate("REGISTRATION_DATE"));
                bean.setEnrollmentId(resultSet.getInt("ENROLLMENT_ID"));
                gradeList.add(bean);
            }

        } catch (SQLException ex) {
            System.out.println("Get list of enrollments fail");
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

    @Override
    public void updateStatusEnrollment(int enrollmentId, String status) {
        System.out.println("Update the enrollment with enrollment id: " + enrollmentId);
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("UPDATE ENROLLMENT SET STATUS = ? WHERE ENROLLMENT_ID = ?");
            pstmt.setString(1, status);
            pstmt.setInt(2, enrollmentId);
            if (pstmt.executeUpdate() == 1) {
                System.out.println("Update the enrollment successfully");
            } else {
                System.out.println("Update the enrollment fail");
            }

        } catch (SQLException ex) {
            System.out.println("Update the enrollment fail");
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

    }

    @Override
    public CoursesSupportBean getEnrollment(int enrollmentId) {
        System.out.println("Get the enrollment with enrollment id: " + enrollmentId);
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("SELECT * FROM ENROLLMENT WHERE ENROLLMENT_ID = ? AND STATUS = 'Active'");
            pstmt.setInt(1, enrollmentId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                CoursesSupportBean bean = new CoursesSupportBean();
                bean.setOfferingId(rs.getInt("OFFERING_ID"));
                return bean;
            }
        } catch (SQLException ex) {
            System.out.println("Get the enrollment fail");
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
        return null;
    }

    @Override
    public boolean updateEnrollementGrade(String enrollId, String grade) {
        System.out.println("update enrollment's record to database with enroll id: " + enrollId + " and grade =: " + grade);
        PreparedStatement pstmt = null;
        boolean success = false;
        try {
            pstmt = conn.prepareStatement("UPDATE ENROLLMENT SET GRADE=? WHERE ENROLLMENT_ID=?");
            pstmt.setString(1, grade);
            pstmt.setString(2, enrollId);
            if (pstmt.executeUpdate() == 1) {
                System.out.println("Update the enrollment successfully");
                success = true;
            } else {
                System.out.println("Update the enrollment fail");
            }

        } catch (SQLException ex) {
            System.out.println("Update the enrollment fail");
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

        return success;
    }

    @Override
    public List<StudentGradeBean> getStudentGrades(String offeringId) {
        System.out.println("Get list of students where offering_id =: " + offeringId);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        List<StudentGradeBean> gradeList = new ArrayList<StudentGradeBean>(5);
        String fname;
        String lname;
        double grade;
        int id;
        try {
            pstmt = conn.prepareStatement("SELECT S.FIRST_NAME, S.LAST_NAME, E.GRADE, E.ENROLLMENT_ID FROM STUDENT AS S JOIN ENROLLMENT AS E on S.STUDENT_ID = E.STUDENT_ID WHERE E.OFFERING_ID = ? AND E.STATUS = ?");
            pstmt.setString(1, offeringId);
            pstmt.setString(2, "Active");
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                fname = resultSet.getString("first_name");
                lname = resultSet.getString("last_name");
                grade = resultSet.getDouble("grade");
                id = resultSet.getInt("enrollment_id");
                StudentGradeBean bean = new StudentGradeBean(fname, lname, grade, id);
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
