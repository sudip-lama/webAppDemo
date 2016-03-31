/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.dao;

import db.assignment1.entity.PatientAdmit;
import java.util.List;

/**
 *
 * @author maha
 */
public interface PatientAdmitDao {
  
    public int createPatientAdmitRecord(PatientAdmit patientAdmit);
    public List<PatientAdmit> getAllPatientAdmit();
    public PatientAdmit getPatientAdmitById(Integer patientAdmitId);
    public int update(PatientAdmit patientAdmit);
}

