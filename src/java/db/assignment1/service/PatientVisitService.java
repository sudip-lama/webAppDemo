/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.service;

import db.assignment1.entity.PatientVisit;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public interface PatientVisitService {
    public boolean createPatientVisitRecord(PatientVisit patientVisit);
    public List<PatientVisit> getAllPatientVisit();
    public PatientVisit getPatientVisitById(Integer patientVisitId);
    public boolean updatePatientVisitRecord(PatientVisit patientVisit);
}
