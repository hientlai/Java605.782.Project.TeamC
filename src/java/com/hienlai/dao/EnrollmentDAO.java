/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.dao;

import java.util.List;

import com.hienlai.model.StudentGradeBean;

/**
 * @author Hien
 */
public interface EnrollmentDAO { 
    boolean insertEnrollment(String status, String student_id, String offering_id);
    
    List<StudentGradeBean> getStudentGrades(String offeringId);
    
    boolean updateEnrollement(String enrollId, String grade);
}
