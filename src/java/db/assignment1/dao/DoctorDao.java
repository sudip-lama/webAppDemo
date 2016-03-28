/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.dao;

import db.assignment1.entity.Doctor;
import db.assignment1.entity.Patient;
import java.util.List;

/**
 *
 * @author SUDIP
 */
public interface DoctorDao {
    public int createDoctorRecord(Doctor doctor);
    public List<Doctor> getAllDoctor();
    public Doctor getDoctorById(Integer doctorId);
    public int update(Doctor doctor);
}
