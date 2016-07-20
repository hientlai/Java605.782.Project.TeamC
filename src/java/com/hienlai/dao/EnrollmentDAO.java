/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.com.hienlai.dao;


/**
 *
 * @author Hien
 */
public interface EnrollmentDAO { 
    public boolean insertEnrollment(String status, String student_id, String offering_id);
}
