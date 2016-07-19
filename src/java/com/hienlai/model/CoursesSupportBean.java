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
    private String courseName;
    private int offeringId;
    private int courseCapacity;

    public CoursesSupportBean(int courseId, String courseName, int offeringId) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.offeringId = offeringId;
    }

    public CoursesSupportBean(int courseId, String courseName, int offeringId, int courseCapacity) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.offeringId = offeringId;
        this.courseCapacity = courseCapacity;
    }

    public int getCourseCapacity() {
        return courseCapacity;
    }

    public void setCourseCapacity(int courseCapacity) {
        this.courseCapacity = courseCapacity;
    }
    
    public int getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(int offeringId) {
        this.offeringId = offeringId;
    }

    public int getCourseId() {
        return courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

}
