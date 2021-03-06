/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.dao;

import db.assignment1.entity.Medicine;
import db.assignment1.entity.PatientMedication;
import java.util.List;

/**
 *
 * @author SUDIP
 */

public interface PatientMedicineDao {
    public int createPatientMedicineRecord(List<PatientMedication> patientMedicationList);
    public List<PatientMedication>getAllUnpaidPatientMedicine(Integer patientId);
    public PatientMedication getPatientMedicineById(Integer patientMedicationId);
    public List<PatientMedication>getTopPatientMedicine();
    
   
}
