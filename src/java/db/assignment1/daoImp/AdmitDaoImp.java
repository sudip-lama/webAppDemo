/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.AdmitDao;
import db.assignment1.entity.Admit;
import db.assignment1.entity.PatientDiagnosis;
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
public class AdmitDaoImp implements  AdmitDao {
@Autowired
    private JdbcTemplate jdbcTemplate;
    @Override
    public int createAdmitRecord(Admit admit) {
        String sql = "INSERT INTO ADMIT_DETAIL ( "
                + " ADMIT_DATE, PATIENT_ID , ROOM_ID,"
              + " DISEASE_ID , PAYMENT_STAUS ) "
                + "VALUES ( ? , ? , ? , ? , 0) ";
        Object [] params=new Object[]{admit.getAdmitDate(),
            admit.getPatientId(),admit.getRoomId(),admit.getDiseaseId()};
        int [] types=new int[]{Types.DATE,Types.INTEGER,Types.INTEGER,Types.INTEGER};
          return jdbcTemplate.update(sql, params, types);  }

    @Override
    public List<Admit> getAllCurrentPatientInHospital() {
        List<Admit> admitList = new ArrayList<>();
                String sql = "SELECT * FROM ADMIT_DETAIL WHERE PAYMENT_STAUS = 0 "
                        + " ORDER BY ADMIT_DATE  ";
                
                admitList=jdbcTemplate.query(sql,
                        new RowMapper<Admit>() {

           @Override
           public Admit mapRow(ResultSet rs, int rowNum) throws SQLException {
               Admit admit=new Admit(rs.getInt("ADMIT_ID"),
                       rs.getDate("ADMIT_DATE"),
                       rs.getInt("PATIENT_ID"),
                       rs.getInt("ROOM_ID"),rs.getInt("DISEASE_ID"));
               return admit;
           }
       });
               return admitList;}

    @Override
    public Admit getAdmitById(Integer admitId) {
         List<Admit> admitList = new ArrayList<>();
                String sql = "SELECT * FROM ADMIT_DETAIL WHERE ADMIT_ID = ? ";
                
                 admitList=jdbcTemplate.query(sql,new Object[]{admitId},
                        new RowMapper<Admit>() {

           @Override
           public Admit mapRow(ResultSet rs, int rowNum) throws SQLException {
                Admit admit=new Admit(rs.getInt("ADMIT_ID"),
                       rs.getDate("ADMIT_DATE"),
                       rs.getInt("PATIENT_ID"),
                       rs.getInt("ROOM_ID"),rs.getInt("DISEASE_ID"));
               return admit;
           }
       });
                 if(admitList.isEmpty())
                     return null;
                 else
                     return admitList.get(0); 
    }

    @Override
    public int updateAdmitRecord(Admit admit) {
        String sql = "UPDATE  ADMIT_DETAIL "
                + " SET ADMIT_DATE = ? ,"
                + " PATIENT_ID = ? , "
                + " ROOM_ID = ? ,"
                + " DISEASE_ID = ? "
                + " WHERE ADMIT_ID = ? ";
        Object [] params=new Object[]{admit.getAdmitDate(),admit.getPatientId(),
            admit.getRoomId(),admit.getDiseaseId(),admit.getId()};
        int [] types=new int[]{Types.DATE,Types.INTEGER,Types.INTEGER, Types.INTEGER,Types.INTEGER};
     return jdbcTemplate.update(sql, params, types);
    
    }

    @Override
    public int payAdmit(Admit admit) {
         String sql = "UPDATE  ADMIT_DETAIL "
                + " SET DISCHARGE_DATE = ? ,"
                + " PAYMENT_STAUS = 1  "
                + " WHERE ADMIT_ID = ? ";
        Object [] params=new Object[]{admit.getDischargeDate(),
            admit.getId()};
        int [] types=new int[]{Types.DATE,Types.INTEGER};
     return jdbcTemplate.update(sql, params, types);
    }

    @Override
    public Admit getAdmitByPatientId(Integer patientId) {
        List<Admit> admitList = new ArrayList<>();
                String sql = "SELECT * FROM ADMIT_DETAIL WHERE "
                        + " PAYMENT_STAUS = 0 "
                        + " AND PATIENT_ID = ? ";
                
                 admitList=jdbcTemplate.query(sql,new Object[]{patientId},
                        new RowMapper<Admit>() {

           @Override
           public Admit mapRow(ResultSet rs, int rowNum) throws SQLException {
                Admit admit=new Admit(rs.getInt("ADMIT_ID"),
                       rs.getDate("ADMIT_DATE"),
                       rs.getInt("PATIENT_ID"),
                       rs.getInt("ROOM_ID"),rs.getInt("DISEASE_ID"));
               return admit;
           }
       });
                 if(admitList.isEmpty())
                     return null;
                 else
                     return admitList.get(0); 
    }

    @Override
    public List<Admit> getAverageStayByDisease() {
        List<Admit> admitList = new ArrayList<>();
                String sql = "SELECT DISEASE_ID, AVG (DISCHARGE_DATE-ADMIT_DATE) AS AVG_STAY "
                        + " FROM ADMIT_DETAIL "
                        + " WHERE PAYMENT_STAUS = 1"
                        + " GROUP BY DISEASE_ID ";
                
                 admitList=jdbcTemplate.query(sql,
                        new RowMapper<Admit>() {

           @Override
           public Admit mapRow(ResultSet rs, int rowNum) throws SQLException {
                Admit admit=new Admit(rs.getInt("DISEASE_ID"),
                       rs.getDouble("AVG_STAY"));
               return admit;
           }
       });
                return admitList;
    }
    
}
