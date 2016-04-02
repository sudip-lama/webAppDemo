/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Doctor;
import db.assignment1.entity.Patient;
import db.assignment1.entity.PatientVisit;
import db.assignment1.service.DoctorService;
import db.assignment1.service.PatientService;
import db.assignment1.service.PatientVisitService;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
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
public class PatientVisitController {
    @Autowired
    private PatientVisitService patientVisitService;
    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    private Patient patient;
    private PatientVisit patientVisit;
    private String message;
    private List<SelectItem> doctorSelectItemType;
    private List<PatientVisit> patientVisitList;
    private DataModel<PatientVisit> patientVisitDataModel;
    private List<Doctor> doctorList;
    private List<Patient> patients;
    private DataModel<Patient> patientList;
    
   
    private List<SelectItem> amPmSelectItem;
    private boolean edit = false;

    public PatientVisitController() {
         patientVisit=null;
         patients=null;
    }
    
    

     public String  saveOrEdit()
    {
        //create New patient
        if(!validPatientVisit())
        {
            message="Insert Valid PID,DID and Visit Date";
               return null;
        }
        if(!edit)
        {
           if(patientVisitService.createPatientVisitRecord(patientVisit))
           {
               message=patientVisit.getPatient().getName()+" visit Record Created";
               patientVisit=null;
           }
           else
           {
               message="Error creating visit Record";
               return null;
           }
           
        }
        else
        {
            if(patientVisitService.updatePatientVisitRecord(patientVisit))
            {
                message=patientVisit.getPatient().getName()+" visit Record Updated";
               patientVisit=null;
            }
            else
            {
            
               message="Error updating visit Record";
               return null;
            }
            
            
        }
        patientVisit=null;
        edit=false;
        patientVisitList=null;
        patientVisitDataModel=null;
        return null;
    }
     private boolean validPatientVisit()
     {
         if(patientService.getPatientById(patientVisit.getPatient().getId())==null)
             return false;
         if(doctorService.getDoctorById(patientVisit.getDoctor().getId())==null)
             return false;
         if(patientVisit.getVistDate()==null)
             return false;
         else
             return true;
     }
      public String selectPatientVisitForEdit() {
        try {
            patientVisit = (PatientVisit) patientVisitDataModel.getRowData();
            patientVisit.setPatient(patientService.getPatientById(patientVisit.getPatientId()));
            patientVisit.setDoctor(doctorService.getDoctorById(patientVisit.getDoctorId()));
            
            //doctorList = null;
            edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Doctor couldn't be retrieved.";
        }
        return null;
        }
    public String searchPatientByName()
    {
        patients=patientService.getAllPatientByPatientName(patientVisit.getPatient().getName());
        patientList=null;
        //message=patientVisit.getPatient().getName()+" Clicked search " +patients.size();
        return null;
       
    }
    
     public String selectPatientForVisit() {
        try {
            patientVisit.setPatient((Patient) patientList.getRowData());
            patients=null;
            patientList = null;
            //edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Patient couldn't be retrieved.";
        }
        return null;
        }
    
    
    public PatientVisitService getPatientVisitService() {
        return patientVisitService;
    }

    public void setPatientVisitService(PatientVisitService patientVisitService) {
        this.patientVisitService = patientVisitService;
    }

    public PatientService getPatientService() {
        return patientService;
    }

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    public DoctorService getDoctorService() {
        return doctorService;
    }

    public void setDoctorService(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    public PatientVisit getPatientVisit() {
        if(patientVisit==null)
        {
            patientVisit=new PatientVisit();
        }
        return patientVisit;
    }

    public void setPatientVisit(PatientVisit patientVisit) {
        this.patientVisit = patientVisit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Doctor> getDoctorList() {
        if(doctorList==null)
        {
            doctorList=doctorService.getAllDoctor();
        }
        return doctorList;
    }

    public void setDoctorList(List<Doctor> doctorList) {
        this.doctorList = doctorList;
    }

    public List<SelectItem> getDoctorSelectItemType() {
        if(doctorSelectItemType==null)
        {
            doctorSelectItemType= new ArrayList<>();
            SelectItem selectItem = new SelectItem(0, "Select");
            doctorSelectItemType.add(selectItem);
            for(Doctor doctor:getDoctorList())
            {
            doctorSelectItemType.add(new SelectItem(doctor.getId(),
                        doctor.getName() + "(" + doctor.getSpecialist() + ")")); 
            }
        }
        return doctorSelectItemType;
    }

    public void setDoctorSelectItemType(List<SelectItem> doctorSelectItemType) {
        this.doctorSelectItemType = doctorSelectItemType;
    }

    public List<PatientVisit> getPatientVisitList() {
        if(patientVisitList==null)
        {
            patientVisitList=patientVisitService.getAllPatientVisit();
        }
        return patientVisitList;
    }

    public void setPatientVisitList(List<PatientVisit> patientVisitList) {
        this.patientVisitList = patientVisitList;
    }

    public DataModel<PatientVisit> getPatientVisitDataModel() {
        if(patientVisitDataModel==null)
        {
            patientVisitDataModel=new ListDataModel<>(getPatientVisitList());
        }
       
        return patientVisitDataModel;
    }

    public void setPatientVisitDataModel(DataModel<PatientVisit> patientVisitDataModel) {
        this.patientVisitDataModel = patientVisitDataModel;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
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
            patients=new LinkedList<>();
        }
        return patients;
    }

    public void setPatients(List<Patient> patients) {
        this.patients = patients;
    }

    public DataModel<Patient> getPatientList() {
        if(patientList==null)
        {
            patientList=new ListDataModel<>(getPatients());
        }
        return patientList;
    }

    public void setPatientList(DataModel<Patient> patientList) {
        this.patientList = patientList;
    }

    public List<SelectItem> getAmPmSelectItem() {
         if(amPmSelectItem==null)
        {
             amPmSelectItem= new ArrayList<>();
            SelectItem selectItem = new SelectItem("AM", "AM");
            amPmSelectItem.add(selectItem);
            amPmSelectItem.add(new SelectItem("PM","PM")); 
        }
        return amPmSelectItem;
    }

    public void setAmPmSelectItem(List<SelectItem> amPmSelectItem) {
        this.amPmSelectItem = amPmSelectItem;
    }
    
    public Patient findPatientById(int patientId)
    {
        return patientService.getPatientById(patientId);
    }
    public Doctor findDoctorById(int doctorId)
    {
        return doctorService.getDoctorById(doctorId);
    }
}
