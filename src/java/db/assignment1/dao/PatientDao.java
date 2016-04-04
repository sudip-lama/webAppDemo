/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.dao;

import db.assignment1.entity.Patient;
import db.assignment1.entity.SearchCriteria;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public interface PatientDao {
  
    public int createPatientRecord(Patient patient);
    public List<Patient> getAllPatient();
    public Patient getPatientById(Integer patientId);
    public int update(Patient patient);
    public List<Patient> getAllPatientByPatientName(String patientName);
}
