/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.entity;

import java.util.Date;


/**
 *
 * @author SUDIP
 */
public class PatientDiagnosis {
    private int id;
        
    private Date diagnosisDate;
      
    private Patient patient;
    
    private Diagnosis diagnosis;
    
    private Doctor doctor;
    
    
    private boolean paymentStatus;

    private int doctorId;
    
    private int patientId;
    
    private int diagnosisId;

    public PatientDiagnosis() {
    }

    public PatientDiagnosis(int id, Date diagnosisDate,  int patientId, int doctorId, int diagnosisId) {
        this.id = id;
        this.diagnosisDate = diagnosisDate;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.diagnosisId = diagnosisId;
    }
    
    public PatientDiagnosis(Date diagnosisDate,  int patientId, int doctorId, int diagnosisId) {
        this.diagnosisDate = diagnosisDate;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.diagnosisId = diagnosisId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDiagnosisDate() {
        return diagnosisDate;
    }

    public void setDiagnosisDate(Date diagnosisDate) {
        this.diagnosisDate = diagnosisDate;
    }

    public Patient getPatient() {
        if(patient==null)
        {
            patient=new Patient();
        }
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Diagnosis getDiagnosis() {
        if(diagnosis==null)
        {
            diagnosis=new Diagnosis();
        }
        return diagnosis;
    }

    public void setDiagnosis(Diagnosis Diagnosis) {
        this.diagnosis = Diagnosis;
    }

    public Doctor getDoctor() {
        if(doctor==null)
        {
            doctor=new Doctor();
        }
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDiagnosisId() {
        return diagnosisId;
    }

    public void setDiagnosisId(int diagnosisId) {
        this.diagnosisId = diagnosisId;
    }

}
