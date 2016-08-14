/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jhu.teamc.model;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author Hien
 */
public class CoursesSupportBean implements Serializable {

    private int courseId;
    private String courseName;
    private int offeringId;
    private int courseCapacity;
    private double grade;
    private double credits;
    private Date enrollmentDate;
    private int enrollmentId;

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

    public CoursesSupportBean() {

    }

    public int getEnrollmentId() {
        return enrollmentId;
    }

    public void setEnrollmentId(int enrollmentId) {
        this.enrollmentId = enrollmentId;
    }

    public Date getEnrollmentDate() {
        return enrollmentDate;
    }

    public void setEnrollmentDate(Date enrollmentDate) {
        this.enrollmentDate = enrollmentDate;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public double getCredits() {
        return credits;
    }

    public void setCredits(double credits) {
        this.credits = credits;
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