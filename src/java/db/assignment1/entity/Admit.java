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

public class Admit {
   
    private int id;
    private Date admitDate;
    private boolean paymentStatus;
    private Date dischargeDate;
    private Patient patient;    
    private Room room;
    private Disease disease;
    private int patientId;
    private int roomId;
    private int diseaseId;
    private String roomNumber;
    private double roomPrice;
    private double averageStay;

    public Admit(int diseaseId, double averageStay) {
        this.diseaseId = diseaseId;
        this.averageStay = averageStay;
    }

    public double getAverageStay() {
        return averageStay;
    }

    public void setAverageStay(double averageStay) {
        this.averageStay = averageStay;
    }
    
    

    public Admit(int id, Date admitDate, int roomId, 
            String roomNumber, double roomPrice) {
        this.id = id;
        this.admitDate = admitDate;
        this.roomId = roomId;
        this.roomNumber = roomNumber;
        this.roomPrice = roomPrice;
    }
    
 
    public Admit() {
    }

    public Admit(int id, Date admitDate, int patientId, int roomId, int diseaseId) {
        this.id = id;
        this.admitDate = admitDate;
        this.patientId = patientId;
        this.roomId = roomId;
        this.diseaseId = diseaseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getAdmitDate() {
        return admitDate;
    }

    public void setAdmitDate(Date admitDate) {
        this.admitDate = admitDate;
    }

    public Date getDischargeDate() {
        return dischargeDate;
    }

    public void setDischargeDate(Date dischargeDate) {
        this.dischargeDate = dischargeDate;
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

    public Room getRoom() {
        if(room==null)
        {
            room=new Room();
        }
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Disease getDisease() {
        if(disease==null)
        {
            disease=new Disease();
        }
        return disease;
    }

    public void setDisease(Disease disease) {
        this.disease = disease;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public boolean isPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(boolean paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public double getRoomPrice() {
        return roomPrice;
    }

    public void setRoomPrice(double roomPrice) {
        this.roomPrice = roomPrice;
    }

}
