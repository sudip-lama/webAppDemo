/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.service;


import db.assignment1.entity.Bill;
import db.assignment1.entity.BillDetail;
import java.util.List;
/**
 *
 * @author maha
 */
public interface BillService {
    public Bill getAllBillAvailableByPatientId(int patientId);
public boolean payBill(List<BillDetail> billDetailList);
}
