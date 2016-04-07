/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;

import db.assignment1.dao.PatientMedicineDao;
import db.assignment1.entity.PatientMedication;
import db.assignment1.service.PatientMedicineService;
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
public class PatientMedicineServiceImp implements PatientMedicineService{

    @Autowired
    private PatientMedicineDao patientMedicineDao;
    
    @Override
    @Transactional(readOnly = false)
    public boolean createPatientMedicineRecord(List<PatientMedication> patientMedicationList) {
    return patientMedicineDao.createPatientMedicineRecord(patientMedicationList)>0?true:false;
    }

    @Override
    public List<PatientMedication> getAllUnpaidPatientMedicine(Integer patientId) {
        return patientMedicineDao.getAllUnpaidPatientMedicine(patientId);
                }

    @Override
    public PatientMedication getPatientMedicineById(Integer patientMedicationId) {
        return patientMedicineDao.getPatientMedicineById(patientMedicationId);
    }

    @Override
    public List<PatientMedication> getTopPatientMedicine() {
        return patientMedicineDao.getTopPatientMedicine();
    }
    
}
