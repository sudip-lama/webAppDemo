/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.daoImp;

import db.assignment1.dao.DoctorDao;
import db.assignment1.entity.Doctor;
import db.assignment1.entity.Patient;
import java.sql.Types;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 *
 * @author SUDIP
 */
@Repository
public class DoctorDaoImp implements DoctorDao{
@Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int createDoctorRecord(Doctor doctor) {
         String sql = "INSERT INTO DOCTOR_DETAIL ( DOCTOR_NAME ,"
                + " DOCTOR_SPECIALIST, DOCTOR_ADDRESS , PATIENT_PHONE , DELETE_STATUS ) "
                + "VALUES ( ? , ? , ? , ? , 0) ";
        Object [] params=new Object[]{doctor.getName(),doctor.getSpecialist(),
            doctor.getAddress(),doctor.getPhone()};
        int [] types=new int[]{Types.VARCHAR,Types.DATE,Types.VARCHAR,Types.VARCHAR};
          return jdbcTemplate.update(sql, params, types); }

    @Override
    public List<Doctor> getAllDoctor() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Patient getDoctorById(Integer doctorId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int update(Doctor doctor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
