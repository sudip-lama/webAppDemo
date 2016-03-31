/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.entity;

import java.util.Date;

/**
 *
 * @author maha
 */
public class PatientAdmit {
   private int id;
    
    private Date admitDate;
    
    private Date dischargeDate;
    
    private Patient patient;
    
    private Room room;
    
    private String roomNumber;
    
    private int patientId;
 
     public PatientAdmit() {
    }

    public PatientAdmit(Date AdmitDate, Date DischargeDate, String roomNumber, int patientId) {
        this.admitDate = admitDate;
        this.dischargeDate= dischargeDate;
        this.roomNumber= roomNumber;
        this.patientId= patientId;
    }

    public PatientAdmit(int aInt, String string, java.sql.Date date, String string0, String string1) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }
    
    
}
