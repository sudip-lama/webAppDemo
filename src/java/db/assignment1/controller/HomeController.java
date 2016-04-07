/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.PatientMedication;
import db.assignment1.service.AdmitService;
import db.assignment1.service.DiseaseService;
import db.assignment1.service.PatientMedicineService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.NoneScoped;
import javax.faces.bean.RequestScoped;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SUDIP
 */
@Component
@ManagedBean
@NoneScoped
public class HomeController {
    
    @Autowired
    private PatientMedicineService patientMedicineService;
    @Autowired
    private AdmitService admitService;
    @Autowired
    private DiseaseService diseaseService;
    
    private List<PatientMedication> patientMedicationList;

    public PatientMedicineService getPatientMedicineService() {
        return patientMedicineService;
    }

    public void setPatientMedicineService(PatientMedicineService patientMedicineService) {
        this.patientMedicineService = patientMedicineService;
    }

    public List<PatientMedication> getPatientMedicationList() {
        
        if(patientMedicationList==null)
        {
            patientMedicationList=patientMedicineService.getTopPatientMedicine();
        }
        return patientMedicationList;
    }

    public void setPatientMedicationList(List<PatientMedication> patientMedicationList) {
        this.patientMedicationList = patientMedicationList;
    }

    public AdmitService getAdmitService() {
        return admitService;
    }

    public void setAdmitService(AdmitService admitService) {
        this.admitService = admitService;
    }

    public DiseaseService getDiseaseService() {
        return diseaseService;
    }

    public void setDiseaseService(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
    }
    
    
}
