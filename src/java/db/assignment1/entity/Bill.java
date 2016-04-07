/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author maha
 */
public class Bill {
    
    private List<PatientMedication> patientMedicationList;
    private List<Admit>admitList;
    private List<PatientVisit>patientVisitList;
    private List<PatientDiagnosis>patientDiagnosisList;
    
    public Bill() {
    }
   

    public List<PatientMedication> getPatientMedicationList() {
        if(patientMedicationList==null)
        {
            patientMedicationList=new ArrayList<>();
        }
        return patientMedicationList;
    }

    public void setPatientMedicationList(List<PatientMedication> patientMedicationList) {
        this.patientMedicationList = patientMedicationList;
    }

    public List<Admit> getAdmitList() {
        if(admitList==null)
        {
            admitList=new ArrayList<>();
        }
        return admitList;
    }

    public void setAdmitList(List<Admit> admitList) {
        this.admitList = admitList;
    }

    public List<PatientVisit> getPatientVisitList() {
        if(patientVisitList==null)
        {
            patientVisitList=new ArrayList<>();
        }
        return patientVisitList;
    }

    public void setPatientVisitList(List<PatientVisit> patientVisitList) {
        this.patientVisitList = patientVisitList;
    }

    public List<PatientDiagnosis> getPatientDiagnosisList() {
         if(patientDiagnosisList==null)
        {
            patientDiagnosisList=new ArrayList<>();
        }
        return patientDiagnosisList;
    }

    public void setPatientDiagnosisList(List<PatientDiagnosis> patientDiagnosisList) {
        this.patientDiagnosisList = patientDiagnosisList;
    }

        
}


