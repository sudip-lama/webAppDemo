/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;



import db.assignment1.dao.BillDao;
import db.assignment1.entity.Bill;
import db.assignment1.entity.BillDetail;
import db.assignment1.service.BillService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author maha
 */
@Service
@Transactional(readOnly = true)
public class BillServiceImp implements BillService{

    @Autowired
    private BillDao billDaoImp;
    
    
    @Override
    public Bill getAllBillAvailableByPatientId(int patientId) {
    return billDaoImp.getAllBillAvailableByPatientId(patientId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean payBill(List<BillDetail> billDetailList) {
    return billDaoImp.payBill(billDetailList);
    }

    
}
