/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;


import db.assignment1.entity.Bill;
import db.assignment1.entity.BillDetail;
import db.assignment1.entity.Patient;
import db.assignment1.entity.PatientMedication;
import db.assignment1.service.BillService;
import db.assignment1.service.PatientService;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
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
public class BillController {
    
    @Autowired
    private BillService billService;
    @Autowired
    private PatientService patientService;
    private Patient patient;
    private Bill  bill;
    private String message;
    private boolean edit=false;
    private List<BillDetail> billDetailList;

    private DataModel<BillDetail>billDetailDataModel;
    private List<Patient> patientList;
    private DataModel<Patient> patientDataModel;
    
    private double totalPrice=0;
    
    public String payBills()
    {
        if(getBillDetailList().isEmpty())
        {
          message="Not bill to pay";
          return null;
        }
        if(billService.payBill(billDetailList))
        {
           message="Bill  paid ";
            billDetailList=null;
            billDetailDataModel=null;
            patient=null;
           
        }
        else
        {
            message="Error Paying Bill.";
        }
        return null;
    }
    public String selectBillDescriptionToRemove() {
        try {
            BillDetail bd=(BillDetail) billDetailDataModel.getRowData();
            totalPrice-=bd.getPrice();
            billDetailList.remove(bd);
            billDetailDataModel=null;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Patient Medication couldn't be retrieved.";
        }
        return null;
     }
     public String  viewBill()
    {
        //create New patient
        if(!validPatientBill())
        {
            message="Insert Valid Patient ";
               return null;
        }
        bill=billService.getAllBillAvailableByPatientId(patient.getId());
        addBillToBillDetail();
        
        //patientDataModel=null;
        return null;
    }
    private boolean validPatientBill()
     {
         if(patientService.getPatientById(patient.getId())==null)
             return false;
            
         else
             return true;
     }
    public String selectPatientForBill() {
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
    public String searchPatientByName()
    {
        patientList=patientService.getAllPatientByPatientName(patient.getName());
        patientDataModel=null;
        //message=patientVisit.getPatient().getName()+" Clicked search " +patients.size();
        return null;
       
    }
   
    public void addBillToBillDetail()
    {
        setBillDetailList(null);
        totalPrice=0;
       //Adding PatientMedication bill
        for(Object billObject:bill.getPatientMedicationList())
        {
            BillDetail bd=new BillDetail(billObject);
            totalPrice+=bd.getPrice();
            getBillDetailList().add(bd);
        }
        //Adding admit bill
        for(Object billObject:bill.getAdmitList())
        {
            BillDetail bd=new BillDetail(billObject);
            totalPrice+=bd.getPrice();
            getBillDetailList().add(bd);
        }
        //Adding patient visit
        for(Object billObject:bill.getPatientVisitList())
        {
            BillDetail bd=new BillDetail(billObject);
            totalPrice+=bd.getPrice();
            getBillDetailList().add(bd);
        }
         //Adding patient diagnosis
        for(Object billObject:bill.getPatientDiagnosisList())
        {
            BillDetail bd=new BillDetail(billObject);
            totalPrice+=bd.getPrice();
            getBillDetailList().add(bd);
        }
        billDetailDataModel=null;
    
    }
     public String getBillForEdit() {
        try {
            //bill= (Bill) billList.getRowData();
           // billList = null;
            edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Bill couldn't be retrieved.";
        }
        return null;
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

    public BillService getBillService() {
        return billService;
    }

    public void setBillService(BillService billService) {
        this.billService = billService;
    }

    public Bill getBill() {
        if(bill==null)
        {
            bill=new Bill();
        }
        return bill;
    }

    public void setBill(Bill bill) {
        this.bill = bill;
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

   


    public List<BillDetail> getBillDetailList() {
        if(billDetailList==null)
        {
            billDetailList=new ArrayList<>();
        }
        return billDetailList;
    }

    public void setBillDetailList(List<BillDetail> billDetailList) {
        this.billDetailList = billDetailList;
    }

    public DataModel<BillDetail> getBillDetailDataModel() {
        if(billDetailDataModel==null)
        {
            billDetailDataModel=new ListDataModel<>(getBillDetailList());
        }
        return billDetailDataModel;
    }

    public void setBillDetailDataModel(DataModel<BillDetail> billDetailDataModel) {
        this.billDetailDataModel = billDetailDataModel;
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

    public PatientService getPatientService() {
        return patientService;
    }

    public void setPatientService(PatientService patientService) {
        this.patientService = patientService;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    
     
    
}

