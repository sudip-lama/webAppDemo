/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;

import db.assignment1.dao.PatientVisitDao;
import db.assignment1.entity.PatientVisit;
import db.assignment1.service.PatientVisitService;
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
public class PatientVisitServiceImp implements PatientVisitService{
    
    @Autowired
    private PatientVisitDao patientVisitDao;

    @Override
    @Transactional(readOnly = false)
    public boolean createPatientVisitRecord(PatientVisit patientVisit) {
        patientVisit.setPatientId(patientVisit.getPatient().getId());
        patientVisit.setDoctorId(patientVisit.getDoctor().getId());
        return patientVisitDao.createPatientVisitRecord(patientVisit)>0?true:false; 
    }

    @Override
    public List<PatientVisit> getAllPatientVisit() {
        return patientVisitDao.getAllPatientVisit();
    }

    @Override
    public PatientVisit getPatientVisitById(Integer patientVisitId) {
        return patientVisitDao.getPatientVisitById(patientVisitId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updatePatientVisitRecord(PatientVisit patientVisit) {
         patientVisit.setPatientId(patientVisit.getPatient().getId());
        patientVisit.setDoctorId(patientVisit.getDoctor().getId());
        return patientVisitDao.update(patientVisit)>0?true:false;   
    }
    
    
}
