/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;

import db.assignment1.dao.MedicineDao;
import db.assignment1.entity.Medicine;
import db.assignment1.service.MedicineService;
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
public class MedicineServiceImp implements MedicineService{
     @Autowired
    private MedicineDao medicineDaoImp;

    @Override
    @Transactional(readOnly = false)
    public boolean createMedicineRecord(Medicine medicine) {
     return medicineDaoImp.createMedicineRecord(medicine)>0?true:false;  
    }

 @Override
    public List<Medicine> getAllMedicineAvailable() {
    return medicineDaoImp.getAllMedicine();
    }

    @Override
    public Medicine getMedicineById(Integer medicineId) {
    return medicineDaoImp.getMedicineById(medicineId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updateMedicineRecord(Medicine medicine) {
    return medicineDaoImp.update(medicine)>0?true:false;     
    }
}