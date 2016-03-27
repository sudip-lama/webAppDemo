/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.entity;

import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author SUDIP
 */
public class PatientVisit {
    private int id;
    
    private double price;
     
    private String reason;
    
    private Date vistDate;
    
    private Patient patient;
    
    private Doctor doctor;
    
    
    private boolean paymentStatus;

    private int doctorId;
    
    private int patientId;

    public PatientVisit() {
    }

    public PatientVisit(double price, String reason, int doctorId, int patientId) {
        this.price = price;
        this.reason = reason;
        this.doctorId = doctorId;
        this.patientId = patientId;
    }
    
    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
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

    public Date getVistDate() {
        return vistDate;
    }

    public void setVistDate(Date vistDate) {
        this.vistDate = vistDate;
    }
    
    
}
