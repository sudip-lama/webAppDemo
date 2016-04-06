/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;

import db.assignment1.dao.PatientDiagnosisDao;
import db.assignment1.entity.PatientDiagnosis;
import db.assignment1.service.PatientDiagnosisService;
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
public class PatientDiagnosisServiceImp implements PatientDiagnosisService {

    @Autowired
    private PatientDiagnosisDao patientDiagnosisDao;

    @Override
    @Transactional(readOnly = false)
    public boolean createPatientDiagnosisRecord(PatientDiagnosis patientDiagnosis) {
        patientDiagnosis.setPatientId(patientDiagnosis.getPatient().getId());
        patientDiagnosis.setDoctorId(patientDiagnosis.getDoctor().getId());
        patientDiagnosis.setDiagnosisId(patientDiagnosis.getDiagnosis().getId());
        return patientDiagnosisDao.createPatientDiagnosisRecord(patientDiagnosis) > 0 ? true : false;
    }

    @Override
    public List<PatientDiagnosis> getAllPatientDiagnosis() {
        return patientDiagnosisDao.getAllPatientDiagnosis();
    }

    @Override
    public PatientDiagnosis getPatientDiagnosisById(Integer patientDiagnosisId) {
        return patientDiagnosisDao.getPatientDiagnosisById(patientDiagnosisId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(PatientDiagnosis patientDiagnosis) {
        patientDiagnosis.setPatientId(patientDiagnosis.getPatient().getId());
        patientDiagnosis.setDoctorId(patientDiagnosis.getDoctor().getId());
        patientDiagnosis.setDiagnosisId(patientDiagnosis.getDiagnosis().getId());
        return patientDiagnosisDao.update(patientDiagnosis) > 0 ? true : false;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean payPatientDiagnosis(PatientDiagnosis patientDiagnosis) {
      return patientDiagnosisDao.payPatientDiagnosis(patientDiagnosis) > 0 ? true : false;
    }

}
