/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.service;

import db.assignment1.entity.Patient;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public interface PatientService {
    public boolean createPatientRecord(Patient patient);
    public List<Patient> getAllPatient();
    public Patient getPatientById(Integer patientId);
    public boolean updatePatientRecord(Patient patient);
}
