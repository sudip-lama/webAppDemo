/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;

import db.assignment1.dao.AdmitDao;
import db.assignment1.dao.PatientVisitDao;
import db.assignment1.dao.RoomDao;
import db.assignment1.entity.Admit;
import db.assignment1.service.AdmitService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author SUDIP
 */
@Service
@Transactional(readOnly = true)
public class AdmitServiceImp implements AdmitService {

    @Autowired
    private AdmitDao admitDao;
     @Autowired
    private RoomDao roomDao;

    @Override
    @Transactional(readOnly = false)
    public boolean createAdmitRecord(Admit admit) {
        admit.setPatientId(admit.getPatient().getId());
        admit.setDiseaseId(admit.getDisease().getId());
        admit.setRoomId(admit.getRoom().getId());
        if(roomDao.reserveRoomById(admit.getRoomId())==0)
        {
         return false;   
        }
        return admitDao.createAdmitRecord(admit) > 0 ? true : false;
    }

    @Override
    public List<Admit> getAllCurrentPatientInHospital() {
        return admitDao.getAllCurrentPatientInHospital();
    }

    @Override
    public Admit getAdmitById(Integer admitId) {
        return admitDao.getAdmitById(admitId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean updateAdmitRecord(Admit admit) {
        admit.setPatientId(admit.getPatient().getId());
        admit.setDiseaseId(admit.getDisease().getId());
        admit.setRoomId(admit.getRoom().getId());
        //Get old admit data before changing it
        Admit admitTemp=admitDao.getAdmitById(admit.getId());
        if(admitTemp==null)
            return false;
        if(admitTemp.getRoomId()!=admit.getRoomId())
        {
            //Free the previously allocated room
            roomDao.payRoom(admitTemp.getRoomId());
            //Allocate new room
            if(roomDao.reserveRoomById(admit.getRoomId())==0)
                return false;
        }
        return admitDao.updateAdmitRecord(admit) > 0 ? true : false;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean payAdmit(Admit admit) {
        return admitDao.payAdmit(admit) > 0 ? true : false;
    
    }

    public AdmitDao getAdmitDao() {
        return admitDao;
    }

    public void setAdmitDao(AdmitDao admitDao) {
        this.admitDao = admitDao;
    }

    public RoomDao getRoomDao() {
        return roomDao;
    }

    public void setRoomDao(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

}
