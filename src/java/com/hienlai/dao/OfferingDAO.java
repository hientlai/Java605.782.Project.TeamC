/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.dao;

import com.hienlai.model.CoursesSupportBean;
import java.util.List;

/**
 * @author Hien
 */
public interface OfferingDAO {

    /**
     * retrieves courses
     *
     * @return
     */
    List<CoursesSupportBean> retrieveOfferingByTermYear(String term, String year);
    
    /**
     * retrieves courses
     *
     * @return
     */
    List<CoursesSupportBean> retrieveCurrentOfferingByTeacherId(String teacherId);


    /**
     * retrieve students registered
     *
     * @param courseId
     * @return
     */
    int retrieveStudentsRegistered(int offeringId);

    /**
     * update number student registered
     *
     * @param courseId
     * @param numberStudentsRegistered
     * @return
     */
    boolean updateNumberStudentsRegistered(int offeringId, int numberStudentsRegistered);

    /**
     * Retrieve offering by offering id
     *
     * @param offeringId
     * @return
     */
    CoursesSupportBean retrieveCourse(int offeringId);
}
