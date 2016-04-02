/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;

import db.assignment1.daoImp.PatientDaoImp;
import db.assignment1.entity.Patient;
import db.assignment1.service.PatientService;
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
public class PatientServiceImpl implements PatientService{
    @Autowired
    private PatientDaoImp patientDaoImp;
    
    @Override
    @Transactional(readOnly = false)
    public boolean createPatientRecord(Patient patient) {
    return patientDaoImp.createPatientRecord(patient)>0?true:false;
    }

    @Override
    public List<Patient> getAllPatient() {
    return patientDaoImp.getAllPatient();
    }

    @Override
    public Patient getPatientById(Integer patientId) {
    return patientDaoImp.getPatientById(patientId);
    }

    public PatientDaoImp getPatientDaoImp() {
        return patientDaoImp;
    }

    public void setPatientDaoImp(PatientDaoImp patientDaoImp) {
        this.patientDaoImp = patientDaoImp;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updatePatientRecord(Patient patient) {
      return patientDaoImp.update(patient)>0?true:false;
    }

    @Override
    public List<Patient> getAllPatientByPatientName(String patientName) {
    
        return patientDaoImp.getAllPatientByPatientName(patientName);
    }
    
}
