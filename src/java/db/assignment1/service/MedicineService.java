/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.service;

import db.assignment1.entity.Medicine;
import java.util.List;

/**
 *
 * @author maha
 */
public interface MedicineService {
    public boolean createMedicineRecord(Medicine medicine);
    public List<Medicine> getAllMedicineAvailable();
    public Medicine getMedicineById(Integer medicineId);

    public boolean updateMedicineRecord(Medicine medicine);
    
}
