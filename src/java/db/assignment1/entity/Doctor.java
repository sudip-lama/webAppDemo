/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.entity;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public class Doctor {
    private int id;
    
    private String name;
    
    private String specialist;
    
    private String address;
    
    private String phone;
    
   

    private List<PatientVisit> patientVisit=new ArrayList<>();
    
    public Doctor() {
    }

    public Doctor(String name, String specialist, String address, String phone) {
        this.name = name;
        this.specialist = specialist;
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

    public String getSpecialist() {
        return specialist;
    }

    public void setSpecialist(String specialist) {
        this.specialist = specialist;
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

    

    public List<PatientVisit> getPatientVisit() {
        return patientVisit;
    }

    public void setPatientVisit(List<PatientVisit> patientVisit) {
        this.patientVisit = patientVisit;
    }
    
    
}
