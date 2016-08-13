package edu.jhu.teamc.model;

import java.io.Serializable;

public class StudentGradeBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String fname;
    private String lname;
    private double grade;
    private int enrollId;

    public StudentGradeBean(String fname, String lname, double grade, int id) {
        this.fname = fname;
        this.lname = lname;
        this.grade = grade;
        this.enrollId = id;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getId() {
        return enrollId;
    }

    public void setId(int id) {
        this.enrollId = id;
    }

}
