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
public interface CourseDAO {

    /**
     * retrieves courses
     *
     * @return
     */
    List<CoursesSupportBean> retrieveCourses();

    /**
     * retrieve course by course id
     *
     * @param courseId
     * @return
     */
    CoursesSupportBean retrieveCourse(int courseId);

    /**
     * check course is exist
     *
     * @param courseId
     * @return
     */
    boolean isCourseExist(int courseId);

    /**
     * Insert a course to database
     *
     * @param courseId
     * @param courseName
     * @return
     */
    boolean insertCourse(int courseId, String courseName);
}
