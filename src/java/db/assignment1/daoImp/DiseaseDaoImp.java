/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.DiseaseDao;
import db.assignment1.entity.Disease;
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
public class DiseaseDaoImp implements DiseaseDao{
@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createDiseaseRecord(Disease disease) {
         String sql = "INSERT INTO DISEASE_DETAIL ( DISEASE_NAME ,"

                + " DISEASE_TYPE, DISEASE_SYMPTOMS)"
                + "VALUES ( ? , ? , ? ) ";
        Object [] params=new Object[]{disease.getDiseaseName(), disease.getDiseaseType(), disease.getDiseasesymptoms()};
        int [] types=new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR};
          return jdbcTemplate.update(sql, params, types); }

    @Override
    public List<Disease> getAllDisease() {
        List<Disease> diseases = new ArrayList<>();
                String sql = "SELECT * FROM DISEASE_DETAIL ";
                
                diseases=jdbcTemplate.query(sql,
                        new RowMapper<Disease>() {

           @Override
           public Disease mapRow(ResultSet rs, int rowNum) throws SQLException {
               Disease disease=new Disease( rs.getInt("DISEASE_id"),
                       rs.getString("DISEASE_NAME"),
                       rs.getString("DISEASE_TYPE"),
                       rs.getString("DISEASE_SYMPTOMS"));
               return disease;
           }
       });
               return diseases;
    }

    @Override
    public Disease getDiseaseById(Integer diseaseId) {
        List<Disease> diseaseList = new ArrayList<>();
                String sql = "SELECT * FROM DISEASE_DETAIL WHERE DISEASE_id = ? ";
                
                diseaseList=jdbcTemplate.query(sql,new Object[]{diseaseId},
                        new RowMapper<Disease>() {

           @Override
           public Disease mapRow(ResultSet rs, int rowNum) throws SQLException {
               Disease disease=new Disease(rs.getInt("DISEASE_id"),
                       rs.getString("DISEASE_NAME"),
                       rs.getString("DISEASE_TYPE"),
                       rs.getString("DISEASE_SYMPTOMS"));
               return disease;
           }
       });
                if(diseaseList.isEmpty())
                     return null;
                 else
                     return diseaseList.get(0); 
               
    
    }

    @Override
    public int update(Disease disease) {
        String sql = "UPDATE  DISEASE_DETAIL "
                + " SET DISEASE_NAME = ? ,"
                + " DISEASE_TYPE = ? , "
                + " DISEASE_SYMPTOMS = ? "
                + " WHERE DISEASE_id = ? ";
        Object [] params=new Object[]{disease.getDiseaseName(), disease.getDiseaseType(), disease.getDiseasesymptoms(), disease.getId()};
        int [] types=new int[]{Types.VARCHAR,Types.VARCHAR,Types.VARCHAR,Types.INTEGER};

     return jdbcTemplate.update(sql, params, types);
    
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
}

