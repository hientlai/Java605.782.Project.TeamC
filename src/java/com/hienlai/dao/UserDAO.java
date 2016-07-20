/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.com.hienlai.dao;

import java.com.hienlai.model.User;

/**
 *
 * @author Hien
 */
public interface UserDAO {

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
    public User getUser(String userId);

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
    public boolean insert(String firstName, String lastName, String ssn, String email, String address, String userId, String password);

}
