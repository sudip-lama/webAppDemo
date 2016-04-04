/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Disease;
import db.assignment1.entity.Doctor;
import db.assignment1.service.DiseaseService;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
/**
 *
 * @author maha
 */
@Component
@ManagedBean
@RequestScoped
public class DiseaseController {
    
    @Autowired
    private DiseaseService diseaseService;
    
    private Disease disease;
    private String message;
    private boolean edit=false;
    private DataModel<Disease> diseaseList;
    
     public String  saveOrEdit()
    {
        //create New disease
        if(!edit)
        {
           if(diseaseService.createDiseaseRecord(disease))
           {
               message=disease.getDiseaseName()+" disease Record Created";
               disease=null;
           }
           else
           {
               message="Error creating Disease Record";
           }
           
        }
        else
        {
            if(diseaseService.updateDiseaseRecord(disease))
            {
                message=disease.getDiseaseName()+" disease Record Updated";
               disease=null;
            }
            else
            {
            
               message="Error updating Disease Record";
            }
            
            
        }
        edit=false;
        diseaseList=null;
        return null;
    }
    
     public String getDiseaseForEdit() {
        try {
            disease = (Disease) diseaseList.getRowData();
            diseaseList = null;
            edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Disease couldn't be retrieved.";
        }
        return null;
        }

    public DiseaseService getDiseaseService() {
        return diseaseService;
    }

    public void setDiseaseService(DiseaseService diseaseService) {
        this.diseaseService = diseaseService;
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

    public DataModel<Disease> getDiseaseList() {
         if(diseaseList==null)
        {
            diseaseList=new ListDataModel<>(diseaseService.getAllDiseaseAvailable());
        }
        return diseaseList;
    }

    public void setDiseaseList(DataModel<Disease> diseaseList) {
        this.diseaseList = diseaseList;
    }


}
