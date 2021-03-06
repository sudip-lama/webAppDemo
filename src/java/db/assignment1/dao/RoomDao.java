/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.assignment1.dao;



import db.assignment1.entity.Room;
import java.util.List;

/**
 *
 * @author maha
 */
public interface RoomDao {
     public int createRoomRecord(Room room);
    public List<Room> getAllRoomAvailable();
    public Room getRoomById(Integer roomId);
    public int update(Room room);
    public int payRoom(Integer roomId);
    public int reserveRoomById(Integer roomId);
}
