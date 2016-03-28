/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Patient;
import db.assignment1.service.PatientService;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.ArrayDataModel;
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
public class PatientController {

    @Autowired
    private PatientService patientService;
    
    private Patient patient;
    private List<Patient> patients;
    private int patientId;
    private String message;
    private boolean edit=false;
    private DataModel<Patient> patientList;
    
    /**
     * Creates a new instance of PatientController
     */
    public PatientController() {
    }
    public String  saveOrEdit()
    {
        //create New patient
        if(!edit)
        {
           if(patientService.createPatientRecord(patient))
           {
               message=patient.getName()+" patient Record Created";
               patient=null;
           }
           else
           {
               message="Error creating Patient Record";
           }
           
        }
        else
        {
            if(patientService.updatePatientRecord(patient))
            {
                message=patient.getName()+" patient Record Updated";
               patient=null;
            }
            else
            {
            
               message="Error updating Patient Record";
            }
            
            
        }
        edit=false;
        patientList=null;
        return null;
    }
    
        public String getPatientForEdit() {
        try {
            patient = (Patient) patientList.getRowData();
            patientList = null;
            edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Patient couldn't be retrieved.";
        }
        return null;
        }

    public PatientService getPatientService() {
        return patientService;
    }

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
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

    public List<Patient> getPatients() {
        if(patients==null)
        {
            //Intially returning all patient details
            patients=patientService.getAllPatient();
        }
        return patients;
    }

    public DataModel<Patient> getPatientList() {
        if(patientList==null)
        {
            patientList=new ListDataModel<>(patientService.getAllPatient());
        }
        return patientList;
    }

    public void setPatientList(DataModel<Patient> patientList) {
        this.patientList = patientList;
    }
    
    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
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
    
}
