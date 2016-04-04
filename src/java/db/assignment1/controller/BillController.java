/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.controller;


import db.assignment1.entity.Bill;
import db.assignment1.service.BillService;
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
    
    private Bill  bill;
    private String message;
    private boolean edit=false;
    private DataModel<Bill> billList;

    
    public String  saveOrEdit()
    {
        //create New Bill
        if(!edit)
        {
           if(billService.createBillRecord(bill))
           {
               message=bill.getBillNumber()+"  Bill Record Created";
               bill=null;
           }
           else
           {
               message="Error creating Bill Record";
           }
           
        }
        else
        {
            if(billService.update(bill))
            {
                message=bill.getBillNumber()+" Bill Record Updated";
               bill=null;
            }
            else
            {
            
               message="Error updating Bill Record";
            }
            
            
        }
        edit=false;
        billList=null;
        return null;
    }
    
     public String getBillForEdit() {
        try {
            bill= (Bill) billList.getRowData();
            billList = null;
            edit = true;
            message="";
        } catch (Exception e) {
            e.printStackTrace();
            message="Bill couldn't be retrieved.";
        }
        return null;
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

    public DataModel<Bill> getBillList() {
         if(billList==null)
        {
            billList=new ListDataModel<>(billService.getAllBillAvailable());
        }
        return billList;
        //return billList;
    }

    public void setBillList(DataModel<Bill> billList) {
        this.billList = billList;
    }
     
}

