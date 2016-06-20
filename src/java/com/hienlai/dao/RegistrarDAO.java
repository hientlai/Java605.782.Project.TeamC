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
public interface RegistrarDAO {

    /**
     * retrieve studens registered
     *
     * @param courseId
     * @return
     */
    public int retrieveStudentsRegistered(int courseId);

    /**
     * update number student registered
     *
     * @param courseId
     * @param numberStudentsRegistered
     * @return
     */
    public boolean updateNumberStudentsRegistered(int courseId, int numberStudentsRegistered);

}
