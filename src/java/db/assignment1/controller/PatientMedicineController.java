/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Diagnosis;
import db.assignment1.entity.Doctor;
import db.assignment1.entity.Medicine;
import db.assignment1.entity.Patient;
import db.assignment1.entity.PatientDiagnosis;
import db.assignment1.entity.PatientMedication;
import db.assignment1.entity.PatientVisit;
import db.assignment1.service.MedicineService;
import db.assignment1.service.PatientMedicineService;
import db.assignment1.service.PatientService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
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
@ViewScoped
public class PatientMedicineController {
    @Autowired
    private PatientMedicineService patientMedicineService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private MedicineService medicineService;
    private String message;
    
    private Patient patient;
    
    private PatientMedication patientMedication;
    private List<PatientMedication> patientMedicationList;
    private DataModel<PatientMedication> patientMedicationDataModel;
    private List<Patient> patientList;
    private DataModel<Patient> patientDataModel;
    private List<SelectItem> medicineSelectItemType;
    private List<Medicine> medicineList;
    
    
    public String purchaseMedicine()
    {
        if(patientMedicineService.createPatientMedicineRecord(patientMedicationList))
        {
             message="Medicine purchsed by " +patient.getName();
            patientMedicationList=null;
            patientMedicationDataModel=null;
            patient=null;
           
        }
        else
        {
            message="Erro Purchasing Medicine.";
        }
        return null;
    }
    public String selectPatientMedicationToRemove() {
        try {
            
            patientMedicationList.remove((PatientMedication) patientMedicationDataModel.getRowData());
            patientMedicationDataModel=null;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Patient Medication couldn't be retrieved.";
        }
        return null;
        }
    
    public String selectPatientForMedication() {
        try {
            patient=(Patient) patientDataModel.getRowData();
            patientList=null;
            patientDataModel = null;
            //edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Patient couldn't be retrieved.";
        }
        return null;
        }
    public Patient findPatientById(int patientId)
    {
        return patientService.getPatientById(patientId);
    }
    public Medicine findMedicineById(int medicineId)
    {
        return medicineService.getMedicineById(medicineId);
    }
    public String  addPatienMedication()
    {
        //create New patient
        if(!validPatientMedicine())
        {
            message="Insert Valid Patient or Medicine or Quantity";
               return null;
        }
        
        patientMedication.setPatientId(patient.getId());
        patientMedication.setPurchaseDate(Calendar.getInstance().getTime());
        getPatientMedicationList().add(patientMedication);
        
        patientMedicationDataModel=null;
        patientMedication=null;
        //patientDataModel=null;
        return null;
    }
    private boolean validPatientMedicine()
     {
         if(patientService.getPatientById(patient.getId())==null)
             return false;
         if(medicineService.getMedicineById(patientMedication.getMedicineId())==null)
             return false;
         if(patientMedication.getMedicineQuantity()<=0)
             return false;
         
         else
             return true;
     }
    public String searchPatientByName()
    {
        patientList=patientService.getAllPatientByPatientName(patient.getName());
        patientDataModel=null;
        //message=patientVisit.getPatient().getName()+" Clicked search " +patients.size();
        return null;
       
    }

    public List<SelectItem> getMedicineSelectItemType() {
          if(medicineSelectItemType==null)
        {
            medicineSelectItemType= new ArrayList<>();
            SelectItem selectItem = new SelectItem(0, "Select");
            medicineSelectItemType.add(selectItem);
            for(Medicine medicine:getMedicineList())
            {
            medicineSelectItemType.add(new SelectItem(medicine.getId(),
                        medicine.getMedicineName()+ "(" + medicine.getPrice()+ ")")); 
            }
        }
        return medicineSelectItemType;
    }

    public void setMedicineSelectItemType(List<SelectItem> medicineSelectItemType) {
        this.medicineSelectItemType = medicineSelectItemType;
    }

    public List<Medicine> getMedicineList() {
       if(medicineList==null)
       {
           medicineList=medicineService.getAllMedicineAvailable();
       }
        return medicineList;
    }

    public void setMedicineList(List<Medicine> medicineList) {
        this.medicineList = medicineList;
    }
    
    
     public List<Patient> getPatientList() {
        if(patientList==null)
        {
            patientList=new LinkedList<>();
        }
        return patientList;
    }

    public void setPatientList(List<Patient> patientList) {
        this.patientList = patientList;
    }

    public DataModel<Patient> getPatientDataModel() {
        if(patientDataModel==null)
        {
            patientDataModel=new ListDataModel<>(getPatientList());
        }
        return patientDataModel;
    }

    public void setPatientDataModel(DataModel<Patient> patientDataModel) {
        this.patientDataModel = patientDataModel;
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

    public PatientMedication getPatientMedication() {
        if(patientMedication==null)
        {
            patientMedication=new PatientMedication();
        }
        return patientMedication;
    }

    public void setPatientMedication(PatientMedication patientMedication) {
        this.patientMedication = patientMedication;
    }

    public List<PatientMedication> getPatientMedicationList() {
        if(patientMedicationList==null)
        {
            patientMedicationList=new LinkedList<>();
        }
        return patientMedicationList;
    }

    public void setPatientMedicationList(List<PatientMedication> patientMedicationList) {
        this.patientMedicationList = patientMedicationList;
    }

    public DataModel<PatientMedication> getPatientMedicationDataModel() {
        if(patientMedicationDataModel==null)
        {
            patientMedicationDataModel=new ListDataModel<>(getPatientMedicationList());
        }
        return patientMedicationDataModel;
    }

    public void setPatientMedicationDataModel(DataModel<PatientMedication> patientMedicationDataModel) {
        this.patientMedicationDataModel = patientMedicationDataModel;
    }

    public MedicineService getMedicineService() {
        return medicineService;
    }

    public void setMedicineService(MedicineService medicineService) {
        this.medicineService = medicineService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PatientMedicineService getPatientMedicineService() {
        return patientMedicineService;
    }

    public void setPatientMedicineService(PatientMedicineService patientMedicineService) {
        this.patientMedicineService = patientMedicineService;
    }
    
    
}
