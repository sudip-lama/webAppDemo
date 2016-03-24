/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;

import db.assignment1.db.ConnectionProvider;
import db.assignment1.model.Student;
import db.assignment1.service.StudentDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SUDIP
 */
@Repository
public class StudentDaoImp implements StudentDao{

    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public List<Student> getStudentsByDepartment(String deparment) {
       List<Student> students = new ArrayList<>();
                String sql = "SELECT * FROM students WHERE dept= ?";
                
                students=jdbcTemplate.query(sql, new Object[]{deparment},
                        new RowMapper<Student>() {

           @Override
           public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
               Student student=new Student(rs.getInt("id"),rs.getString("name"));
               return student;
           }
       });
               return students;
    }
    
}
