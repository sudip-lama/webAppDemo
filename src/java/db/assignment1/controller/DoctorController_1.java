/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Doctor;
import db.assignment1.entity.Patient;
import db.assignment1.service.DoctorService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SUDIP
 */
@Component
@ManagedBean
@RequestScoped
public class DoctorController_1 {
    
    @Autowired
    private DoctorService doctorService;
    
    private Doctor doctor;
    private String message;
    private boolean edit=false;
    private DataModel<Doctor> doctorList;

    
    public String  saveOrEdit()
    {
        //create New patient
        if(!edit)
        {
           if(doctorService.createDoctortRecord(doctor))
           {
               message=doctor.getName()+" doctor Record Created";
               doctor=null;
           }
           else
           {
               message="Error creating Doctor Record";
           }
           
        }
        else
        {
            if(doctorService.updateDoctorRecord(doctor))
            {
                message=doctor.getName()+" doctor Record Updated";
               doctor=null;
            }
            else
            {
            
               message="Error updating Doctor Record";
            }
            
            
        }
        edit=false;
        doctorList=null;
        return null;
    }
    
     public String getDoctorForEdit() {
        try {
            doctor = (Doctor) doctorList.getRowData();
            doctorList = null;
            edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Doctor couldn't be retrieved.";
        }
        return null;
        }
    
    public DoctorService getDoctorService() {
        return doctorService;
    }

    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

    public DataModel<Doctor> getDoctorList() {
        if(doctorList==null)
        {
            doctorList=new ListDataModel<>(doctorService.getAllDoctor());
        }
        return doctorList;
    }

    public void setDoctorList(DataModel<Doctor> doctorList) {
        this.doctorList = doctorList;
    }
    
    
    
    
}
