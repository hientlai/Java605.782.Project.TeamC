/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jhu.teamc.dao;

import edu.jhu.teamc.model.CoursesSupportBean;
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

    private final Connection conn;

    public OfferingDAOImpl(Connection conn) {

        this.conn = conn;
    }

    @Override
    public boolean overrideSelectedCourseCapacty(int offeringId, int overrideNum) {
        System.out.println("Override Selected Course");
        PreparedStatement pstmt;

        try {
            pstmt = conn.prepareStatement("UPDATE offering SET course_capacity=? WHERE offering_id=?");
            pstmt.setInt(1, overrideNum);
            pstmt.setInt(2, offeringId);
            int resultSet = pstmt.executeUpdate();

            if (resultSet == 1) {
                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    public boolean checkCourseExists(int offeringId) {
        System.out.println("Check if the course_id exists in the Offering database.");
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = conn.prepareStatement("SELECT offering_id FROM offering WHERE offering_id=?");
            pstmt.setInt(1, offeringId);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("offering_id") == offeringId) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
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
                offering_id = resultSet.getInt("offering_id");
                CoursesSupportBean bean = new CoursesSupportBean(course_id, course_name, offering_id);
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
    public List<CoursesSupportBean> retrieveOfferingByTermYearLocationFaculty(String term, String year, String location, String facultyId) {
        System.out.println("Retrieve courses lists from database with term: " + term + " and year: " + year + " location: " + location + " faculty id: " + facultyId);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int course_id;
        String course_name;
        int offering_id;
        List<CoursesSupportBean> courses = new ArrayList<CoursesSupportBean>();
        try {
            pstmt = conn.prepareStatement("SELECT C.COURSE_ID, C.COURSE_NAME, O.OFFERING_ID FROM COURSES AS C JOIN OFFERING AS O on C.COURSE_ID = O.COURSE_ID where O.TERM=? and O.YEAR=? and O.LOCATION= ? and O.FACULTY_ID = ?");
            pstmt.setString(1, term);
            pstmt.setString(2, year);
            pstmt.setString(3, location);
            pstmt.setString(4, facultyId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                course_id = resultSet.getInt("course_id");
                course_name = resultSet.getString("course_name");
                offering_id = resultSet.getInt("offering_id");
                CoursesSupportBean bean = new CoursesSupportBean(course_id, course_name, offering_id);
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
                CoursesSupportBean bean = new CoursesSupportBean(courseId, courseName, offeringId, courseCapacity);
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

    @Override
    public List<CoursesSupportBean> retrieveCurrentOfferingByTeacherId(String teacherId) {
        System.out.println("Retrieve courses lists from database with teacher id: " + teacherId);
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;
        int course_id;
        String course_name;
        int offering_id;
        List<CoursesSupportBean> courses = new ArrayList<CoursesSupportBean>();
        try {
            pstmt = conn.prepareStatement("SELECT C.COURSE_ID, C.COURSE_NAME, O.OFFERING_ID FROM COURSES AS C JOIN OFFERING AS O on C.COURSE_ID = O.COURSE_ID where O.FACULTY_ID=?");
            pstmt.setString(1, teacherId);
            resultSet = pstmt.executeQuery();
            while (resultSet.next()) {
                course_id = resultSet.getInt("course_id");
                course_name = resultSet.getString("course_name");
                offering_id = resultSet.getInt("offering_id");
                CoursesSupportBean bean = new CoursesSupportBean(course_id, course_name, offering_id);
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

    public CoursesSupportBean modifyCourseCapacity(int course_id, int offering) {
        System.out.println("Modify the course capacity from database with offering: " + offering + "and course_id: " + course_id);
        PreparedStatement pstmt;
        ResultSet resultSet;
        int courseId;
        String courseName;
        int courseCapacity;

        try {
            pstmt = conn.prepareStatement("SELECT course_id FROM offering WHERE course_id = ?");
            pstmt.setInt(1, course_id);
            resultSet = pstmt.executeQuery();

            if (resultSet.getFetchSize() == 1) {
                while (resultSet.next()) {
//                    PreparedStatement pstmt2 = conn.prepareStatement("UPDATE offering SET ");
                }
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }

        return null;
    }

    @Override
    public boolean insert(String term, String year, String location, String courseCapacity, String courseId, String facultyId, String staffId) {
        System.out.println("Insert course's record to database.");
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement("INSERT INTO offering(term,year,location,course_capacity,course_id,faculty_id,staff_id) VALUES (?,?,?,?,?,?,?)");
            pstmt.setString(1, term);
            pstmt.setString(2, year);
            pstmt.setString(3, location);
            pstmt.setString(4, courseCapacity);
            pstmt.setString(5, courseId);
            pstmt.setString(6, facultyId);
            pstmt.setString(7, staffId);

            if (pstmt.executeUpdate() == 1) {
                System.out.println("Insert the course id: " + courseId + " successfully");
            } else {
                System.out.println("Insert the course id: " + courseId + " fail");
            }

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

    @Override
    public boolean checkOfferingExists(String term, String year, String location, String facultyId, String courseId) {
        System.out.println("Check if the course_id exists in the Offering database.");
        PreparedStatement pstmt = null;
        ResultSet resultSet = null;

        try {
            pstmt = conn.prepareStatement("SELECT count(*) as nooffering FROM offering WHERE term =? and year =? and location=? and faculty_id =? and course_id=?");
            pstmt.setString(1, term);
            pstmt.setString(2, year);
            pstmt.setString(3, location);
            pstmt.setString(4, facultyId);
            pstmt.setString(5, courseId);
            resultSet = pstmt.executeQuery();

            while (resultSet.next()) {
                if (resultSet.getInt("nooffering") > 0) {
                    return true;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}