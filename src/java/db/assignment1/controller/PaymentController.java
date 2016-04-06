/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Patient;
import db.assignment1.service.AdmitService;
import db.assignment1.service.PatientDiagnosisService;
import db.assignment1.service.PatientService;
import db.assignment1.service.PatientVisitService;
import db.assignment1.service.RoomService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SUDIP
 */
@Component
@ManagedBean
@ViewScoped
public class PaymentController {
    @Autowired
    private RoomService roomService;
    
    @Autowired
    private PatientVisitService patientVisitService;
    @Autowired
    private PatientService patientService;
     @Autowired
    private AdmitService admitService;
     @Autowired
    private PatientDiagnosisService patientDiagnosisService;

     private String message;
     private Patient patient;
     private List<Patient> patientList;
     private DataModel<Patient> patientDataModel;
     

     
     public String searchPatientByName() {
        patientList = patientService.getAllPatientByPatientName(patient.getName());
        patientDataModel = null;
        //message=patientVisit.getPatient().getName()+" Clicked search " +patients.size();
        return null;

    }
    public Patient getPatient() {
        if(patient==null)
        {
            patient=new Patient();
        }
        return patient;
    }

    public String getMessage() {
        return message;
    }

    public List<Patient> getPatientList() {
        if(patientList==null)
        {
            patientList=new ArrayList<>();
        }
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public DataModel<Patient> getPatientDataModel() {
        if(patientDataModel==null)
        {
            patientDataModel=new ListDataModel<>(getPatientList());
        }
        return patientDataModel;
    }

    public void setPatientDataModel(DataModel<Patient> patientDataModel) {
        this.patientDataModel = patientDataModel;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public RoomService getRoomService() {
        return roomService;
    }

    public void setRoomService(RoomService roomService) {
        this.roomService = roomService;
    }

    public PatientVisitService getPatientVisitService() {
        return patientVisitService;
    }

    public void setPatientVisitService(PatientVisitService patientVisitService) {
        this.patientVisitService = patientVisitService;
    }

    public AdmitService getAdmitService() {
        return admitService;
    }

    public void setAdmitService(AdmitService admitService) {
        this.admitService = admitService;
    }

    public PatientDiagnosisService getPatientDiagnosisService() {
        return patientDiagnosisService;
    }

    public void setPatientDiagnosisService(PatientDiagnosisService patientDiagnosisService) {
        this.patientDiagnosisService = patientDiagnosisService;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }
    
}
