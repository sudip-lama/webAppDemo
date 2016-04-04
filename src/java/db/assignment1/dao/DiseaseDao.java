/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.dao;

import db.assignment1.entity.Disease;
import java.util.List;

/**
 *
 * @author maha
 */
    public interface DiseaseDao {
    public int createDiseaseRecord(Disease disease);
    public List<Disease> getAllDisease();
    public Disease getDiseaseById(Integer diseaseId);
    public int update(Disease disease);

}
