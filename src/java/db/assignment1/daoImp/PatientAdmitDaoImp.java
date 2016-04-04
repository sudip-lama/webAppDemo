/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;


import db.assignment1.dao.PatientAdmitDao;
import db.assignment1.entity.Patient;
import db.assignment1.entity.PatientAdmit;
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
 * @author maha
 */
@Repository
public class PatientAdmitDaoImp implements PatientAdmitDao{
@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createPatientAdmitRecord(PatientAdmit patientAdmit) {
        String sql = "INSERT INTO PATIENTADMIT_DETAIL ( PATIENT_ID ,"
                + " ADMIT_DATE , DISCHARGE_DATE , ROOM_NUMBER ) "
                + "VALUES ( ? , ? , ? , ? ) ";
        Object [] params=new Object[]{patientAdmit.getPatientId(),patientAdmit.getAdmitDate(),
            patientAdmit.getDischargeDate(),patientAdmit.getRoomNumber()};
        int [] types=new int[]{Types.VARCHAR,Types.DATE,Types.DATE,Types.VARCHAR};
          return jdbcTemplate.update(sql, params, types);
    }

    @Override
    public List<PatientAdmit> getAllPatientAdmit() {
        List<PatientAdmit> patientsAdmit = new ArrayList<>();
                String sql = "SELECT * FROM PATIENTADMIT_DETAIL";
                
                patientsAdmit=jdbcTemplate.query(sql,
                        new RowMapper<PatientAdmit>() {

           @Override
           public PatientAdmit mapRow(ResultSet rs, int rowNum) throws SQLException {
               PatientAdmit patientAdmit=new PatientAdmit( 
                       rs.getDate("ADMIT_DATE"),
                       rs.getDate("DISCHARGE_DATE"),
                           rs.getString("ROOM_NUMBER"),
               rs.getInt("PATIENT_ID"));
               return patientAdmit;
           }
       });
               return patientsAdmit;}


    @Override
    public PatientAdmit getPatientAdmitById(Integer patientAdmitId) {
        PatientAdmit patientAdmit = null;
                String sql = "SELECT * FROM PATIENTADMIT_DETAIL WHERE PATIENTADMIT_ID = ? ";
                
                patientAdmit=jdbcTemplate.queryForObject(sql,new Object[]{patientAdmitId},
                        new RowMapper<PatientAdmit>() {

           @Override
           public PatientAdmit mapRow(ResultSet rs, int rowNum) throws SQLException {
               PatientAdmit patientAdmit=new PatientAdmit( 
                       rs.getDate("ADMIT_DATE"),
                       rs.getDate("DISCHARGE_DATE"),
                       rs.getString("ROOM_NUMBER"),
               rs.getInt("PATIENT_ID"));
               return patientAdmit;
           }
       });
               return patientAdmit;
    }

    @Override
    public int update(PatientAdmit patientAdmit) {
        String sql = "UPDATE  PATIENTADMIT_DETAIL "
                + " ADMIT_DATE = ? ,"
                + " DISCHARGE_DATE = ? , "
                + " ROOM_NUMBER = ? ,"
                + " PATIENT_ID = ? "
                + " WHERE PATIENTADMIT_ID = ? ";
        Object [] params=new Object[]{patientAdmit.getPatientId(),patientAdmit.getAdmitDate(),
            patientAdmit.getDischargeDate(),patientAdmit.getRoomNumber()};
        int [] types=new int[]{Types.VARCHAR,Types.DATE,Types.DATE,Types.VARCHAR};
     return jdbcTemplate.update(sql, params, types);
    }
}
