/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.dao;


import db.assignment1.entity.PatientVisit;
import db.assignment1.entity.SearchCriteria;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public interface PatientVisitDao {
     public int createPatientVisitRecord(PatientVisit patientVisit);
    public List<PatientVisit> getAllPatientVisit();
    public PatientVisit getPatientVisitById(Integer patientVisitId);
    public int update(PatientVisit patientVisit);
    public List<PatientVisit> getPatientVisitBySearchCriteria(SearchCriteria searchCriteria);
    public int payPatientVisit(PatientVisit patientVisit);
}
