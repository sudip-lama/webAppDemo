/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.dao;

import db.assignment1.entity.Diagnosis;
import db.assignment1.entity.Doctor;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public interface DiagnosisDao {
    public int createDiagnosisRecord(Diagnosis diagnosis);
    public List<Diagnosis> getAllDiagnosis();
    public Diagnosis getDiagnosisById(Integer diagnosisId);
    public int update(Diagnosis diagnosis);
    
}
