/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.dao;

import db.assignment1.entity.PatientDiagnosis;
import db.assignment1.entity.PatientVisit;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public interface PatientDiagnosisDao {
    public int createPatientDiagnosisRecord(PatientDiagnosis patientDiagnosis);
    public List<PatientDiagnosis> getAllPatientDiagnosis();
    public PatientDiagnosis getPatientDiagnosisById(Integer patientDiagnosisId);
    public int update(PatientDiagnosis patientDiagnosis);
    public int payPatientDiagnosis(PatientDiagnosis patientDiagnosis);
 
}
