/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.DoctorDao;
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
public class DoctorDaoImp implements DoctorDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createDoctorRecord(Doctor doctor) {
        String sql = "INSERT INTO DOCTOR_DETAIL ( DOCTOR_NAME ,"
                + " DOCTOR_SPECIALIST, DOCTOR_ADDRESS , DOCTOR_PHONE , DELETE_STATUS ) "
                + "VALUES ( ? , ? , ? , ? , 0) ";
        Object[] params = new Object[]{doctor.getName(), doctor.getSpecialist(),
            doctor.getAddress(), doctor.getPhone()};
        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR};
        return jdbcTemplate.update(sql, params, types);
    }

    @Override
    public List<Doctor> getAllDoctor() {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM DOCTOR_DETAIL WHERE DELETE_STATUS = 0"
                + " ORDER BY DOCTOR_SPECIALIST ";

        doctors = jdbcTemplate.query(sql,
                new RowMapper<Doctor>() {

                    @Override
                    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Doctor doctor = new Doctor(rs.getInt("DOCTOR_id"),
                                rs.getString("DOCTOR_NAME"),
                                rs.getString("DOCTOR_SPECIALIST"),
                                rs.getString("DOCTOR_ADDRESS"), rs.getString("DOCTOR_PHONE"));
                        return doctor;
                    }
                });
        return doctors;
    }

    public List<Doctor> getAllDoctorBySpecialist(String specialist) {
        List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM DOCTOR_DETAIL WHERE DELETE_STATUS = 0"
                + "WHERE DOCTOR_SPECIALIST = ? "
                + " ORDER BY DOCTOR_SPECIALIST ";

        doctors = jdbcTemplate.query(sql, new Object[]{specialist},
                new RowMapper<Doctor>() {

                    @Override
                    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Doctor doctor = new Doctor(rs.getInt("DOCTOR_id"),
                                rs.getString("DOCTOR_NAME"),
                                rs.getString("DOCTOR_SPECIALIST"),
                                rs.getString("DOCTOR_ADDRESS"), rs.getString("DOCTOR_PHONE"));
                        return doctor;
                    }
                });
        return doctors;
    }

    @Override
    public Doctor getDoctorById(Integer doctorId) {
         List<Doctor> doctors = new ArrayList<>();
        String sql = "SELECT * FROM DOCTOR_DETAIL WHERE DOCTOR_id = ? "
                + " AND DELETE_STATUS = 0";

         doctors = jdbcTemplate.query(sql,new Object[]{doctorId},
                new RowMapper<Doctor>() {

                    @Override
                    public Doctor mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Doctor doctor = new Doctor(rs.getInt("DOCTOR_id"),
                                rs.getString("DOCTOR_NAME"),
                                rs.getString("DOCTOR_SPECIALIST"),
                                rs.getString("DOCTOR_ADDRESS"), rs.getString("DOCTOR_PHONE"));
                        return doctor;
                    }
                });
         
         if(doctors.isEmpty())
             return null;
         else
             return doctors.get(0);
        

    }

    @Override
    public int update(Doctor doctor) {
        String sql = "UPDATE  DOCTOR_DETAIL "
                + " SET DOCTOR_NAME = ? ,"
                + " DOCTOR_SPECIALIST = ? , "
                + " DOCTOR_ADDRESS = ? ,"
                + " DOCTOR_PHONE = ? "
                + " WHERE DOCTOR_id = ? ";
        Object[] params = new Object[]{doctor.getName(), doctor.getSpecialist(),
            doctor.getAddress(), doctor.getPhone(), doctor.getId()};
        int[] types = new int[]{Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.VARCHAR, Types.INTEGER};
        return jdbcTemplate.update(sql, params, types);

    }

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

}
