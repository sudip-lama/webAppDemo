/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;

import db.assignment1.dao.DoctorDao;
import db.assignment1.daoImp.DoctorDaoImp;
import db.assignment1.daoImp.PatientDaoImp;
import db.assignment1.entity.Doctor;
import db.assignment1.service.DoctorService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SUDIP
 */
@Service
@Transactional(readOnly = true)
public class DoctorServiceImp implements DoctorService{
     @Autowired
    private DoctorDao doctorDaoImp;

    @Override
    @Transactional(readOnly = false)
    public boolean createDoctortRecord(Doctor doctor) {
     return doctorDaoImp.createDoctorRecord(doctor)>0?true:false;  
    }

    @Override
    public List<Doctor> getAllDoctor() {
    return doctorDaoImp.getAllDoctor();
    }

    @Override
    public Doctor getDoctorById(Integer doctorId) {
    return doctorDaoImp.getDoctorById(doctorId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updateDoctorRecord(Doctor doctor) {
    return doctorDaoImp.update(doctor)>0?true:false;     
    }
     
     
}
