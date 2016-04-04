/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.service;

import db.assignment1.entity.Diagnosis;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public interface DiagnosisService {
 public boolean createDiagnosisRecord(Diagnosis diagnosis);
    public List<Diagnosis> getAllDiagnosis();
    public Diagnosis getDiagnosisById(Integer diagnosisId);
    public boolean update(Diagnosis diagnosis);   
}
