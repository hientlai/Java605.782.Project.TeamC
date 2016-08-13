/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jhu.teamc.dao;

import edu.jhu.teamc.model.CoursesSupportBean;
import java.util.List;

/**
 *
 * @author Hien
 */
public interface OfferingDAO {

    /**
     * retrieves courses
     *
     * @return
     */
    public List<CoursesSupportBean> retrieveOfferingByTermYear(String term, String year);
    
    /**
     * retrieve offering by term, year, location and faculty id
     * @param term
     * @param year
     * @param location
     * @param facultyId
     * @return 
     */
    public List<CoursesSupportBean> retrieveOfferingByTermYearLocationFaculty(String term, String year, String location, String facultyId);
    
    /**
     * retrieve current offering by teacher id
     * @param teacherId
     * @return 
     */
    public List<CoursesSupportBean> retrieveCurrentOfferingByTeacherId(String teacherId);

    /**
     * Check if the course exists
     * @param course_id
     * @return
     */
    public boolean checkCourseExists(int course_id);
    
    /**
     * check if the offering exists
     * @param term
     * @param year
     * @param location
     * @param facultyId
     * @param courseId
     * @return 
     */
    public boolean checkOfferingExists(String term, String year, String location, String facultyId, String courseId);
    

    /**
     * Override the course capacity
     * @param courseId
     * @param overrideNum
     * @return
     */
    public boolean overrideSelectedCourseCapacty(int courseId, int overrideNum);

        /**
     * retrieve students registered
     *
     * @param courseId
     * @return
     */
    public int retrieveStudentsRegistered(int offeringId);
    
    /**
     * update number student registered
     *
     * @param courseId
     * @param numberStudentsRegistered
     * @return
     */
    public boolean updateNumberStudentsRegistered(int offeringId, int numberStudentsRegistered);

    /**
     * Retrieve offering by offering id
     * @param offeringId
     * @return 
     */
    public CoursesSupportBean retrieveCourse(int offeringId);
    
    /**
     * insert offering
     * @param term
     * @param year
     * @param location
     * @param courseCapacity
     * @param courseId
     * @param facultyId
     * @param staffId 
     */
    public boolean insert(String term, String year, String location, String courseCapacity, String courseId, String facultyId, String staffId);
}
