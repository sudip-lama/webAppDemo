/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.PatientDiagnosisDao;
import db.assignment1.entity.PatientDiagnosis;
import db.assignment1.entity.PatientVisit;
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
public class PatientDiagnosisImp implements PatientDiagnosisDao{
@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createPatientDiagnosisRecord(PatientDiagnosis patientDiagnosis) {
        String sql = "INSERT INTO PATIENT_DIAGNOSIS ( "
                + " DIAGNOSIS_DATE , PATIENT_ID , DOCTOR_ID,"
              + " DIAGNOSIS_ID , PAYMENT_STAUS ) "
                + "VALUES ( ? , ? , ? , ? , 0) ";
        Object [] params=new Object[]{patientDiagnosis.getDiagnosisDate(),patientDiagnosis.getPatientId(),
            patientDiagnosis.getDoctorId(),patientDiagnosis.getDiagnosisId()};
        int [] types=new int[]{Types.DATE,Types.INTEGER,Types.INTEGER,Types.INTEGER};
          return jdbcTemplate.update(sql, params, types);  }

    @Override
    public List<PatientDiagnosis> getAllPatientDiagnosis() {
        List<PatientDiagnosis> patientDiagnosisList = new ArrayList<>();
                String sql = "SELECT * FROM PATIENT_DIAGNOSIS WHERE PAYMENT_STAUS = 0 "
                        + " ORDER BY DIAGNOSIS_DATE DESC ";
                
                patientDiagnosisList=jdbcTemplate.query(sql,
                        new RowMapper<PatientDiagnosis>() {

           @Override
           public PatientDiagnosis mapRow(ResultSet rs, int rowNum) throws SQLException {
               PatientDiagnosis patientVisit=new PatientDiagnosis(rs.getInt("PATIENT_DIAGNOSIS_ID"),
                       rs.getDate("DIAGNOSIS_DATE"),rs.getInt("PATIENT_ID"),
                       rs.getInt("DOCTOR_ID"),rs.getInt("DIAGNOSIS_ID"));
               return patientVisit;
           }
       });
               return patientDiagnosisList;
    }

    @Override
    public PatientDiagnosis getPatientDiagnosisById(Integer patientDiagnosisId) {
        List<PatientDiagnosis> patientDiagnosisList = new ArrayList<>();
                String sql = "SELECT * FROM PATIENT_DIAGNOSIS WHERE PATIENT_DIAGNOSIS_ID = ? ";
                
                 patientDiagnosisList=jdbcTemplate.query(sql,new Object[]{patientDiagnosisId},
                        new RowMapper<PatientDiagnosis>() {

           @Override
           public PatientDiagnosis mapRow(ResultSet rs, int rowNum) throws SQLException {
                PatientDiagnosis patientVisit=new PatientDiagnosis(rs.getInt("PATIENT_DIAGNOSIS_ID"),
                       rs.getDate("DIAGNOSIS_DATE"),rs.getInt("PATIENT_ID"),
                       rs.getInt("DOCTOR_ID"),rs.getInt("DIAGNOSIS_ID"));
               return patientVisit;
           }
       });
                 if(patientDiagnosisList.isEmpty())
                     return null;
                 else
                     return patientDiagnosisList.get(0);
    }

    @Override
    public int update(PatientDiagnosis patientDiagnosis) {
         String sql = "UPDATE  PATIENT_DIAGNOSIS "
                + " SET DIAGNOSIS_DATE = ? ,"
                + " PATIENT_ID = ? , "
                + " DOCTOR_ID = ? ,"
                + " DIAGNOSIS_ID = ? "
                + " WHERE PATIENT_DIAGNOSIS_ID = ? ";
        Object [] params=new Object[]{patientDiagnosis.getDiagnosisDate(),patientDiagnosis.getPatientId(),
            patientDiagnosis.getDoctorId(),patientDiagnosis.getDiagnosisId(),patientDiagnosis.getId()};
        int [] types=new int[]{Types.DATE,Types.INTEGER,Types.INTEGER, Types.INTEGER,Types.INTEGER};
     return jdbcTemplate.update(sql, params, types);
    
    }
    
     public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int payPatientDiagnosis(PatientDiagnosis patientDiagnosis) {
         String sql = "UPDATE  PATIENT_DIAGNOSIS "
                + " SET PAYMENT_STAUS = 1 "
                + " WHERE PATIENT_DIAGNOSIS_ID = ? ";
        Object [] params=new Object[]{patientDiagnosis.getId()};
        int [] types=new int[]{Types.INTEGER};
     return jdbcTemplate.update(sql, params, types);
    
    }
}
