/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.DiagnosisDao;
import db.assignment1.entity.Diagnosis;
import db.assignment1.entity.Doctor;
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
public class DiagnosisDaoImp implements DiagnosisDao{

     @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createDiagnosisRecord(Diagnosis diagnosis) {
         String sql = "INSERT INTO DIAGNOSIS_DETAIL ( DIAGNOSIS_NAME ,"
                + " DIAGNOSIS_PRICE, DELETE_STATUS ) "
                + "VALUES ( ? , ? , 0) ";
        Object[] params = new Object[]{diagnosis.getName(), diagnosis.getPrice()};
        int[] types = new int[]{Types.VARCHAR, Types.DOUBLE };
        return jdbcTemplate.update(sql, params, types);
    }

    @Override
    public List<Diagnosis> getAllDiagnosis() {
        List<Diagnosis> diagnosisList = new ArrayList<>();
        String sql = "SELECT * FROM DIAGNOSIS_DETAIL WHERE DELETE_STATUS = 0"
                + " ORDER BY DIAGNOSIS_NAME ";

        diagnosisList = jdbcTemplate.query(sql,
                new RowMapper<Diagnosis>() {

                    @Override
                    public Diagnosis mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Diagnosis diagnosis = new Diagnosis(rs.getInt("DIAGNOSIS_id"),
                                rs.getString("DIAGNOSIS_NAME"),
                                rs.getDouble("DIAGNOSIS_PRICE") );
                        return diagnosis;
                    }
                });
        return diagnosisList;
    }

    @Override
    public Diagnosis getDiagnosisById(Integer diagnosisId) {
       List<Diagnosis> diagnosisList = new ArrayList<>();
        String sql = "SELECT * FROM DIAGNOSIS_DETAIL WHERE DIAGNOSIS_id = ? "
                + " AND DELETE_STATUS = 0";

         diagnosisList = jdbcTemplate.query(sql,new Object[]{diagnosisId},
                new RowMapper<Diagnosis>() {

                    @Override
                    public Diagnosis mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Diagnosis diagnosis = new Diagnosis(rs.getInt("DIAGNOSIS_id"),
                                rs.getString("DIAGNOSIS_NAME"),
                                rs.getDouble("DIAGNOSIS_PRICE") );
                        return diagnosis;
                    }
                });
         
         if(diagnosisList.isEmpty())
             return null;
         else
             return diagnosisList.get(0);
    }

    @Override
    public int update(Diagnosis diagnosis) {
       String sql = "UPDATE  DIAGNOSIS_DETAIL "
                + " SET DIAGNOSIS_NAME = ? ,"
                + " DIAGNOSIS_PRICE = ? "             
                + " WHERE DIAGNOSIS_id = ? ";
        Object[] params = new Object[]{diagnosis.getName(), diagnosis.getPrice(),diagnosis.getId()};
        int[] types = new int[]{Types.VARCHAR, Types.DOUBLE,Types.INTEGER};
        return jdbcTemplate.update(sql, params, types);
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
}
