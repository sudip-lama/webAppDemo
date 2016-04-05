/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


/**
 *
 * @author SUDIP
 */

public class Patient implements Serializable {
    
    private int id;
   
    private String name;
    
   
    private Date dob;
    

    private String address;
    
    private String phone;

    private String insurance;
    
    private List<PatientVisit> patientVisit=new ArrayList<>();
    

    private boolean deleteStatus;
    
    public Patient() {
    }

    public Patient(int id, String name, Date dob, String address, String phone) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
    }

    public Patient(int id, String name, Date dob, String address, String phone, String insurance) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
        this.insurance = insurance;
    }
   
   

    public Patient(String name, Date dob, String address, String phone) {
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isDeleteStatus() {
        return deleteStatus;
    }

    public void setDeleteStatus(boolean deleteStatus) {
        this.deleteStatus = deleteStatus;
    }

    public List<PatientVisit> getPatientVisit() {
        return patientVisit;
    }

    public void setPatientVisit(List<PatientVisit> patientVisit) {
        this.patientVisit = patientVisit;
    }

    public String getInsurance() {
        return insurance;
    }

    public void setInsurance(String insurance) {
        this.insurance = insurance;
    }
    
     
     
}
