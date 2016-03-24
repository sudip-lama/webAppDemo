/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.model.Student;
import db.assignment1.service.StudentDao;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SUDIP
 */
@Component
@ManagedBean
@RequestScoped
public class StudentController {

   @Autowired
    private StudentDao studentDao;
    private List<Student> students;
    /**
     * Creates a new instance of StudentController
     */
    public StudentController() {
    }

    public StudentDao getStudentDao() {
        return studentDao;
    }

    public void setStudentDao(StudentDao studentDao) {
        this.studentDao = studentDao;
    }

    public List<Student> getStudents() {
        students=studentDao.getStudentsByDepartment("CS");
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
    
}
