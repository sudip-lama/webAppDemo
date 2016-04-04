/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;

import db.assignment1.dao.DiagnosisDao;
import db.assignment1.entity.Diagnosis;
import db.assignment1.service.DiagnosisService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SUDIP
 */
@Service
@Transactional(readOnly = true)
public class DiagnosisServiceImp implements DiagnosisService{
    
    @Autowired
    DiagnosisDao diagnosisDao;
    
    @Override
    @Transactional(readOnly = false)
    public boolean createDiagnosisRecord(Diagnosis diagnosis) {
       return diagnosisDao.createDiagnosisRecord(diagnosis)>0?true:false;  
    }

    @Override
    public List<Diagnosis> getAllDiagnosis() {
    return diagnosisDao.getAllDiagnosis();
    }

    @Override
    public Diagnosis getDiagnosisById(Integer diagnosisId) {
    return diagnosisDao.getDiagnosisById(diagnosisId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(Diagnosis diagnosis) {
         return diagnosisDao.update(diagnosis)>0?true:false;   
    
    }
    
}
