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

//@Table(name = "PATIENT_MEDICINE")
public class PatientMedication {
    
   
    //@Column(name = "PATIENT_MEDICINE_id")
    private int id;
    
    
    //@JoinColumn(name = "PATIENT_ID")
    private Patient patient;
    
    //@JoinColumn(name = "MEDICINE_ID")
    private Medicine medicine;
    
    
    
    private Date payedDate;
    
    //@Column(name = "PURCHASE_DATE")
    
    private Date purchaseDate;
    
    //@Column(name = "PAYMENT_STAUS")
    private boolean paymentStatus;
    
  //@Column(name = "MEDICINE_QUANTITY")
    private int medicineQuantity;
    
    private int totalQuantity;
    
    
    private int patientId;
    
    
    private int medicineId;

    public PatientMedication() {
    }

    public PatientMedication(int totalQuantity, int medicineId) {
        this.totalQuantity = totalQuantity;
        this.medicineId = medicineId;
    }

    public PatientMedication(int id, Date purchaseDate, int medicineQuantity, int patientId, int medicineId) {
        this.id = id;
        this.purchaseDate = purchaseDate;
        this.medicineQuantity = medicineQuantity;
        this.patientId = patientId;
        this.medicineId = medicineId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public Date getPayedDate() {
        return payedDate;
    }

    public void setPayedDate(Date payedDate) {
        this.payedDate = payedDate;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(int medicineId) {
        this.medicineId = medicineId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public int getMedicineQuantity() {
        return medicineQuantity;
    }

    public void setMedicineQuantity(int medicineQuantity) {
        this.medicineQuantity = medicineQuantity;
    }

    public int getTotalQuantity() {
        return totalQuantity;
    }

    public void setTotalQuantity(int totalQuantity) {
        this.totalQuantity = totalQuantity;
    }
    
    
}
