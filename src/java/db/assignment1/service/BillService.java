/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.service;


import db.assignment1.entity.Bill;
import java.util.List;
/**
 *
 * @author maha
 */
public interface BillService {
    public boolean createBillRecord(Bill bill);
    public List<Bill> getAllBillAvailable();
    public Bill getBillById(Integer BillId);
    public boolean update(Bill bill);

}
