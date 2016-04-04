/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;


import db.assignment1.dao.DiseaseDao;
import db.assignment1.entity.Disease;
import db.assignment1.service.DiseaseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maha
 */
@Service
@Transactional(readOnly = true)
public class DiseaseServiceImp implements DiseaseService{
     @Autowired
    private DiseaseDao diseaseDaoImp;

    @Override
    @Transactional(readOnly = false)
    public boolean createDiseaseRecord(Disease disease) {
     return diseaseDaoImp.createDiseaseRecord(disease)>0?true:false;  
    }

    @Override
    public List<Disease> getAllDiseaseAvailable() {
    return diseaseDaoImp.getAllDisease();
    }

    @Override
    public Disease getDiseaseById(Integer diseaseId) {
    return diseaseDaoImp.getDiseaseById(diseaseId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updateDiseaseRecord(Disease disease) {
    return diseaseDaoImp.update(disease)>0?true:false;     
    }

}
