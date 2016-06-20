/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.model;

import java.io.Serializable;

/**
 *
 * @author Hien
 */
public class CoursesSupportBean implements Serializable {

    private int courseId;
    private String course_name;

    public CoursesSupportBean(int courseId, String course_name) {
        this.courseId = courseId;
        this.course_name = course_name;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

}
