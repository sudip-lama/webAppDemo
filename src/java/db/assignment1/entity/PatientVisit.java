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
    
    private int hr;
    private int min;
    private String amPm;
    
    private boolean paymentStatus;

    private int doctorId;
    
    private int patientId;

    public PatientVisit() {
    }

    public PatientVisit(int id, double price, String reason, Date vistDate, int hr, 
            String amPm, int min, int doctorId, int patientId) {
        this.id = id;
        this.price = price;
        this.reason = reason;
        this.vistDate = vistDate;
        this.hr = hr;
        this.amPm = amPm;
        this.min = min;
        this.doctorId = doctorId;
        this.patientId = patientId;
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
        if(patient==null)
        {
            patient=new Patient();
        }
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public int getHr() {
        return hr;
    }

    public void setHr(int hr) {
        this.hr = hr;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
    }

    public String getAmPm() {
        return amPm;
    }

    public void setAmPm(String amPm) {
        this.amPm = amPm;
    }
    
    public String  visitTime()
    {
        return this.hr+":"+this.min+"  "+this.amPm;
    }
}
