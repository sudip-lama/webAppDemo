/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.dao;

import db.assignment1.entity.Medicine;
import java.util.List;

/**
 *
 * @author maha
 */
public interface MedicineDao {
    public int createMedicineRecord(Medicine medicine);
    public List<Medicine> getAllMedicine();
    public Medicine getMedicineById(Integer medicineId);
    public int update(Medicine medicine);
    
}
