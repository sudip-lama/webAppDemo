/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.entity;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author SUDIP
 */

public class Patient implements Serializable {
    
    private int id;
    private String name;
    
    private Date dob;
    
    private String address;
    
    private int phone;

    public Patient() {
    }
 public Patient(int id, String name, Date dob, String address, int phone) {
        this.id = id;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.phone = phone;
    }
    public Patient(String name, Date dob, String address, int phone) {
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

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }
    
    
}
