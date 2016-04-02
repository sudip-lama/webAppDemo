/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Doctor;
import db.assignment1.service.DoctorService;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author SUDIP
 */
@Component
@ManagedBean
@RequestScoped
public class DoctorController {
    
    @Autowired
    private DoctorService doctorService;
    
    private Doctor doctor;
    private String message;
    private boolean edit=false;
    private DataModel<Doctor> doctorList;
    private List<SelectItem> specialistSelectItem;
    
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

    public List<SelectItem> getSpecialistSelectItem() {
        if(specialistSelectItem==null)
        {
             specialistSelectItem= new ArrayList<>();
            SelectItem selectItem = new SelectItem(null, "Select");
            specialistSelectItem.add(selectItem);
            
            specialistSelectItem.add(new SelectItem("Allergy & Immunology","Allergy & Immunology")); 
            specialistSelectItem.add(new SelectItem("Anesthesiology","Anesthesiology"));
            specialistSelectItem.add(new SelectItem("Blood Banking - Transfusion Medicine","Blood Banking - Transfusion Medicine"));
            specialistSelectItem.add(new SelectItem("Cardiothoracic Radiology","Cardiothoracic Radiology"));
            specialistSelectItem.add(new SelectItem("Cardiovascular Disease","Cardiovascular Disease"));
            specialistSelectItem.add(new SelectItem("Gastroenterology","Gastroenterology"));
            specialistSelectItem.add(new SelectItem("Neurology","Neurology"));
            
        }
         return specialistSelectItem;
    }

    public void setSpecialistSelectItem(List<SelectItem> specialistSelectItem) {
        this.specialistSelectItem = specialistSelectItem;
    }
    
    
    
    
}
