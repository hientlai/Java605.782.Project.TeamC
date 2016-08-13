/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.jhu.teamc.dao;

import edu.jhu.teamc.model.FacultySupportBean;
import java.util.List;

/**
 *
 * @author Hien
 */
public interface FacultyDAO extends UserDAO {

    /**
     * retrieve all faculties
     *
     * @return
     */
    public List<FacultySupportBean> retrieveFaculties();
}
