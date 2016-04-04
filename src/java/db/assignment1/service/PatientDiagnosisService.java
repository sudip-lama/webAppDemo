/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.service;

import db.assignment1.entity.PatientDiagnosis;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public interface PatientDiagnosisService {
     public boolean createPatientDiagnosisRecord(PatientDiagnosis patientDiagnosis);
    public List<PatientDiagnosis> getAllPatientDiagnosis();
    public PatientDiagnosis getPatientDiagnosisById(Integer patientDiagnosisId);
    public boolean update(PatientDiagnosis patientDiagnosis);
}
