/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.dao;

import db.assignment1.entity.Admit;
import db.assignment1.entity.Patient;
import java.util.List;

/**
 *
 * @author maha
 */

    public interface AdmitDao {
    public int createAdmitRecord(Admit admit);
    public List<Admit> getAllCurrentPatientInHospital();
    public Admit getAdmitById(Integer admitId);
    public int updateAdmitRecord(Admit admit);
    public int payAdmit(Admit admit);
}

    

