/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.service;

import db.assignment1.model.Student;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public interface StudentDao {
    public List<Student> getStudentsByDepartment(String deparment);
}
