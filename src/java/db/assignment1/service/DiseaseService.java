/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.service;

import db.assignment1.entity.Disease;
import java.util.List;

/**
 *
 * @author maha
 */
public interface DiseaseService {
      public boolean createDiseaseRecord(Disease disease);
    public List<Disease> getAllDiseaseAvailable();
    public Disease getDiseaseById(Integer diseaseId);

    public boolean updateDiseaseRecord(Disease disease);

}
