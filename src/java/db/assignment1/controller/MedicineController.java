/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Disease;
import db.assignment1.entity.Medicine;
import db.assignment1.service.MedicineService;
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
public class MedicineController {
    @Autowired
    private MedicineService medicineService;
    private Medicine medicine;
    private String message;
    private boolean edit=false;
    private DataModel<Medicine> medicineList;
    
    
            public String  saveOrEdit()
    {
        //create New medicine
        if(!edit)
        {
           if(medicineService.createMedicineRecord(medicine))
           {
               message=medicine.getMedicineName()+" medicine Record Created";
               medicine=null;
           }
           else
           {
               message="Error creating Medicine Record";
           }
           
        }
        else
        {
            if(medicineService.updateMedicineRecord(medicine))
            {
                message=medicine.getMedicineName()+" medicine Record Updated";
               medicine=null;
            }
            else
            {
            
               message="Error updating Medicine Record";
            }
            
            
        }
        edit=false;
        medicineList=null;
        return null;
    }
    
     public String getMedicineForEdit() {
        try {
            medicine = (Medicine) medicineList.getRowData();
            medicineList = null;
            edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Medicine couldn't be retrieved.";
        }
        return null;
        }

    public MedicineService getMedicineService() {
        return medicineService;
    }

    public void setMedicineService(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public Medicine getMedicine() {
        if(medicine==null)
        {
            medicine=new Medicine();
        }
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
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

    public DataModel<Medicine> getMedicineList() {
        if(medicineList==null)
        {
            medicineList=new ListDataModel<>(medicineService.getAllMedicineAvailable());
        }
        return medicineList;
    }

    public void setMedicineList(DataModel<Medicine> medicineList) {
        this.medicineList = medicineList;
    }
     
     
}
