/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.MedicineDao;
import db.assignment1.entity.Medicine;
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
public class MedicineDaoImp implements MedicineDao{
@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createMedicineRecord(Medicine medicine) {
         String sql = "INSERT INTO MEDICINE_DETAIL ( MEDICINE_NAME ,"

                + " PRICE)"
                + "VALUES ( ? , ? ) ";
        Object [] params=new Object[]{medicine.getMedicineName(), medicine.getPrice()};
        int [] types=new int[]{Types.VARCHAR,Types.DOUBLE};
          return jdbcTemplate.update(sql, params, types); }

    @Override
    public List<Medicine> getAllMedicine() {
        List<Medicine> medicines = new ArrayList<>();
                String sql = "SELECT * FROM MEDICINE_DETAIL ";
                
                medicines=jdbcTemplate.query(sql,
                        new RowMapper<Medicine>() {

           @Override
           public Medicine mapRow(ResultSet rs, int rowNum) throws SQLException {
               Medicine medicine=new Medicine( rs.getInt("MEDICINE_id"),
                       rs.getString("MEDICINE_NAME"),
                       rs.getDouble("PRICE"));
               return medicine ;
           }
       });
               return medicines;
    }

    @Override
    public Medicine getMedicineById(Integer medicineId) {
        Medicine medicine = null;
                String sql = "SELECT * FROM MEDICINE_DETAIL WHERE MEDICINE_id = ? ";
                
                medicine=jdbcTemplate.queryForObject(sql,new Object[]{medicineId},
                        new RowMapper<Medicine>() {

           @Override
           public Medicine mapRow(ResultSet rs, int rowNum) throws SQLException {
               Medicine medicine=new Medicine(rs.getInt("MEDICINE_id"),
                       rs.getString("MEDICINE_NAME"),
                       rs.getDouble("PRICE"));
               return medicine;
           }
       });
               return medicine; 
    
    }

    @Override
    public int update(Medicine medicine) {
        String sql = "UPDATE  MEDICINE_DETAIL "
                + " SET MEDICINE_NAME = ? ,"
                + " PRICE = ?  "
                + " WHERE MEDICINE_id = ? ";
        Object [] params=new Object[]{medicine.getMedicineName(), medicine.getPrice(), medicine.getId()};
        int [] types=new int[]{Types.VARCHAR,Types.DOUBLE,Types.INTEGER};

     return jdbcTemplate.update(sql, params, types);
    
    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    
}

