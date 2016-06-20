/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.model;

/**
 *
 * @author Hien
 */
public class RegistrationSupportBean {

    private int courseId;
    private int number_students_registered;

    public int getCourseId() {
        return courseId;
    }

    public int getNumber_students_registered() {
        return number_students_registered;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public void setNumber_students_registered(int number_students_registered) {
        this.number_students_registered = number_students_registered;
    }

}
