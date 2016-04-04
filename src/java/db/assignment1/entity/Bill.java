/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author maha
 */
public class Bill {
    private int id;
    
    private String billNumber;
    
    private Date billDate;
    
    private double total;

    private String preparedBy;

    public Bill() {
    }
    
   public Bill(String billNumber, Date billDate, double total, String preparedBy) {
        this.billNumber= billNumber; 
        this.billDate= billDate;
        this.total= total;
        this.preparedBy = preparedBy;
    }

    public Bill(int id, String billNumber, Date billDate, double total, String preparedBy ) {
        this.id = id;
        this.billNumber= billNumber; 
        this.billDate= billDate;
        this.total= total;
        this.preparedBy = preparedBy;
        
    } 

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(Date billDate) {
        this.billDate = billDate;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getPreparedBy() {
        return preparedBy;
    }

    public void setPreparedBy(String preparedBy) {
        this.preparedBy = preparedBy;
    }
    
    
    
}


