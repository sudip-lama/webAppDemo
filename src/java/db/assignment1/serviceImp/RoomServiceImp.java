/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.serviceImp;

import db.assignment1.dao.RoomDao;

import db.assignment1.entity.Room;
import db.assignment1.service.RoomService;
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
public class RoomServiceImp implements RoomService{

    @Autowired
    private RoomDao roomDaoImp;
    @Override
    @Transactional(readOnly = false)
    public boolean createRoomRecord(Room room) {
        return roomDaoImp.createRoomRecord(room)>0?true:false;
    }

    @Override
    public List<Room> getAllRoomAvailable() {
        return roomDaoImp.getAllRoomAvailable();
    }

    @Override
    public Room getRoomById(Integer roomId) {
        return roomDaoImp.getRoomById(roomId);
    }

    @Override
    @Transactional(readOnly = false)
    public boolean update(Room room) {
        return roomDaoImp.update(room)>0?true:false;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean payRoom(Integer roomId) {
        return roomDaoImp.payRoom(roomId)>0?true:false;
    }

    @Override
    @Transactional(readOnly = false)
    public boolean reserveRoomById(Integer roomId) {
    return roomDaoImp.reserveRoomById(roomId)>0?true:false;    
    }
    
}
