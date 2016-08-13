/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jhu.teamc.dao;

/**
 *
 * @author Hien
 */
public interface StaffDAO extends UserDAO {

    public boolean overrideSelectedCourseCapacty(int courseId, int overrideNum);

}
