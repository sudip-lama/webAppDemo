/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.PatientVisitDao;
import db.assignment1.entity.Doctor;
import db.assignment1.entity.PatientVisit;
import db.assignment1.entity.SearchCriteria;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SUDIP
 */
@Repository
public class PatientVisitDaoImp implements PatientVisitDao{
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createPatientVisitRecord(PatientVisit patientVisit) {
      String sql = "INSERT INTO PATIENT_VISIT ( PATIENT_VISIT_REASON ,"
                + " VISIT_PRICE, VISIT_DATE , PATIENT_ID , DOCTOR_ID,"
              + " PATIENT_VISIT_HR, PATIENT_VISIT_MIN, PATIENT_VISIT_AM_PM ,"
              + " PAYMENT_STAUS ) "
                + "VALUES ( ? , ? , ? , ? , ?, ?, ?, ?, 0) ";
        Object [] params=new Object[]{patientVisit.getReason(),patientVisit.getPrice(),
            patientVisit.getVistDate(),patientVisit.getPatientId(),
            patientVisit.getDoctorId(),patientVisit.getHr(), patientVisit.getMin()
        , patientVisit.getAmPm()};
        int [] types=new int[]{Types.VARCHAR,Types.DOUBLE,Types.DATE,Types.INTEGER,
        Types.INTEGER,Types.INTEGER,Types.INTEGER,Types.VARCHAR};
          return jdbcTemplate.update(sql, params, types);   
    }

    @Override
    public List<PatientVisit> getAllPatientVisit() {
       List<PatientVisit> patientVisitList = new ArrayList<>();
                String sql = "SELECT * FROM PATIENT_VISIT WHERE PAYMENT_STAUS = 0"
                        + " ORDER BY VISIT_DATE DESC";
                
                patientVisitList=jdbcTemplate.query(sql,
                        new RowMapper<PatientVisit>() {

           @Override
           public PatientVisit mapRow(ResultSet rs, int rowNum) throws SQLException {
               PatientVisit patientVisit=new PatientVisit(rs.getInt("PATIENT_VISIT_ID"),
                       rs.getDouble("VISIT_PRICE"),rs.getString("PATIENT_VISIT_REASON"),
                       rs.getDate("VISIT_DATE"),rs.getInt("PATIENT_VISIT_HR"),
                       rs.getString("PATIENT_VISIT_AM_PM"),rs.getInt("PATIENT_VISIT_MIN"), 
                       rs.getInt("DOCTOR_ID"),rs.getInt("PATIENT_ID"));
               return patientVisit;
           }
       });
               return patientVisitList;
    }

    @Override
    public PatientVisit getPatientVisitById(Integer patientVisitId) {
         List<PatientVisit> patientVisitList = new ArrayList<>();
                String sql = "SELECT * FROM PATIENT_VISIT WHERE PATIENT_VISIT_ID = ? ";
                
                 patientVisitList=jdbcTemplate.query(sql,new Object[]{patientVisitId},
                        new RowMapper<PatientVisit>() {

           @Override
           public PatientVisit mapRow(ResultSet rs, int rowNum) throws SQLException {
               PatientVisit patientVisit=new PatientVisit(rs.getInt("PATIENT_VISIT_ID"),
                       rs.getDouble("VISIT_PRICE"),rs.getString("PATIENT_VISIT_REASON"),
                       rs.getDate("VISIT_DATE"),rs.getInt("PATIENT_VISIT_HR"),
                       rs.getString("PATIENT_VISIT_AM_PM"),rs.getInt("PATIENT_VISIT_MIN"), 
                       rs.getInt("DOCTOR_ID"),rs.getInt("PATIENT_ID"));
               return patientVisit;
           }
       });
                 if(patientVisitList.isEmpty())
                     return null;
                 else
                     return patientVisitList.get(0);
              
    }

    @Override
    public int update(PatientVisit patientVisit) {
        String sql = "UPDATE  PATIENT_VISIT "
                + " SET PATIENT_VISIT_REASON = ? ,"
                + " VISIT_PRICE = ? , "
                + " VISIT_DATE = ? ,"
                + " PATIENT_VISIT_HR = ? ,"
                + " PATIENT_VISIT_MIN = ? ,"
                + " PATIENT_VISIT_AM_PM = ? ,"
                + " DOCTOR_ID = ? , "
                + " PATIENT_ID = ? "
                + " WHERE PATIENT_VISIT_ID = ? ";
        Object [] params=new Object[]{patientVisit.getReason(),patientVisit.getPrice(),
            patientVisit.getVistDate(),patientVisit.getHr(),patientVisit.getMin(),patientVisit.getAmPm(),
        patientVisit.getDoctorId(),patientVisit.getPatientId(),patientVisit.getId()};
        int [] types=new int[]{Types.VARCHAR,Types.DOUBLE,Types.DATE,Types.INTEGER,Types.INTEGER,
        Types.VARCHAR,Types.INTEGER,Types.INTEGER,Types.INTEGER};
     return jdbcTemplate.update(sql, params, types);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<PatientVisit> getPatientVisitBySearchCriteria(final SearchCriteria searchCriteria) {
        List<PatientVisit> patientVisitList = new ArrayList<>();
                String sql = "SELECT * FROM PATIENT_VISIT ";
                sql += " WHERE DOCTOR_ID = ? ";
                if(searchCriteria.getBeginDate()!=null)
                {
                    sql += " AND   VISIT_DATE >= ? ";
                    
                }
                 if(searchCriteria.getEndDate()!=null)
                {
                    sql += " AND   VISIT_DATE <= ? ";
                    
                }
                sql += " ORDER BY VISIT_DATE ";
           PreparedStatementSetter pss=     new PreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                ps.setInt(1, searchCriteria.getDoctorId());
                
                if(searchCriteria.getBeginDate()!=null)
                {
                    ps.setDate(2, new java.sql.Date (searchCriteria.getBeginDate().getTime()));
                }
                if(searchCriteria.getEndDate()!=null)
                {
                    ps.setDate(3,  new java.sql.Date (searchCriteria.getEndDate().getTime()));
                }
            }
        };
           
                patientVisitList=jdbcTemplate.query(sql,pss,
                        new RowMapper<PatientVisit>() {

           @Override
           public PatientVisit mapRow(ResultSet rs, int rowNum) throws SQLException {
               PatientVisit patientVisit=new PatientVisit(rs.getInt("PATIENT_VISIT_ID"),
                       rs.getDouble("VISIT_PRICE"),rs.getString("PATIENT_VISIT_REASON"),
                       rs.getDate("VISIT_DATE"),rs.getInt("PATIENT_VISIT_HR"),
                       rs.getString("PATIENT_VISIT_AM_PM"),rs.getInt("PATIENT_VISIT_MIN"), 
                       rs.getInt("DOCTOR_ID"),rs.getInt("PATIENT_ID"));
               return patientVisit;
           }
       });
               return patientVisitList;
    
    }

    @Override
    public int payPatientVisit(PatientVisit patientVisit) {
       String sql = "UPDATE  PATIENT_VISIT "
                + " SET PAYMENT_STAUS = 1 "
                + " WHERE PATIENT_VISIT_ID = ? ";
        Object [] params=new Object[]{patientVisit.getId()};
        int [] types=new int[]{Types.INTEGER};
     return jdbcTemplate.update(sql, params, types); 
    }
    
}
