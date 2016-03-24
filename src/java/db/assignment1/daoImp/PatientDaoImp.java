/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.PatientDao;
import db.assignment1.entity.Patient;
import db.assignment1.model.Student;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
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
public class PatientDaoImp implements PatientDao{
@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createPatientRecord(Patient patient) {
        String sql = "INSERT INTO PATIENT_DETAIL ( PATIENT_NAME ,"
                + " DATE_OF_BIRTH , PATIENT_ADDRESS , PATIENT_PHONE ) "
                + "VALUES ( ? , ? , ? , ? ) ";
        Object [] params=new Object[]{patient.getName(),patient.getDob(),
            patient.getAddress(),patient.getPhone()};
        int [] types=new int[]{Types.VARCHAR,Types.DATE,Types.VARCHAR,Types.INTEGER};
          return jdbcTemplate.update(sql, params, types);
    }

    @Override
    public List<Patient> getAllPatient() {
        List<Patient> patients = new ArrayList<>();
                String sql = "SELECT * FROM PATIENT_DETAIL ";
                
                patients=jdbcTemplate.query(sql,
                        new RowMapper<Patient>() {

           @Override
           public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
               Patient patient=new Patient( rs.getInt("PATIENT_ID"),
                       rs.getString("PATIENT_NAME"),
                       rs.getDate("DATE_OF_BIRTH"),
                       rs.getString("PATIENT_ADDRESS"),rs.getInt("PATIENT_PHONE"));
               return patient;
           }
       });
               return patients;}

    @Override
    public Patient getPatientById(Integer patientId) {
        Patient patient = null;
                String sql = "SELECT * FROM PATIENT_DETAIL WHERE PATIENT_ID = ? ";
                
                patient=jdbcTemplate.queryForObject(sql,new Object[]{patientId},
                        new RowMapper<Patient>() {

           @Override
           public Patient mapRow(ResultSet rs, int rowNum) throws SQLException {
               Patient patient=new Patient( rs.getInt("PATIENT_ID"),
                       rs.getString("PATIENT_NAME"),
                       rs.getDate("DATE_OF_BIRTH"),
                       rs.getString("PATIENT_ADDRESS"),rs.getInt("PATIENT_PHONE"));
               return patient;
           }
       });
               return patient;
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
}
