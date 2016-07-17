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

    private int course_id;
    private String course_name;
    private int offering_id;

    public CoursesSupportBean(int course_id, String course_name,int offering_id) {
        this.course_id = course_id;
        this.course_name = course_name;
        this.offering_id = offering_id;
    }

    public int getOffering_id() {
        return offering_id;
    }

    public void setOffering_id(int offering_id) {
        this.offering_id = offering_id;
    }

    
    public int getCourse_id() {
        return course_id;
    }

    public String getCourse_name() {
        return course_name;
    }

    public void setCourse_id(int course_id) {
        this.course_id = course_id;
    }

    public void setCourse_name(String course_name) {
        this.course_name = course_name;
    }

}
