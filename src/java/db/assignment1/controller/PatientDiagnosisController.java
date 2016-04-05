/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Diagnosis;
import db.assignment1.entity.Doctor;
import db.assignment1.entity.Patient;
import db.assignment1.entity.PatientDiagnosis;
import db.assignment1.entity.PatientVisit;
import db.assignment1.service.DiagnosisService;
import db.assignment1.service.DiseaseService;
import db.assignment1.service.DoctorService;
import db.assignment1.service.PatientDiagnosisService;
import db.assignment1.service.PatientService;
import java.util.ArrayList;
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
public class PatientDiagnosisController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;

    @Autowired
    private PatientDiagnosisService patientDiagnosisService;
    
    @Autowired
    private DiagnosisService diagnosisService;
    
    private String message;
    private PatientDiagnosis patientDiagnosis;
    private List<PatientDiagnosis> patientDiagnosisList;
    private DataModel<PatientDiagnosis> patientDiagnosisDataModel;
    private List<SelectItem> doctorSelectItemType;
    private List<Doctor> doctorList;
    private List<SelectItem> diagnosisSelectItemType;
    private List<Diagnosis> diagnosisList;
    private List<Patient> patientList;
    private DataModel<Patient> patientDataModel;
    private boolean edit = false;
    
    public String  saveOrEdit()
    {
        //create New patient
        if(!validPatientDiagnosis())
        {
            message="Insert Valid Patient ID or Doctor ID or Diagnosis ID or Diagnosis Date";
               return null;
        }
        if(!edit)
        {
           if(patientDiagnosisService.createPatientDiagnosisRecord(patientDiagnosis))
           {
               message=patientDiagnosis.getPatient().getName()+" Diagnosis record Created";
               patientDiagnosis=null;
           }
           else
           {
               message="Error creating Diagnosis Record";
               return null;
           }
           
        }
        else
        {
            if(patientDiagnosisService.update(patientDiagnosis))
            {
                message=patientDiagnosis.getPatient().getName()+" Diagnosis Record Updated";
               patientDiagnosis=null;
            }
            else
            {
            
               message="Error updating Diagnosis Record";
               return null;
            }
            
            
        }
        //patient=null;
        edit=false;
        patientDiagnosisList=null;
        patientDiagnosisDataModel=null;
        patientList=null;
        patientDataModel=null;
        return null;
    }
    public String selectPatientDiagnosisForEdit() {
        try {
            patientDiagnosis = (PatientDiagnosis) patientDiagnosisDataModel.getRowData();
            patientDiagnosis.setPatient(patientService.getPatientById(patientDiagnosis.getPatientId()));
            patientDiagnosis.setDoctor(doctorService.getDoctorById(patientDiagnosis.getDoctorId()));
            patientDiagnosis.setDiagnosis(diagnosisService.getDiagnosisById(patientDiagnosis.getDiagnosisId()));
            
            //doctorList = null;
            edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Patient Diagnosis couldn't be retrieved.";
        }
        return null;
        }
    private boolean validPatientDiagnosis()
     {
         if(patientService.getPatientById(patientDiagnosis.getPatient().getId())==null)
             return false;
         if(doctorService.getDoctorById(patientDiagnosis.getDoctor().getId())==null)
             return false;
         if(diagnosisService.getDiagnosisById(patientDiagnosis.getDiagnosis().getId())==null)
             return true;
         if(patientDiagnosis.getDiagnosisDate()==null)
             return false;
         else
             return true;
     }
    public String selectPatientForDiagnosis() {
        try {
            patientDiagnosis.setPatient((Patient) patientDataModel.getRowData());
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
    
    public String searchPatientByName()
    {
        patientList=patientService.getAllPatientByPatientName(patientDiagnosis.getPatient().getName());
        patientDataModel=null;
        //message=patientVisit.getPatient().getName()+" Clicked search " +patients.size();
        return null;
       
    }
    
    public Patient findPatientById(int patientId)
    {
        return patientService.getPatientById(patientId);
    }
    public Doctor findDoctorById(int doctorId)
    {
        return doctorService.getDoctorById(doctorId);
    }
    public Diagnosis findDiagnosisById(int diagnosisId)
    {
        return diagnosisService.getDiagnosisById(diagnosisId);
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

    public PatientDiagnosisService getPatientDiagnosisService() {
        return patientDiagnosisService;
    }

    public void setPatientDiagnosisService(PatientDiagnosisService patientDiagnosisService) {
        this.patientDiagnosisService = patientDiagnosisService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PatientDiagnosis getPatientDiagnosis() {
        if(patientDiagnosis==null)
        {
            patientDiagnosis=new PatientDiagnosis();
        }
        return patientDiagnosis;
    }

    public void setPatientDiagnosis(PatientDiagnosis patientDiagnosis) {
        this.patientDiagnosis = patientDiagnosis;
    }

    public List<PatientDiagnosis> getPatientDiagnosisList() {
        if (patientDiagnosisList == null) {
            patientDiagnosisList = patientDiagnosisService.getAllPatientDiagnosis();
        }
        return patientDiagnosisList;
    }

    public void setPatientDiagnosisList(List<PatientDiagnosis> patientDiagnosisList) {
        this.patientDiagnosisList = patientDiagnosisList;
    }

    public DataModel<PatientDiagnosis> getPatientDiagnosisDataModel() {
        if (patientDiagnosisDataModel == null) {
            patientDiagnosisDataModel = new ListDataModel<>(getPatientDiagnosisList());
        }
        return patientDiagnosisDataModel;
    }

    public void setPatientDiagnosisDataModel(DataModel<PatientDiagnosis> patientDiagnosisDataModel) {
        this.patientDiagnosisDataModel = patientDiagnosisDataModel;
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

    public List<SelectItem> getDiagnosisSelectItemType() {
        if(diagnosisSelectItemType==null)
        {
            diagnosisSelectItemType= new ArrayList<>();
            SelectItem selectItem = new SelectItem(0, "Select");
            diagnosisSelectItemType.add(selectItem);
            for(Diagnosis diagnosis:getDiagnosisList())
            {
            diagnosisSelectItemType.add(new SelectItem(diagnosis.getId(),
                        diagnosis.getName() + "(" + diagnosis.getPrice() + ")")); 
            }
        }
        return diagnosisSelectItemType;
    }

    public void setDiagnosisSelectItemType(List<SelectItem> diagnosisSelectItemType) {
        this.diagnosisSelectItemType = diagnosisSelectItemType;
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

    public DiagnosisService getDiagnosisService() {
        return diagnosisService;
    }

    public void setDiagnosisService(DiagnosisService diagnosisService) {
        this.diagnosisService = diagnosisService;
    }

    public boolean isEdit() {
        return edit;
    }

    public void setEdit(boolean edit) {
        this.edit = edit;
    }

}
