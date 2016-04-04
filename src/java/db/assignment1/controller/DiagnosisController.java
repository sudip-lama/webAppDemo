/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Diagnosis;
import db.assignment1.entity.Doctor;
import db.assignment1.service.DiagnosisService;
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
public class DiagnosisController {
     
    @Autowired
    private DiagnosisService diagnosisService;
    private Diagnosis diagnois;
    private String message;
    private boolean edit=false;
    private DataModel<Diagnosis> diagnosisDataModel;
    private List<Diagnosis> diagnosisList;
    
    public String  saveOrEdit()
    {
        //create New patient
        if(!edit)
        {
           if(diagnosisService.createDiagnosisRecord(diagnois))
           {
               message=diagnois.getName()+"  Record Created";
               diagnois=null;
           }
           else
           {
               message="Error creating Diagnosis Record";
           }
           
        }
        else
        {
            if(diagnosisService.update(diagnois))
            {
                message=diagnois.getName()+"  Record Updated";
               diagnois=null;
            }
            else
            {
            
               message="Error updating Diagnosis Record";
            }
            
            
        }
        edit=false;
        diagnosisList=null;
        diagnosisDataModel=null;
        return null;
    }
    
     public String getDiagnosisForEdit() {
        try {
            diagnois = (Diagnosis) diagnosisDataModel.getRowData();
            //doctorList = null;
            edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Diagnosis Record couldn't be retrieved.";
        }
        return null;
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

    public DiagnosisService getDiagnosisService() {
        return diagnosisService;
    }

    public void setDiagnosisService(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    public Diagnosis getDiagnois() {
        if(diagnois==null)
        {
            diagnois=new Diagnosis();
        }
        return diagnois;
    }

    public void setDiagnois(Diagnosis diagnois) {
        this.diagnois = diagnois;
    }

    public List<Diagnosis> getDiagnosisList() {
        if(diagnosisList==null)
        {
            diagnosisList=diagnosisService.getAllDiagnosis();
        }
        return diagnosisList;
    }

    public void setDiagnosisList(List<Diagnosis> diagnosisList) {
        this.diagnosisList = diagnosisList;
    }

    public DataModel<Diagnosis> getDiagnosisDataModel() {
        if(diagnosisDataModel==null)
        {
            diagnosisDataModel=new ListDataModel<>(getDiagnosisList());
        }
        return diagnosisDataModel;
    }

    public void setDiagnosisDataModel(DataModel<Diagnosis> diagnosisDataModel) {
        this.diagnosisDataModel = diagnosisDataModel;
    }

   
    
    
}
