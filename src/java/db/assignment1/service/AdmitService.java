/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.service;

import db.assignment1.entity.Admit;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public interface AdmitService {
  public boolean createAdmitRecord(Admit admit);
    public List<Admit> getAllCurrentPatientInHospital();
    public Admit getAdmitById(Integer admitId);
    public boolean updateAdmitRecord(Admit admit);  
}
