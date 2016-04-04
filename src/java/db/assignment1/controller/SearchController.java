/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;

import db.assignment1.entity.Doctor;
import db.assignment1.entity.Patient;
import db.assignment1.entity.PatientVisit;
import db.assignment1.entity.SearchCriteria;
import db.assignment1.service.DoctorService;
import db.assignment1.service.PatientService;
import db.assignment1.service.PatientVisitService;
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
public class SearchController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private DoctorService doctorService;
    @Autowired
    private PatientVisitService patientVisitService;
    private List<SelectItem> doctorSelectItemType;
    private SearchCriteria searchCriteria;
    private List<Patient> patients;
    private DataModel<Patient> patientList;
    private List<Doctor> doctorList;
    private List<PatientVisit> patientVisitList;
    private DataModel<PatientVisit> patientVisitDataModel;
    private String message;
    
    
    public String searchPatientVisit()
    {
        if(!compareDates(searchCriteria))
        {
            message="End date must be greater than begin date";
            patientVisitList=null;
           
            return null;
        }
        else
        {
        patientVisitList=patientVisitService.searchPatientVisitBySearchCriteria(searchCriteria);
         if(patientVisitList==null)
         {
             message="No Record Found!!!";
         }
        }
        
        patientVisitDataModel=null;
        searchCriteria=null;
        return null;
    }
    public boolean compareDates(SearchCriteria searchCriteria)
    {
        if(searchCriteria.getBeginDate()==null)
            return true;
        if(searchCriteria.getEndDate()==null)
            return true;
        if(searchCriteria.getBeginDate().after(searchCriteria.getEndDate()))
            return false;
        return true;
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

    public SearchCriteria getSearchCriteria() {
        if(searchCriteria==null)
        {
            searchCriteria=new SearchCriteria();
        }
        return searchCriteria;
    }

    public void setSearchCriteria(SearchCriteria searchCriteria) {
        this.searchCriteria = searchCriteria;
    }

    public List<Patient> getPatients() {
        if(patients==null)
        {
            patients=new ArrayList<>();
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

    public List<PatientVisit> getPatientVisitList() {
         if(patientVisitList==null)
        {
            patientVisitList=new ArrayList<>();
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

    public PatientVisitService getPatientVisitService() {
        return patientVisitService;
    }

    public void setPatientVisitService(PatientVisitService patientVisitService) {
        this.patientVisitService = patientVisitService;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
       
    
}
