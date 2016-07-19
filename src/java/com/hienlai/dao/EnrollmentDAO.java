/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hienlai.dao;


/**
 *
 * @author Hien
 */
public interface EnrollmentDAO { 
    
    /**
     * 
     * @param status
     * @param student_id
     * @param offering_id
     * @return 
     */
    public boolean insertEnrollment(String status, String student_id, String offering_id);
    
    /**
     * check the student enrolled in class or not
     * @param studentId
     * @param offeringId
     * @return 
     */
    public boolean isEnrolled(int studentId, int offeringId);
}
