/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.service.AdmitService;
import db.assignment1.service.PatientDiagnosisService;
import db.assignment1.service.PatientVisitService;
import db.assignment1.service.RoomService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
    private AdmitService admitService;
     @Autowired
    private PatientDiagnosisService patientDiagnosisService;

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
    
}
