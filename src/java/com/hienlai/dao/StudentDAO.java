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
public interface StudentDAO {

    /**
     * check userId and password are matching or not
     *
     * @param userId
     * @param password
     * @return
     */
    public boolean isUserIdPasswordMatch(String userId, String password);

    /**
     * get user full name
     *
     * @param userId
     * @return
     */
    public String getUserName(String userId);

    /**
     * insert student to database
     *
     * @param firstName
     * @param lastName
     * @param ssn
     * @param email
     * @param address
     * @param userId
     * @param password
     * @return
     */
    public boolean insertStudent(String firstName, String lastName, String ssn, String email, String address, String userId, String password);

}
