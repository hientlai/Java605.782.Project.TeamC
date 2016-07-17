/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.dao;

import com.hienlai.model.CoursesSupportBean;
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
}